package com.wgz.sentiment_analysis.schedule;

import com.wgz.sentiment_analysis.dao.TicketMapper;
import com.wgz.sentiment_analysis.entity.Ticket;
import com.wgz.sentiment_analysis.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
@Slf4j
public class StartTask {

    @Autowired
    private CacheService cacheService;
    @Autowired
    private TicketMapper ticketMapper;

    @Scheduled(cron = "0/10 * * * * ? ")
    public void startTask() {
        if (cacheService.waitQueueSize() > 0L && cacheService.startQueueSize() < 1L) {
            String id = cacheService.popWait();
            if (StringUtils.isEmpty(id)) {
                return;
            }
            try {
                cacheService.pushStart(id);
                Ticket ticket = ticketMapper.selectById(Long.valueOf(id));
                Process process;
                log.info(id + "----start");
                String cmd = "/usr/yang/anaconda/envs/tensorflow/bin/python /usr/yang/tensorflow/params.py "+ ticket.getText() + " " +ticket.getId();
                log.info("cmd:"+cmd);
                process = Runtime.getRuntime().exec(cmd);
                //获取进程的标准输入流
                InputStream in = process.getInputStream();
                //获取进城的错误流
                InputStream err = process.getErrorStream();
                ClogThread inClog = new ClogThread();
                inClog.setInputStream(in);
                ClogThread errClog = new ClogThread();
                errClog.setInputStream(err);
                new Thread(inClog).start();
                new Thread(errClog).start();
                process.waitFor();
                process.destroy();
                ticket.setFinish(true);
                ticketMapper.updateById(ticket);
                log.info(id + "----end");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cacheService.remove(id);
            }
        }
    }
}
