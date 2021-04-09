package com.wgz.sentiment_analysis.vo;

import lombok.Data;

import java.util.Date;

@Data
public class PageTicketVo {
    private Long id;
    private String ticket;
    private Long uid;
    private Date createdTime;
    private Integer status;
    private Boolean isDone;
    private Date endTime;
}
