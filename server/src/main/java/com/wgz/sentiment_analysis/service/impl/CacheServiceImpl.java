package com.wgz.sentiment_analysis.service.impl;

import com.wgz.sentiment_analysis.enums.RedisKey;
import com.wgz.sentiment_analysis.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void pushWaitQueue(String id) {
        redisTemplate.opsForList().leftPush(RedisKey.WAIT.getValue(), id);
    }

    @Override
    public Long waitQueueSize() {
        return redisTemplate.opsForList().size(RedisKey.WAIT.getValue());

    }

    @Override
    public Long startQueueSize() {
        return redisTemplate.opsForList().size(RedisKey.START.getValue());
    }

    @Override
    public String popWait() {
        return (String) redisTemplate.opsForList().rightPop(RedisKey.WAIT.getValue());
    }

    @Override
    public void pushStart(String id) {
        redisTemplate.opsForList().leftPush(RedisKey.START.getValue(),id);
    }

    @Override
    public void remove(String id) {
        redisTemplate.opsForList().remove(RedisKey.START.getValue(),0,id);
    }
}
