package com.wgz.sentiment_analysis.controller;

import com.wgz.sentiment_analysis.service.TicketService;
import com.wgz.sentiment_analysis.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @GetMapping("/create")
    public Result createTicket(@RequestParam("uid") Long uid){
        return Result.success(ticketService.createTicket(uid));
    }
}
