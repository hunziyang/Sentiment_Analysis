package com.wgz.sentiment_analysis.service.impl;

import com.wgz.sentiment_analysis.dao.TicketMapper;
import com.wgz.sentiment_analysis.entity.Ticket;
import com.wgz.sentiment_analysis.enums.TicketEnum;
import com.wgz.sentiment_analysis.service.TicketService;
import com.wgz.sentiment_analysis.utils.RedisSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private RedisSequence redisSequence;
    @Autowired
    private TicketMapper ticketMapper;
    @Override
    public String createTicket(Long uid) {
        String ticketStr = redisSequence.getTicket();
        Ticket ticket = new Ticket();
        ticket.setTicket(ticketStr);
        ticket.setUid(uid);
        ticket.setStatus(TicketEnum.WAIT.getId());
        ticketMapper.insert(ticket);
        return ticketStr;
    }
}
