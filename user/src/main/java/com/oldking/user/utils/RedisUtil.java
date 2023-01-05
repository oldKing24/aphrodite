package com.oldking.user.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author wangzhiyong
 */
@Component
public class RedisUtil {
    public static final String SEND_CODE_KEY = "S_C_%s";
    @Autowired
    public RedisTemplate redisTemplate;

    public <V> void set(String key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public <V> void set(String key, V value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public <T> T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }
}
