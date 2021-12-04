package com.wgz.sentiment_analysis.schedule;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
@Slf4j
public class ClogThread implements Runnable {
    @Setter
    private InputStream inputStream;
    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line != null){
                }
            }
        } catch (IOException e) {
            log.error("py err:",e);
        }
        finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
