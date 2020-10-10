package com.sdz.love.bamboos.commons.config.service.impl;


import com.sdz.love.bamboos.commons.config.service.RedisService;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author mds
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private ValueOperations<String, Object> valueOperations;
    @Resource
    private HashOperations<String, String, Object> hashOperations;
    @Resource
    private ListOperations<String, Object> listOperations;
    @Resource
    private SetOperations<String, Object> setOperations;
    @Resource
    private ZSetOperations<String, Object> zSetOperations;
    private String key;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key 缓存的键值
     * @param value 缓存的值
     * @param timeout 时间
     * @param timeUnit 时间颗粒度
     * @return 缓存的对象
     */
    @Override
    public <T> ValueOperations<String, T> setCacheObject(String key, T value, Integer timeout, TimeUnit timeUnit)
    {
        ValueOperations<String, T > operation = (ValueOperations<String, T>) redisTemplate.opsForValue();
        operation.set(key, value, timeout, timeUnit);
        return operation;
    }
    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    @Override
    public <T> T getCacheObject(String key)
    {
        ValueOperations<String, T> operation = (ValueOperations<String, T>)redisTemplate.opsForValue();
        return operation.get(key);
    }

    /** -------------------key相关操作--------------------- */

    /**
     * 删除key
     *
     * @param key
     */
    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量删除key
     *
     * @param keys
     */
    @Override
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 序列化key
     *
     * @param key
     * @return
     */
    @Override
    public byte[] dump(String key) {
        return redisTemplate.dump(key);
    }

    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    @Override
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param date
     * @return
     */
    @Override
    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    /**
     * 查找匹配的key
     *
     * @param pattern
     * @return
     */
    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 将当前数据库的 key 移动到给定的数据库 db 当中
     *
     * @param key
     * @param dbIndex
     * @return
     */
    @Override
    public Boolean move(String key, int dbIndex) {
        return redisTemplate.move(key, dbIndex);
    }

    /**
     * 移除 key 的过期时间，key 将持久保持
     *
     * @param key
     * @return
     */
    @Override
    public Boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    /**
     * 返回 key 的剩余的过期时间
     *
     * @param key
     * @param unit
     * @return
     */
    @Override
    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    /**
     * 返回 key 的剩余的过期时间
     *
     * @param key
     * @return
     */
    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 从当前数据库中随机返回一个 key
     *
     * @return
     */
    @Override
    public String randomKey() {
        return redisTemplate.randomKey();
    }

    /**
     * 修改 key 的名称
     *
     * @param oldKey
     * @param newKey
     */
    @Override
    public void rename(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * 仅当 newkey 不存在时，将 oldKey 改名为 newkey
     *
     * @param oldKey
     * @param newKey
     * @return
     */
    @Override
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 返回 key 所储存的值的类型
     *
     * @param key
     * @return
     */
    @Override
    public DataType type(String key) {
        return redisTemplate.type(key);
    }

    /** -------------------string相关操作--------------------- */

    /**
     * 设置指定 key 的值
     *
     * @param key
     * @param value
     */
    @Override
    public void set(String key, String value) {
        valueOperations.set(key, value);
    }

    /**
     * 获取指定 key 的值
     *
     * @param key
     * @return
     */
    @Override
    public Object get(String key) {
        return valueOperations.get(key);
    }

    /**
     * 返回 key 中字符串值的子字符
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public Object getRange(String key, long start, long end) {
        return valueOperations.get(key, start, end);
    }

    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Object getAndSet(String key, String value) {
        return valueOperations.getAndSet(key, value);
    }

    /**
     * 对 key 所储存的字符串值，获取指定偏移量上的位(bit)
     *
     * @param key
     * @param offset
     * @return
     */
    @Override
    public Boolean getBit(String key, long offset) {
        return valueOperations.getBit(key, offset);
    }

    /**
     * 批量获取
     *
     * @param keys
     * @return
     */
    @Override
    public List<Object> multiGet(Collection<String> keys) {
        return valueOperations.multiGet(keys);
    }

    /**
     * 设置ASCII码, 字符串'a'的ASCII码是97, 转为二进制是'01100001', 此方法是将二进制第offset位值变为value
     *
     * @param key
     * @param offset 位置
     * @param value  值,true为1, false为0
     * @return
     */
    @Override
    public boolean setBit(String key, long offset, boolean value) {
        return valueOperations.setBit(key, offset, value);
    }

    /**
     * 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
     *
     * @param key
     * @param value
     * @param timeout 过期时间
     * @param unit    时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES
     *                秒:TimeUnit.SECONDS 毫秒:TimeUnit.MILLISECONDS
     */
    @Override
    public void setEx(String key, String value, long timeout, TimeUnit unit) {
        valueOperations.set(key, value, timeout, unit);
    }

    /**
     * 只有在 key 不存在时设置 key 的值
     *
     * @param key
     * @param value
     * @return 之前已经存在返回false, 不存在返回true
     */
    @Override
    public boolean setIfAbsent(String key, String value) {
        return valueOperations.setIfAbsent(key, value);
    }

    /**
     * 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始
     *
     * @param key
     * @param value
     * @param offset 从指定位置开始覆写
     */
    @Override
    public void setRange(String key, String value, long offset) {
        valueOperations.set(key, value, offset);
    }

    /**
     * 获取字符串的长度
     *
     * @param key
     * @return
     */
    @Override
    public Long size(String key) {
        return valueOperations.size(key);
    }

    /**
     * 批量添加
     *
     * @param maps
     */
    @Override
    public void multiSet(Map<String, String> maps) {
        valueOperations.multiSet(maps);
    }

    /**
     * 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在
     *
     * @param maps
     * @return 之前已经存在返回false, 不存在返回true
     */
    @Override
    public boolean multiSetIfAbsent(Map<String, String> maps) {
        return valueOperations.multiSetIfAbsent(maps);
    }

    /**
     * 增加(自增长), 负数则为自减
     *
     * @param key
     * @param increment
     * @return
     */
    @Override
    public Long incrBy(String key, long increment) {
        return valueOperations.increment(key, increment);
    }

    /**
     * @param key
     * @param increment
     * @return
     */
    @Override
    public Double incrByFloat(String key, double increment) {
        return valueOperations.increment(key, increment);
    }

    /**
     * 追加到末尾
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Integer append(String key, String value) {
        return valueOperations.append(key, value);
    }

    /** -------------------hash相关操作------------------------- */

    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key
     * @param field
     * @return
     */
    @Override
    public Object hGet(String key, String field) {
        return hashOperations.get(key, field);
    }

    /**
     * 获取所有给定字段的值
     *
     * @param key
     * @return
     */
    @Override
    public Map<String, Object> hGetAll(String key) {
        return hashOperations.entries(key);
    }

    /**
     * 获取所有给定字段的值
     *
     * @param key
     * @param fields
     * @return
     */
    @Override
    public List<Object> hMultiGet(String key, List<String> fields) {
        return hashOperations.multiGet(key, fields);
    }

    @Override
    public void hPut(String key, String hashKey, String value) {
        hashOperations.put(key, hashKey, value);
    }

    @Override
    public void hPutAll(String key, Map<String, String> maps) {
        hashOperations.putAll(key, maps);
    }

    /**
     * 仅当hashKey不存在时才设置
     *
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    @Override
    public Boolean hPutIfAbsent(String key, String hashKey, String value) {
        return hashOperations.putIfAbsent(key, hashKey, value);
    }

    /**
     * 删除一个或多个哈希表字段
     *
     * @param key
     * @param fields
     * @return
     */
    @Override
    public Long hDelete(String key, Object... fields) {
        return hashOperations.delete(key, fields);
    }

    /**
     * 查看哈希表 key 中，指定的字段是否存在
     *
     * @param key
     * @param field
     * @return
     */
    @Override
    public boolean hExists(String key, String field) {
        return hashOperations.hasKey(key, field);
    }



    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     *
     * @param key
     * @param field
     * @param increment
     * @return
     */
    @Override
    public Long hIncrBy(String key, String field, long increment) {
        return hashOperations.increment(key, field, increment);
    }

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     *
     * @param key
     * @param field
     * @param delta
     * @return
     */
    public Double hIncrByFloat(String key, String field, double delta) {
        return hashOperations.increment(key, field, delta);
    }

    /**
     * 获取所有哈希表中的字段
     *
     * @param key
     * @return
     */
    @Override
    public Set<String> hKeys(String key) {
        return hashOperations.keys(key);
    }

    /**
     * 获取哈希表中字段的数量
     *
     * @param key
     * @return
     */
    @Override
    public Long hSize(String key) {
        return hashOperations.size(key);
    }

    /**
     * 获取哈希表中所有值
     *
     * @param key
     * @return
     */
    @Override
    public List<Object> hValues(String key) {
        return hashOperations.values(key);
    }

    /**
     * 迭代哈希表中的键值对
     *
     * @param key
     * @param options
     * @return
     */
    @Override
    public Cursor<Map.Entry<String, Object>> hScan(String key, ScanOptions options) {
        return hashOperations.scan(key, options);
    }

    /** ------------------------list相关操作---------------------------- */

    /**
     * 通过索引获取列表中的元素
     *
     * @param key
     * @param index
     * @return
     */
    @Override
    public Object lIndex(String key, long index) {
        return listOperations.index(key, index);
    }

    /**
     * 获取列表指定范围内的元素
     *
     * @param key
     * @param start 开始位置, 0是开始位置
     * @param end   结束位置, -1返回所有
     * @return
     */
    @Override
    public List<Object> lRange(String key, long start, long end) {
        return listOperations.range(key, start, end);
    }

    /**
     * 存储在list头部
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long lLeftPush(String key, String value) {
        return listOperations.leftPush(key, value);
    }

    /**
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long lLeftPushAll(String key, String... value) {
        return listOperations.leftPushAll(key, value);
    }

    /**
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long lLeftPushAll(String key, Collection<String> value) {
        return listOperations.leftPushAll(key, value);
    }

    /**
     * 当list存在的时候才加入
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long lLeftPushIfPresent(String key, String value) {
        return listOperations.leftPushIfPresent(key, value);
    }

    /**
     * 如果pivot存在,再pivot前面添加
     *
     * @param key
     * @param pivot
     * @param value
     * @return
     */
    @Override
    public Long lLeftPush(String key, String pivot, String value) {
        return listOperations.leftPush(key, pivot, value);
    }

    /**
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long lRightPush(String key, String value) {
        return listOperations.rightPush(key, value);
    }

    /**
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long lRightPushAll(String key, String... value) {
        return listOperations.rightPushAll(key, value);
    }

    /**
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long lRightPushAll(String key, Collection<String> value) {
        return listOperations.rightPushAll(key, value);
    }

    /**
     * 为已存在的列表添加值
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long lRightPushIfPresent(String key, String value) {
        return listOperations.rightPushIfPresent(key, value);
    }

    /**
     * 在pivot元素的右边添加值
     *
     * @param key
     * @param pivot
     * @param value
     * @return
     */
    @Override
    public Long lRightPush(String key, String pivot, String value) {
        return listOperations.rightPush(key, pivot, value);
    }

    /**
     * 通过索引设置列表元素的值
     *
     * @param key
     * @param index 位置
     * @param value
     */
    @Override
    public void lSet(String key, long index, String value) {
        listOperations.set(key, index, value);
    }

    /**
     * 移出并获取列表的第一个元素
     *
     * @param key
     * @return 删除的元素
     */
    @Override
    public Object lLeftPop(String key) {
        return listOperations.leftPop(key);
    }

    /**
     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     * @param key
     * @param timeout 等待时间
     * @param unit    时间单位
     * @return
     */
    @Override
    public Object lBLeftPop(String key, long timeout, TimeUnit unit) {
        return listOperations.leftPop(key, timeout, unit);
    }

    /**
     * 移除并获取列表最后一个元素
     *
     * @param key
     * @return 删除的元素
     */
    @Override
    public Object lRightPop(String key) {
        return listOperations.rightPop(key);
    }

    /**
     * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     * @param key
     * @param timeout 等待时间
     * @param unit    时间单位
     * @return
     */
    @Override
    public Object lBRightPop(String key, long timeout, TimeUnit unit) {
        return listOperations.rightPop(key, timeout, unit);
    }

    /**
     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     *
     * @param sourceKey
     * @param destinationKey
     * @return
     */
    @Override
    public Object lRightPopAndLeftPush(String sourceKey, String destinationKey) {
        return listOperations.rightPopAndLeftPush(sourceKey,
                destinationKey);
    }

    /**
     * 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     *
     * @param sourceKey
     * @param destinationKey
     * @param timeout
     * @param unit
     * @return
     */
    @Override
    public Object lBRightPopAndLeftPush(String sourceKey, String destinationKey,
                                        long timeout, TimeUnit unit) {
        return listOperations.rightPopAndLeftPush(sourceKey,
                destinationKey, timeout, unit);
    }

    /**
     * 删除集合中值等于value得元素
     *
     * @param key
     * @param index index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素;
     *              index<0, 从尾部开始删除第一个值等于value的元素;
     * @param value
     * @return
     */
    @Override
    public Long lRemove(String key, long index, String value) {
        return listOperations.remove(key, index, value);
    }

    /**
     * 裁剪list
     *
     * @param key
     * @param start
     * @param end
     */
    @Override
    public void lTrim(String key, long start, long end) {
        listOperations.trim(key, start, end);
    }

    /**
     * 获取列表长度
     *
     * @param key
     * @return
     */
    @Override
    public Long lLen(String key) {
        this.key = key;
        return listOperations.size(key);
    }

    /** --------------------set相关操作-------------------------- */

    /**
     * set添加元素
     *
     * @param key
     * @param values
     * @return
     */
    @Override
    public Long sAdd(String key, String... values) {
        return setOperations.add(key, values);
    }

    /**
     * set移除元素
     *
     * @param key
     * @param values
     * @return
     */
    @Override
    public Long sRemove(String key, Object... values) {
        return setOperations.remove(key, values);
    }

    /**
     * 移除并返回集合的一个随机元素
     *
     * @param key
     * @return
     */
    @Override
    public Object sPop(String key) {
        return setOperations.pop(key);
    }

    /**
     * 将元素value从一个集合移到另一个集合
     *
     * @param key
     * @param value
     * @param destKey
     * @return
     */
    @Override
    public Boolean sMove(String key, String value, String destKey) {
        return setOperations.move(key, value, destKey);
    }

    /**
     * 获取集合的大小
     *
     * @param key
     * @return
     */
    @Override
    public Long sSize(String key) {
        return setOperations.size(key);
    }

    /**
     * 判断集合是否包含value
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Boolean sIsMember(String key, Object value) {
        return setOperations.isMember(key, value);
    }

    /**
     * 获取两个集合的交集
     *
     * @param key
     * @param otherKey
     * @return
     */
    @Override
    public Set<Object> sIntersect(String key, String otherKey) {
        return setOperations.intersect(key, otherKey);
    }

    /**
     * 获取key集合与多个集合的交集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    @Override
    public Set<Object> sIntersect(String key, Collection<String> otherKeys) {
        return setOperations.intersect(key, otherKeys);
    }

    /**
     * key集合与otherKey集合的交集存储到destKey集合中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    @Override
    public Long sIntersectAndStore(String key, String otherKey, String destKey) {
        return setOperations.intersectAndStore(key, otherKey,
                destKey);
    }

    /**
     * key集合与多个集合的交集存储到destKey集合中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    @Override
    public Long sIntersectAndStore(String key, Collection<String> otherKeys,
                                   String destKey) {
        return setOperations.intersectAndStore(key, otherKeys,
                destKey);
    }

    /**
     * 获取两个集合的并集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    @Override
    public Set<Object> sUnion(String key, String otherKeys) {
        return setOperations.union(key, otherKeys);
    }

    /**
     * 获取key集合与多个集合的并集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    @Override
    public Set<Object> sUnion(String key, Collection<String> otherKeys) {
        return setOperations.union(key, otherKeys);
    }

    /**
     * key集合与otherKey集合的并集存储到destKey中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    @Override
    public Long sUnionAndStore(String key, String otherKey, String destKey) {
        return setOperations.unionAndStore(key, otherKey, destKey);
    }

    /**
     * key集合与多个集合的并集存储到destKey中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    @Override
    public Long sUnionAndStore(String key, Collection<String> otherKeys,
                               String destKey) {
        return setOperations.unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取两个集合的差集
     *
     * @param key
     * @param otherKey
     * @return
     */
    @Override
    public Set<Object> sDifference(String key, String otherKey) {
        return setOperations.difference(key, otherKey);
    }

    /**
     * 获取key集合与多个集合的差集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    @Override
    public Set<Object> sDifference(String key, Collection<String> otherKeys) {
        return setOperations.difference(key, otherKeys);
    }

    /**
     * key集合与otherKey集合的差集存储到destKey中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    @Override
    public Long sDifference(String key, String otherKey, String destKey) {
        return setOperations.differenceAndStore(key, otherKey,
                destKey);
    }

    /**
     * key集合与多个集合的差集存储到destKey中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    @Override
    public Long sDifference(String key, Collection<String> otherKeys,
                            String destKey) {
        return setOperations.differenceAndStore(key, otherKeys,
                destKey);
    }

    /**
     * 获取集合所有元素
     *
     * @param key
     * @return
     */
    @Override
    public Set<Object> setMembers(String key) {
        return setOperations.members(key);
    }

    /**
     * 随机获取集合中的一个元素
     *
     * @param key
     * @return
     */
    @Override
    public Object sRandomMember(String key) {
        return setOperations.randomMember(key);
    }

    /**
     * 随机获取集合中count个元素
     *
     * @param key
     * @param count
     * @return
     */
    @Override
    public List<Object> sRandomMembers(String key, long count) {
        return setOperations.randomMembers(key, count);
    }

    /**
     * 随机获取集合中count个元素并且去除重复的
     *
     * @param key
     * @param count
     * @return
     */
    @Override
    public Set<Object> sDistinctRandomMembers(String key, long count) {
        return setOperations.distinctRandomMembers(key, count);
    }

    /**
     * @param key
     * @param options
     * @return
     */
    @Override
    public Cursor<Object> sScan(String key, ScanOptions options) {
        return setOperations.scan(key, options);
    }

    /**------------------zSet相关操作--------------------------------*/

    /**
     * 添加元素,有序集合是按照元素的score值由小到大排列
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    @Override
    public Boolean zAdd(String key, String value, double score) {
        return zSetOperations.add(key, value, score);
    }

    /**
     * @param key
     * @param values
     * @return
     */
    @Override
    public Long zAdd(String key, Set<ZSetOperations.TypedTuple<Object>> values) {
        return zSetOperations.add(key, values);
    }

    /**
     * @param key
     * @param values
     * @return
     */
    @Override
    public Long zRemove(String key, Object... values) {
        return zSetOperations.remove(key, values);
    }

    /**
     * 增加元素的score值，并返回增加后的值
     *
     * @param key
     * @param value
     * @param delta
     * @return
     */
    @Override
    public Double zIncrementScore(String key, String value, double delta) {
        return zSetOperations.incrementScore(key, value, delta);
    }

    /**
     * 返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
     *
     * @param key
     * @param value
     * @return 0表示第一位
     */
    @Override
    public Long zRank(String key, Object value) {
        return zSetOperations.rank(key, value);
    }

    /**
     * 返回元素在集合的排名,按元素的score值由大到小排列
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long zReverseRank(String key, Object value) {
        return zSetOperations.reverseRank(key, value);
    }

    /**
     * 获取集合的元素, 从小到大排序
     *
     * @param key
     * @param start 开始位置
     * @param end   结束位置, -1查询所有
     * @return
     */
    @Override
    public Set<Object> zRange(String key, long start, long end) {
        return zSetOperations.range(key, start, end);
    }

    /**
     * 获取集合元素, 并且把score值也获取
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public Set<ZSetOperations.TypedTuple<Object>> zRangeWithScores(String key, long start,
                                                                   long end) {
        return zSetOperations.rangeWithScores(key, start, end);
    }

    /**
     * 根据Score值查询集合元素
     *
     * @param key
     * @param min 最小值
     * @param max 最大值
     * @return
     */
    @Override
    public Set<Object> zRangeByScore(String key, double min, double max) {
        return zSetOperations.rangeByScore(key, min, max);
    }

    /**
     * 根据Score值查询集合元素, 从小到大排序
     *
     * @param key
     * @param min 最小值
     * @param max 最大值
     * @return
     */
    @Override
    public Set<ZSetOperations.TypedTuple<Object>> zRangeByScoreWithScores(String key,
                                                                          double min, double max) {
        return zSetOperations.rangeByScoreWithScores(key, min, max);
    }

    /**
     * @param key
     * @param min
     * @param max
     * @param start
     * @param end
     * @return
     */
    @Override
    public Set<ZSetOperations.TypedTuple<Object>> zRangeByScoreWithScores(String key,
                                                                          double min, double max, long start, long end) {
        return zSetOperations.rangeByScoreWithScores(key, min, max,
                start, end);
    }

    /**
     * 获取集合的元素, 从大到小排序
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public Set<Object> zReverseRange(String key, long start, long end) {
        return zSetOperations.reverseRange(key, start, end);
    }

    /**
     * 获取集合的元素, 从大到小排序, 并返回score值
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public Set<ZSetOperations.TypedTuple<Object>> zReverseRangeWithScores(String key,
                                                                          long start, long end) {
        return zSetOperations.reverseRangeWithScores(key, start,
                end);
    }

    /**
     * 根据Score值查询集合元素, 从大到小排序
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    @Override
    public Set<Object> zReverseRangeByScore(String key, double min,
                                            double max) {
        return zSetOperations.reverseRangeByScore(key, min, max);
    }

    /**
     * 根据Score值查询集合元素, 从大到小排序
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    @Override
    public Set<ZSetOperations.TypedTuple<Object>> zReverseRangeByScoreWithScores(
            String key, double min, double max) {
        return zSetOperations.reverseRangeByScoreWithScores(key,
                min, max);
    }

    /**
     * @param key
     * @param min
     * @param max
     * @param start
     * @param end
     * @return
     */
    @Override
    public Set<Object> zReverseRangeByScore(String key, double min,
                                            double max, long start, long end) {
        return zSetOperations.reverseRangeByScore(key, min, max,
                start, end);
    }

    /**
     * 根据score值获取集合元素数量
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    @Override
    public Long zCount(String key, double min, double max) {
        return zSetOperations.count(key, min, max);
    }

    /**
     * 获取集合大小
     *
     * @param key
     * @return
     */
    @Override
    public Long zSize(String key) {
        return zSetOperations.size(key);
    }

    /**
     * 获取集合大小
     *
     * @param key
     * @return
     */
    @Override
    public Long zZCard(String key) {
        return zSetOperations.zCard(key);
    }

    /**
     * 获取集合中value元素的score值
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Double zScore(String key, Object value) {
        return zSetOperations.score(key, value);
    }

    /**
     * 移除指定索引位置的成员
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    @Override
    public Long zRemoveRange(String key, long start, long end) {
        return zSetOperations.removeRange(key, start, end);
    }

    /**
     * 根据指定的score值的范围来移除成员
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    @Override
    public Long zRemoveRangeByScore(String key, double min, double max) {
        return zSetOperations.removeRangeByScore(key, min, max);
    }

    /**
     * 获取key和otherKey的并集并存储在destKey中
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    @Override
    public Long zUnionAndStore(String key, String otherKey, String destKey) {
        return zSetOperations.unionAndStore(key, otherKey, destKey);
    }

    /**
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    @Override
    public Long zUnionAndStore(String key, Collection<String> otherKeys,
                               String destKey) {
        return zSetOperations
                .unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 交集
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    @Override
    public Long zIntersectAndStore(String key, String otherKey,
                                   String destKey) {
        return zSetOperations.intersectAndStore(key, otherKey,
                destKey);
    }

    /**
     * 交集
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    @Override
    public Long zIntersectAndStore(String key, Collection<String> otherKeys,
                                   String destKey) {
        return zSetOperations.intersectAndStore(key, otherKeys,
                destKey);
    }

    /**
     * @param key
     * @param options
     * @return
     */
    @Override
    public Cursor<ZSetOperations.TypedTuple<Object>> zScan(String key, ScanOptions options) {
        return zSetOperations.scan(key, options);
    }
}
