package com.wgz.sentiment_analysis.controller;

import com.wgz.sentiment_analysis.service.TicketService;
import com.wgz.sentiment_analysis.utils.result.Result;
import com.wgz.sentiment_analysis.utils.result.ResultCode;
import com.wgz.sentiment_analysis.vo.TicketSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/create")
    public Result createTicket(@RequestParam("uid") Long uid) {
        return Result.success(ticketService.createTicket(uid));
    }

    @PostMapping("/search")
    public Result searchTicket(@RequestBody TicketSearchVo ticketSearchVo) {
        if (ticketSearchVo.getPageSize() == 0 || ticketSearchVo.getPageNum() == 0) {
            return Result.error(ResultCode.PARAM_IS_INVALID);
        }
        return Result.success(ticketService.getPageTicket(ticketSearchVo));
    }

    @PostMapping("/seeFile/{id}")
    public Result seeFile(@PathVariable("id") Long id) {
        return Result.success(ticketService.seeFile(id));
    }
}
