package com.wgz.sentiment_analysis.config;

import com.wgz.sentiment_analysis.utils.result.Result;
import com.wgz.sentiment_analysis.utils.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandle {
    @ExceptionHandler(Exception.class)
    public Result err(Exception e){
        log.error("err:",e);
        return Result.error(ResultCode.FAILED);
    }
}
