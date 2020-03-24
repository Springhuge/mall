package com.jihu.mall.tiny.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redis操作Service实现类
 * 对象和数据都以json形式进行存储
 */
@Service("redisService")
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 存储数据
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 获取数据
     * @param key
     * @return
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


    /**
     * 设置超期时间
     * @param key
     * @param expire
     * @return
     */
    public boolean expire(String key, long expire) {
        return redisTemplate.expire(key,expire,TimeUnit.SECONDS);
    }

    /**
     * 删除数据
     * @param key
     */
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 自增操作
     * @param key
     * @param delta 自增步长
     * @return
     */
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }
}
