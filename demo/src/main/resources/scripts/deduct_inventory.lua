local inventory = tonumber(redis.call('GET', KEYS[1]))

if inventory == nil then
    return 0
end

if inventory < tonumber(ARGV[1]) then
    return 0
end

redis.call(
    'DECRBY',
    KEYS[1],
    ARGV[1]
)

return 1