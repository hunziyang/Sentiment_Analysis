package com.wgz.sentiment_analysis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.wgz.sentiment_analysis.entity.Ticket;
import com.wgz.sentiment_analysis.vo.TicketVo;

import java.io.IOException;

public interface TicketService extends IService<Ticket> {

    void insertTicket(TicketVo ticketVo);

    PageInfo<Ticket> selectTicket(int PageNum, int PageSize);

    String seeTxt(int id) throws IOException;
}
