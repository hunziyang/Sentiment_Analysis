package com.wgz.sentiment_analysis.controller;

import com.wgz.sentiment_analysis.service.TicketService;
import com.wgz.sentiment_analysis.utils.result.Result;
import com.wgz.sentiment_analysis.vo.TicketVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * (Ticket)表控制层
 *
 * @author makejava
 * @since 2021-12-04 16:38:30
 */
@RestController
@Slf4j
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/tickets")
    public Result insertTicket(@RequestBody TicketVo ticketVo) {
        log.info("message:", ticketVo.getText());
        ticketService.insertTicket(ticketVo);
        return Result.success();
    }

    @GetMapping("/tickets")
    public Result selectTicket(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        return Result.success(ticketService.selectTicket(pageNum, pageSize));
    }

    @GetMapping("/see")
    public Result seeTxt(@RequestParam("id") int id) throws IOException {
        return Result.success(ticketService.seeTxt(id));
    }
}

