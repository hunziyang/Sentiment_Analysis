package com.wgz.sentiment_analysis.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 *  (Ticket)实体类
 *
 * @author makejava
 * @since 2021-12-04 16:38:30
 */
@Data
@TableName("ticket")
public class Ticket implements Serializable {
    private static final long serialVersionUID = -11570397254556683L;
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 文本
     */
    private String text;
    /**
     * 结果
     */
    private Boolean result;
    /**
     * 完成
     */
    private Boolean finish;
    /**
     * 乐观锁
     */
    private Integer revision;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    @TableField(value = "CREATED_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    /**
     * 更新人
     */
    private String updatedBy;
    /**
     * 更新时间
     */
    @TableField(value = "UPDATED_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
}

