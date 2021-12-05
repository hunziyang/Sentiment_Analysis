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

import java.io.*;

@Component
@Slf4j
public class StartTask {

    @Autowired
    private CacheService cacheService;
    @Autowired
    private TicketMapper ticketMapper;
    @Value("${py.path}")
    private String pyPath;
    @Value("${py.file.path}")
    private String pyFilePath;

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
                String cmd = pyPath + " " + pyFilePath + " " + ticket.getText() + " " + ticket.getId();
                log.info("cmd:" + cmd);
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
                Ticket t = new Ticket();
                t.setId(ticket.getId());
                t.setFinish(true);
                ticketMapper.updateById(t);
                log.info(id + "----end");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cacheService.remove(id);
            }
        }
    }
}
