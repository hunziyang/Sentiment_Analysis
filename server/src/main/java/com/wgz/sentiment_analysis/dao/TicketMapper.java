package com.wgz.sentiment_analysis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wgz.sentiment_analysis.entity.Ticket;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  (Ticket)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-04 16:38:30
 */
@Repository
public interface TicketMapper extends BaseMapper<Ticket> {
}

