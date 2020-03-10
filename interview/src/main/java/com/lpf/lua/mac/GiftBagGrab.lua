--抢红包lua脚本
-- 一次领取一个红包
local GIFTBAG_KEY = KEYS[1]
local INVENTORY_KEY = KEYS[2]
local LIFECYCLE_KEY = KEYS[3]
local GRAB_RESULT_KEY = KEYS[4]
local PENDING_SYNC_KEY = KEYS[5]
local IS_GRAB_KEY = KEYS[6]

local customerId = ARGV[1]
local customerNickname = ARGV[2]
local customerAvatarUrl = ARGV[3]
local createTime = ARGV[4]
local giftBagId = ARGV[5]


-- 查询“礼包数据”的过期时间（秒）
local ttl = redis.call('ttl', GIFTBAG_KEY)
-- 设置默认领取结果为空，并起到拦截二次领取的作用
local isGrabed = 0 == redis.call('hsetnx', IS_GRAB_KEY, customerId, "[]");
if isGrabed then
    -- 如果抢过了，直接返回1 、0
    local grabedResult = redis.call('hget', IS_GRAB_KEY, customerId)
    return grabedResult ~= nil and grabedResult ~= "[]"
end
-- 未抢过（setnx成功）设置数据有效期
redis.call('expire', IS_GRAB_KEY, ttl);

-- 返回结果
local resultJson

local redbag

repeat
    -- 重置redbag
    redbag = nil

    -- 【lpop抢】
    local redbagJson = redis.call('lpop', INVENTORY_KEY)

    -- 取到则解码
    if redbagJson then
        redbag = cjson.decode(redbagJson)
    end

    -- 如果最后一个红包是过期的，再放回库存中，这个过期红包用于判断礼包是否过期
    local leftNumber = redis.call('llen', INVENTORY_KEY)
    if(redbag ~= nil and leftNumber == 0 and (redbag.expireTime == nil or ""..redbag.expireTime <= createTime)) then
        redis.call('lpush', INVENTORY_KEY, redbagJson)
        redis.call('expire', INVENTORY_KEY, ttl)
        redbag = nil
    end

    -- 取到空（取光） 或 取到有效红包（取到过期红包就重复再取一个）
    -- 此处传入的createTime为字符串，需要将expireTime转为字符串再比较
until (redbag == nil or (redbag.expireTime and ""..redbag.expireTime > createTime))

local receiver = {
    customerId = customerId,
    customerNickname = customerNickname,
    customerAvatarUrl = customerAvatarUrl
}

-- 如果抢到
if redbag and redbag.expireTime then
    -- 抢到红包列表（群抢只能抢到1个）,lua脚本下标从1开始
    local redbagList = {}
    redbagList[#redbagList + 1] = redbag

    -- 返回结果列表
    local redbagResultList = {};

    --遍历红包列表生成抢结果列表以及待同步列表
    for i,v in ipairs(redbagList) do
        -- 【从有效期红包集合中移除】
        redis.call('zrem', LIFECYCLE_KEY, v.redBagId)
        --抢红包结果
        local grabResult = {
            giftBagId = giftBagId,
            redBagId = v.redBagId,
            createTime = createTime,

            receiver = receiver,
            redbag = v
        }

        -- 加入返回结果列表
        redbagResultList[#redbagResultList + 1] = grabResult

        -- 序列化
        local grabResultJson = cjson.encode(grabResult)

        --【zadd到待同步列表】
        redis.call('zadd', PENDING_SYNC_KEY, createTime, grabResultJson)

        --【lpush抢结果】
        redis.call('lpush', GRAB_RESULT_KEY, grabResultJson)
    end

    resultJson = cjson.encode(redbagResultList)

    -- 【设置抢结果为红包列表】
    redis.call("hset", IS_GRAB_KEY, customerId, resultJson)

    -- 刷新“领取结果”的过期时间与库存数据相同，主要用于礼包第一次被抢到时
    redis.call('expire', GRAB_RESULT_KEY, ttl)

    -- 刷新“领取记录”的过期时间与库存数据相同，set操作会重置TTL，需要刷新该有效期
--    redis.call('expire', IS_GRAB_KEY, ttl)
end

return resultJson