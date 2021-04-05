package com.wgz.sentiment_analysis.service.impl;

import com.wgz.sentiment_analysis.dao.UploadMapper;
import com.wgz.sentiment_analysis.entity.Upload;
import com.wgz.sentiment_analysis.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private UploadMapper uploadMapper;

    @Override
    public Long add(String name,String path) {
        Upload upload = new Upload();
        upload.setName(name);
        upload.setPath(path);
        uploadMapper.insert(upload);
        return upload.getId();
    }
}
