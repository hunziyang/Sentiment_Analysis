package com.wgz.sentiment_analysis.enums;

public enum RedisKey {
    TICKET(1,"ticket","ticket");

    private int id;
    private String key;
    private String value;

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    RedisKey(int id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }
}
