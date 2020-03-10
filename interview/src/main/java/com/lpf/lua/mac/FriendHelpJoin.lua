-- 好友助力lua脚本
-- 被邀请人（新用户）助力
local USER_RIGHTS_HELP_NUM_KEY = KEYS[1]
local USER_RIGHTS_HELP_SUCCESS_NUM = KEYS[2]

local customerId = ARGV[1]
local rightsId = ARGV[2]

-- 助力结果
local isSuccess = '0'
-- 获取权益剩余助力数量,判断任务完成
if (0==redis.call('exists', USER_RIGHTS_HELP_NUM_KEY))
then
    -- 助力数据不存在
    isSuccess = '-1'
elseif('0'== redis.call('get', USER_RIGHTS_HELP_NUM_KEY))
then
  -- 如果权益助力任务完成,本次助力失败
     isSuccess = '0'
elseif(1 == redis.call('hsetnx',USER_RIGHTS_HELP_SUCCESS_NUM,customerId,1))
then
   redis.call('decrby', USER_RIGHTS_HELP_NUM_KEY,1)
   isSuccess = '1'
end
return isSuccess
