package com.wgz.sentiment_analysis.service;

import com.github.pagehelper.PageInfo;
import com.wgz.sentiment_analysis.vo.PageTicketVo;
import com.wgz.sentiment_analysis.vo.TicketSearchVo;

public interface TicketService {
    String createTicket(Long uid);
    PageInfo<PageTicketVo> getPageTicket(TicketSearchVo ticketSearchVo);
    String seeFile(Long id);

}
