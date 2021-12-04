package com.wgz.sentiment_analysis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wgz.sentiment_analysis.entity.Ticket;
import com.wgz.sentiment_analysis.vo.TicketVo;

public interface TicketService extends IService<Ticket> {

    void insertTicket(TicketVo ticketVo);
}
