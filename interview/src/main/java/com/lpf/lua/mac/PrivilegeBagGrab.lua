--抢红包lua脚本
-- 一次领取一个红包
local PRIVILEGE_BAG_KEY = KEYS[1]
--特权包中待领特权金，List<特权金JSON>
local INVENTORY_KEY = KEYS[2]
--特权包领取结果，Hash<特权金ID，领取时间>
local GRAB_RESULT_KEY = KEYS[3]
--礼包领取记录，Hash<UID，特权金id>
local IS_GRAB_KEY = KEYS[4]
--特权金数据，String<特权金JSON>
local PRIVILEGE_AMOUNT_KEY_PREFIX = KEYS[5]

--用户id
local customerId = ARGV[1]
--用户昵称
local customerNickname = ARGV[2]
--用户头像
local customerAvatarUrl = ARGV[3]
--领取时间
local receiveTime = ARGV[4]
--特权包id
local privilegeBagId = ARGV[5]
--待生成特权金有效时间
local expireTime = ARGV[6]


-- 设置默认领取结果为空，并起到拦截二次领取的作用
local isGrabed = 0 == redis.call('hsetnx', IS_GRAB_KEY, customerId, "0")
if isGrabed then
    -- 如果抢过了，直接返回1 、0
    local grabedResult = redis.call('hget', IS_GRAB_KEY, customerId)
    if (grabedResult ~= nil and grabedResult ~= '0')
    then
        return '1'
    else
        return '0'
    end
end

-- 返回结果
local resultJson = "0"
-- 【lpop抢】
local amountId = redis.call('lpop', INVENTORY_KEY)
if amountId then
    -- 更新领取结果
    redis.call('hset', IS_GRAB_KEY, customerId, amountId)
    -- 记录礼包领取结果
    redis.call('hset', GRAB_RESULT_KEY, amountId, expireTime)

    local num = redis.call('hlen', GRAB_RESULT_KEY)
    --抢红包结果
    --抢红包结果`
    local grabResult = {
        privilegeAmountId = amountId,
        grabNum = num
    }
    resultJson = cjson.encode(grabResult)
end
return resultJson