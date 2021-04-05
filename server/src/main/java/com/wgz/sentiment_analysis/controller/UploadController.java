package com.wgz.sentiment_analysis.controller;

import com.wgz.sentiment_analysis.service.UploadService;
import com.wgz.sentiment_analysis.utils.TimeUtil;
import com.wgz.sentiment_analysis.utils.result.Result;
import com.wgz.sentiment_analysis.utils.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {
    @Value("${upload.prefixPath}")
    private String prefixPath;

    @Autowired
    private UploadService uploadService;

    @PostMapping("/checkFile")
    public Result upload(MultipartFile file) {
        Date date = new Date();
        String path = prefixPath + TimeUtil.getSimpleDateFormatTime(date) + "/" + TimeUtil.getTime(date) + "/" + file.getOriginalFilename();
        if (!new File(path).getParentFile().exists()) {
            try {
                FileUtils.forceMkdir(new File(path).getParentFile());
            } catch (IOException e) {
                log.error("uploadForceMkdirErr:" + e.getMessage());
                return Result.error(ResultCode.FAILED);
            }
        }
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            log.error("uploadErr:" + e.getMessage());
            return Result.error(ResultCode.FAILED);
        }
        Long uid = uploadService.add(file.getOriginalFilename(), path);
        return Result.success(uid);
    }
}
