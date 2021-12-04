package com.wgz.sentiment_analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SentimentAnalysisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentimentAnalysisApplication.class, args);
    }
}
