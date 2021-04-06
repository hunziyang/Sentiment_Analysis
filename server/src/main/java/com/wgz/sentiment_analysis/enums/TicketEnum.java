package com.wgz.sentiment_analysis.enums;

import java.util.Arrays;

public enum TicketEnum {
    WAIT(0,"WAIT","WAIT"),
    OTHER(1,"OTHER","OTHER");
    private int id;
    private String key;
    private String value;

    TicketEnum(int id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getValueByID(int id){
       return Arrays.stream(TicketEnum.values()).filter(status->status.getId()==id).findFirst().map(status-> status.getValue()).orElse(OTHER.getValue());
    }
}
