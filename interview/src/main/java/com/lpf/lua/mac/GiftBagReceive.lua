-- 领红包lua脚本
-- 一次领取所有红包
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
local isGrabed = 0 == redis.call('hsetnx', IS_GRAB_KEY, customerId, "[]")
if isGrabed then
	-- 如果抢过了，直接返回1 、0
	local grabedResult = redis.call('hget', IS_GRAB_KEY, customerId)
	return grabedResult ~= nil and grabedResult ~= "[]"
end
-- 未抢过（setnx成功）设置数据有效期
redis.call('expire', IS_GRAB_KEY, ttl);
--【lrange查询】出红包列表
local total = redis.call('llen', INVENTORY_KEY)
local redbagJsonList = redis.call('lrange', INVENTORY_KEY, 0, total)
local resultJson

local receiver = {
	customerId = customerId,
	customerNickname = customerNickname,
	customerAvatarUrl = customerAvatarUrl
}

--如果抢到
if (redbagJsonList ~= nil and #redbagJsonList > 0) then
	-- 【清除库存】
	redis.call('del', INVENTORY_KEY)
	-- 返回结果列表
	local grabJsonList = {}
	--遍历红包列表生成抢结果列表以及待同步列表
	for i,v in ipairs(redbagJsonList) do
		-- 红包
		local redbag = cjson.decode(v)

		-- 不过期的红包进行处理
		if(""..redbag.expireTime > createTime) then

			--【从有效期红包集合中移除】
			redis.call('zrem', LIFECYCLE_KEY, redbag.redBagId)

			--抢红包结果
			local grabResult = {
				giftBagId = giftBagId,
				redBagId = redbag.redBagId,
				createTime = createTime,

				receiver = receiver,
				redbag = redbag
			}

			-- json结果加入返回列表
			grabJsonList[#grabJsonList + 1] = grabResult

			-- 加入列表
			local grabJson = cjson.encode(grabResult);

			--【lpush抢结果】
			redis.call('lpush', GRAB_RESULT_KEY, grabJson)

			--【zadd到待同步列表】
			redis.call('zadd', PENDING_SYNC_KEY, createTime, grabJson)
		end
	end
	-- 刷新“领取结果”的过期时间与库存数据相同，主要用于礼包第一次被抢到时
	redis.call('expire', GRAB_RESULT_KEY, ttl);

	if(#grabJsonList == 0) then
		-- 如果没领到，把所有数据扔回库存，用以判断礼包是否过期
		for i,v in ipairs(redbagJsonList) do
			redis.call("lpush", INVENTORY_KEY, v)
		end
		redis.call('expire', INVENTORY_KEY, ttl);
	else
		-- 如果抢到了，更新IS_GRAB_KEY
		resultJson = cjson.encode(grabJsonList)
		--【设置抢结果为红包列表】
		redis.call("hset", IS_GRAB_KEY, customerId, resultJson)
		-- 刷新“领取记录”的过期时间与库存数据相同，set操作会重置TTL，需要刷新该有效期
		redis.call('expire', IS_GRAB_KEY, ttl);
	end

end

return resultJson