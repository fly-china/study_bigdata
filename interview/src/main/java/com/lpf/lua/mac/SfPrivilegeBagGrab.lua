--
-- Created by IntelliJ IDEA.
-- User: zhangrong
-- Date: 2018/10/8
-- Time: 18:07
-- To change this template use File | Settings | File Templates.
--
-- 返回结果说明
--0 领取失败
--1 已从相同的包领过特权金
--2 无可领取包需创建包

--抢红包lua脚本

local SF_PRIVILEGE_BAG_KEY = KEYS[1] -- 顺丰特权包池子 ，SortedSet<包ID，过期时间>

local INVENTORY_KEY_PREFIX = KEYS[2] --特权包中待领特权金，List<特权金JSON>

local GRAB_RESULT_KEY_PREFIX = KEYS[3] --特权包领取结果，Hash<特权金ID，领取时间>

local IS_GRAB_KEY_PREFIX = KEYS[4] --礼包领取记录，Hash<UID，特权金id>


local customerId = ARGV[1] --用户id

local receiveTime = ARGV[2] --领取时间

local expireTime = ARGV[3] --待生成特权金有效时间



--local SF_PRIVILEGE_BAG_KEY = 'mac:shopRedBag:SF:bag'
--
--local INVENTORY_KEY_PREFIX = 'mac:shopRedBag:b:a:i:'
--
--local GRAB_RESULT_KEY_PREFIX = 'mac:shopRedBag:b:a:r:'
--
--local IS_GRAB_KEY_PREFIX = 'mac:shopRedBag:b:a:g:'
--
--local customerId = '123456'
--
--local receiveTime = '1539153497445'
--
--local expireTime = '1539792000000'

--删除池子里过期的包
redis.call('ZREMRANGEBYSCORE', SF_PRIVILEGE_BAG_KEY, '-inf', receiveTime)

local grabBagId = 0

local resultJson = '0'

--判断未过期的包 按照分数从小到大获取第一个包  判断包是否被领光  已领光进行删除操作  未领光从当前包中领取
while redis.call('zcard', SF_PRIVILEGE_BAG_KEY) > 0 do
    local bagIdList = redis.call('zrange', SF_PRIVILEGE_BAG_KEY, 0, 0)
    local bagId = bagIdList[1]
    local bagInventoryKey = INVENTORY_KEY_PREFIX .. bagId
    local num = redis.call('llen', bagInventoryKey)
    if num > 0 then
        grabBagId = bagId
        break
    else
        redis.call('zrem', SF_PRIVILEGE_BAG_KEY, bagId)
    end
end


if grabBagId == 0 then
    --    需要创建包
    return '2'
end

-- 设置默认领取结果为空，并起到拦截二次领取的作用
local isGrabed = 0 == redis.call('hsetnx', IS_GRAB_KEY_PREFIX .. grabBagId, customerId, '0')
if isGrabed then
    -- 如果抢过了，直接返回1 、0
    local grabedResult = redis.call('hget', IS_GRAB_KEY_PREFIX .. grabBagId, customerId)
    if (grabedResult ~= nil and grabedResult ~= '0') then
        return '1' --同一个包已经存在领取记录
        --    else
        --        return '0'
    end
end

local grabBagInventoryKey = INVENTORY_KEY_PREFIX .. grabBagId

-- 【lpop抢】
local amountId = redis.call('lpop', grabBagInventoryKey)

--领取的包是否还有可领取特权金 没有的话从池子里删除
if redis.call('llen', grabBagInventoryKey) == 0 then
    redis.call('zrem', SF_PRIVILEGE_BAG_KEY, grabBagId)
end

if amountId then

    -- 更新领取结果
    redis.call('hset', IS_GRAB_KEY_PREFIX .. grabBagId, customerId, amountId)
    -- 记录礼包领取结果
    redis.call('hset', GRAB_RESULT_KEY_PREFIX .. grabBagId, amountId, expireTime)

    local num = redis.call('zcard', SF_PRIVILEGE_BAG_KEY)
    --抢红包结果
    local grabResult = {
        privilegeAmountId = amountId,
        leftNum = num
    }
    resultJson = cjson.encode(grabResult)
end
return resultJson


