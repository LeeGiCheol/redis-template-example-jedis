package me.gicheol.springdataredisexample.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;


@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String key, Serializable object) {
        redisTemplate.opsForValue().set(key, object);
    }

    public <T> T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

}
