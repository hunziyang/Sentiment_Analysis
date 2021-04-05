package com.wgz.sentiment_analysis.utils;

import com.wgz.sentiment_analysis.enums.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class RedisSequence {
    @Autowired
    private RedisTemplate redisTemplate;

    public String getTicket() {
        return TimeUtil.getTime(new Date()) + getSuffix(String.valueOf(getID()));
    }

    private String getSuffix(String id) {
        while (id.length() < 5) {
            id = "0" + id;
        }
        return id;
    }

    private long getID() {
        RedisAtomicLong redisAtomicLong = new RedisAtomicLong(RedisKey.TICKET.getValue(), redisTemplate.getConnectionFactory());
        long increment = redisAtomicLong.getAndIncrement() + 1l;
        if (increment == 1l) {
            redisAtomicLong.expireAt(getExpireTime());
        }
        return increment;
    }

    private Date getExpireTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }
}
