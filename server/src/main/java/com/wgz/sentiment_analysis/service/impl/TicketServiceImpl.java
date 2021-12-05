package com.wgz.sentiment_analysis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgz.sentiment_analysis.dao.TicketMapper;
import com.wgz.sentiment_analysis.entity.Ticket;
import com.wgz.sentiment_analysis.service.CacheService;
import com.wgz.sentiment_analysis.service.TicketService;
import com.wgz.sentiment_analysis.vo.TicketVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
@Slf4j
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private CacheService cacheService;
    @Value("${result.path}")
    private String resultPath;
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

    @Override
    public PageInfo<Ticket> selectTicket(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Ticket> tickets = ticketMapper.selectList(null);
        PageInfo<Ticket> pageInfo = new PageInfo<>(tickets);
        return pageInfo;
    }

    @Override
    public String seeTxt(int id) throws IOException {
        String path = resultPath+id+".txt";
        if (!new File(path).exists()){
            return "文件不存在";
        }
        StringBuffer sb  = new StringBuffer();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String str = "";
        while ((str = br.readLine())!= null){
            sb.append(str);
        }
        if (br != null){
            br.close();
        }
        return sb.toString();
    }
}
