package com.wgz.sentiment_analysis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wgz.sentiment_analysis.entity.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketMapper extends BaseMapper<Ticket> {
}