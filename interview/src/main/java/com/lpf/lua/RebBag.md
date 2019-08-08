#### 红包雨需求
1. 用户ID:userId, 红包ID:redBagId
2. 抢红包雨的相关需求  
    - 红包雨详情：redis的列表结构存储，预先将红包金额塞入其中
    - 红包雨名单：redis的有序集合结构存储，后续需展示红包金额前N名
    - 抢红包雨限制：redis的Hash结构存储，单用户一次红包雨最多抢多1个红包
3. Redis的相关结构和Key的设计  
 
|  | 红包雨详情 | 红包雨名单 | 抢红包雨限制 |
| :------: | :------: | :------: | :------: |
| 结构 | 列表<br>List<金额> | 有序集合<br>SortedSet <金额, uid> | Hash集合<br>Hash<uid, 'uid-第N次抢'> |
| key | RedBagBatch:${redBagId} | RedBagBatch:${redBagId}:Users | RedBagBatch:${redBagId}:Limit |


下面开始具体的表演 
1. 红包id为：7758521，用户id分别为：u1、u3、u3、u4、u5 
2. redis的key分别为：RedBagBatch:7758521、RedBagBatch:7758521:Users、RedBagBatch:7758521:Limit   
3. 给redBagId=7758521的红包，初始化进去10个红包。红包金额为1-10，随机顺序。以下为redis-cli的截图    
```
## 为方便演示，以下为redis客户端使用命令行操作记录
## step1：初始化红包数据
127.0.0.1:6379> lpush RedBagBatch:7758521 1 3 10 6 8 7 2 5 4 9
(integer) 10
127.0.0.1:6379> lrange RedBagBatch:7758521 0 -1
 1) "9"
 2) "4"
 3) "5"
 4) "2"
 5) "7"
 6) "8"
 7) "6"
 8) "10"
 9) "3"
10) "1"
```    
```
## step2：使用lua脚本抢红包，模拟用户抢夺情况 
[root@vm01 learn_lua]# redis-cli -a 123456 --eval RedBagBatchGrab.lua RedBagBatch:7758521:Limit RedBagBatch:7758521 RedBagBatch:7758521:Users , u1 
"9"
[root@vm01 learn_lua]# redis-cli -a 123456 --eval RedBagBatchGrab.lua RedBagBatch:7758521:Limit RedBagBatch:7758521 RedBagBatch:7758521:Users , u2
"4"
[root@vm01 learn_lua]# redis-cli -a 123456 --eval RedBagBatchGrab.lua RedBagBatch:7758521:Limit RedBagBatch:7758521 RedBagBatch:7758521:Users , u3 
"5"
[root@vm01 learn_lua]# redis-cli -a 123456 --eval RedBagBatchGrab.lua RedBagBatch:7758521:Limit RedBagBatch:7758521 RedBagBatch:7758521:Users , u4
"2"
[root@vm01 learn_lua]# redis-cli -a 123456 --eval RedBagBatchGrab.lua RedBagBatch:7758521:Limit RedBagBatch:7758521 RedBagBatch:7758521:Users , u5
"7"
[root@vm01 learn_lua]# redis-cli -a 123456 --eval RedBagBatchGrab.lua RedBagBatch:7758521:Limit RedBagBatch:7758521 RedBagBatch:7758521:Users , u1 
"8"
[root@vm01 learn_lua]# redis-cli -a 123456 --eval RedBagBatchGrab.lua RedBagBatch:7758521:Limit RedBagBatch:7758521 RedBagBatch:7758521:Users , u2 
"6"
[root@vm01 learn_lua]# redis-cli -a 123456 --eval RedBagBatchGrab.lua RedBagBatch:7758521:Limit RedBagBatch:7758521 RedBagBatch:7758521:Users , u1 
"10"
[root@vm01 learn_lua]# redis-cli -a 123456 --eval RedBagBatchGrab.lua RedBagBatch:7758521:Limit RedBagBatch:7758521 RedBagBatch:7758521:Users , u1 
"-1"
[root@vm01 learn_lua]# redis-cli -a 123456 --eval RedBagBatchGrab.lua RedBagBatch:7758521:Limit RedBagBatch:7758521 RedBagBatch:7758521:Users , u2 
"3"
[root@vm01 learn_lua]# redis-cli -a 123456 --eval RedBagBatchGrab.lua RedBagBatch:7758521:Limit RedBagBatch:7758521 RedBagBatch:7758521:Users , u2 
"-1"
[root@vm01 learn_lua]# redis-cli -a 123456 --eval RedBagBatchGrab.lua RedBagBatch:7758521:Limit RedBagBatch:7758521 RedBagBatch:7758521:Users , u3
"1"
```  

```
## step3.查看红包雨排行榜，按红包金额倒序（奇数行为：value，偶数行为：score）
## value解读：用户-本轮次红包第N次抢夺
## score解读：红包金额
127.0.0.1:6379> zrevrange RedBagBatch:7758521:Users 0 -1 WITHSCORES 
 1) "u1-3"
 2) "10"
 3) "u1-1"
 4) "9"
 5) "u1-2"
 6) "8"
 7) "u5-1"
 8) "7"
 9) "u2-2"
10) "6"
11) "u3-1"
12) "5"
13) "u2-1"
14) "4"
15) "u2-3"
16) "3"
17) "u4-1"
18) "2"
19) "u3-2"
20) "1" 
```
  
  
 lua脚本如下：
 ```lua
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
```
