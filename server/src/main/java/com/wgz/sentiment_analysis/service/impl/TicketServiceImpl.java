package com.wgz.sentiment_analysis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgz.sentiment_analysis.dao.TicketMapper;
import com.wgz.sentiment_analysis.dao.UploadMapper;
import com.wgz.sentiment_analysis.entity.Ticket;
import com.wgz.sentiment_analysis.entity.Upload;
import com.wgz.sentiment_analysis.enums.TicketEnum;
import com.wgz.sentiment_analysis.service.TicketService;
import com.wgz.sentiment_analysis.utils.RedisSequence;
import com.wgz.sentiment_analysis.vo.PageTicketVo;
import com.wgz.sentiment_analysis.vo.TicketSearchVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {
    @Autowired
    private RedisSequence redisSequence;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private UploadMapper uploadMapper;

    @Override
    public String createTicket(Long uid) {
        String ticketStr = redisSequence.getTicket();
        Ticket ticket = new Ticket();
        ticket.setTicket(ticketStr);
        ticket.setUid(uid);
        ticket.setStatus(TicketEnum.WAIT.getId());
        ticket.setIsDone(false);
        ticketMapper.insert(ticket);
        return ticketStr;
    }

    @Override
    public PageInfo<PageTicketVo> getPageTicket(TicketSearchVo ticketSearchVo) {
        List<PageTicketVo> pageTicketVoList = new ArrayList<>();
        LambdaQueryWrapper<Ticket> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(ticketSearchVo.getStartTime() != null, Ticket::getCreatedTime, ticketSearchVo.getStartTime())
                .le(ticketSearchVo.getEndTime() != null, Ticket::getCreatedTime, ticketSearchVo.getEndTime())
                .like(!StringUtils.isBlank(ticketSearchVo.getTicket()), Ticket::getTicket, ticketSearchVo.getTicket());
        Page page = PageHelper.startPage(ticketSearchVo.getPageNum(), ticketSearchVo.getPageSize());
        List<Ticket> ticketList = ticketMapper.selectList(queryWrapper);
        ticketList.forEach(ticket -> {
            PageTicketVo ticketVo = new PageTicketVo();
            BeanUtils.copyProperties(ticket, ticketVo);
            pageTicketVoList.add(ticketVo);
        });
        PageInfo<PageTicketVo> pageInfo = new PageInfo<>(pageTicketVoList);
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public String seeFile(Long id) {
        Upload upload = uploadMapper.getFilePath(id);
        String path = upload.getPath();
        StringBuffer sb = new StringBuffer();
        if (!new File(path).exists()) {
            return "无法找到对应文件";
        }
        try {
            List<String> file = IOUtils.readLines(new FileReader(path));
            file.forEach(str -> sb.append(str).append("\r\n"));
        } catch (IOException e) {
            log.error("readFileErr:" + e.getMessage());
            return "无法找到对应文件";
        }
        return sb.toString();
    }
}
