package com.wgz.sentiment_analysis.config;

import com.wgz.sentiment_analysis.utils.result.Result;
import com.wgz.sentiment_analysis.utils.result.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(Exception.class)
    public Result err(){
        return Result.error(ResultCode.FAILED);
    }
}
