package com.wgz.sentiment_analysis.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_ticket")
public class Ticket {
    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    /**
     * TICKET
     */
    @TableField(value = "TICKET")
    private String ticket;
    /**
     * UID
     */
    @TableField(value = "UID")
    private Long uid;
    /**
     * STATUS
     */
    @TableField(value = "STATUS")
    private Integer status;
    /**
     * ISDONE
     */
    @TableField(value = "ISDONE")
    private Boolean isDone;
    /**
     * END_TIME
     */
    @TableField(value = "END_TIME")
    private Date endTime;
    /**
     * 乐观锁
     */
    @TableField(value = "REVISION")
    private Integer revision;
    /**
     * 创建人
     */
    @TableField(value = "CREATED_BY")
    private String createdBy;
    /**
     * 创建时间
     */
    @TableField(value = "CREATED_TIME", fill = FieldFill.INSERT)
    private Date createdTime;
    /**
     * 更新人
     */
    @TableField(value = "UPDATED_BY")
    private String updatedBy;
    /**
     * 更新时间
     */
    @TableField(value = "UPDATED_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;
}