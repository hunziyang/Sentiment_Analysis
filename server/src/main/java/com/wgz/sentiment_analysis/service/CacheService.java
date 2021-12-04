package com.wgz.sentiment_analysis.service;

public interface CacheService {
    void pushWaitQueue(String id);

    Long waitQueueSize();

    Long startQueueSize();

    String popWait();

    void pushStart(String id);

    void remove(String id);
}
