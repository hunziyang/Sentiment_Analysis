package com.wgz.sentiment_analysis.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat time = new SimpleDateFormat("HHmmss");

    public static String getSimpleDateFormatTime(Date date){
        return simpleDateFormat.format(date);
    }
    public static String getTime(Date date){
        return time.format(date);
    }
}
