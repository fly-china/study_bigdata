--抢红包雨lua脚本
local REDBAG_LIMIT_KEY = KEYS[1]
local REDBAG_INFO_KEY = KEYS[2]
local REDBAG_USER_KEY = KEYS[3]

local userId = ARGV[1]

-- 抢了超过3个，返回没抢到
local grabCount = redis.call('hincrby', REDBAG_LIMIT_KEY, customerId, 1)
if(grabCount > 3) then
    return "-1"
end

-- pop一个红包数据
local amount = redis.call('lpop', REDBAG_INFO_KEY)

-- 没抢到返回0
if(amount == nil) then
    return "-2"
end

-- 放入结果Set
redis.call('zadd', REDBAG_USER_KEY, amount, userId.."-"..grabCount);

return amount
