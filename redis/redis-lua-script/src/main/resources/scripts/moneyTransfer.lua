--moneyTransfer.lua
local account = 'account'
local fromBalance = tonumber(redis.call('HGET', account, KEYS[1]))
local toBalance = tonumber(redis.call('HGET', account, KEYS[2]))
local amount = tonumber(ARGV[1])
if fromBalance >= amount
then
    redis.call('HSET', account, KEYS[1], fromBalance - amount)
    redis.call('HSET', account, KEYS[2], toBalance + amount)
    return true
end
return false