package com.wgz.sentiment_analysis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wgz.sentiment_analysis.entity.Upload;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadMapper extends BaseMapper<Upload> {
    Upload getFilePath(@Param("id") Long id);
}