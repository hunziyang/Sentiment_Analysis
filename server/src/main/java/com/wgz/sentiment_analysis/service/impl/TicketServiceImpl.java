package com.wgz.sentiment_analysis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wgz.sentiment_analysis.dao.TicketMapper;
import com.wgz.sentiment_analysis.entity.Ticket;
import com.wgz.sentiment_analysis.service.CacheService;
import com.wgz.sentiment_analysis.service.TicketService;
import com.wgz.sentiment_analysis.vo.TicketVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private CacheService cacheService;
    @Override
    public void insertTicket(TicketVo ticketVo) {
        Ticket ticket = new Ticket();
        ticket.setFinish(false);
        ticket.setResult(false);
        BeanUtils.copyProperties(ticketVo,ticket);
        ticketMapper.insert(ticket);
        log.info(ticket.getId().toString());
        cacheService.pushWaitQueue(ticket.getId().toString());
    }
}
