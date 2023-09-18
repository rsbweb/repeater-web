package com.rsbweb.repeaterweb.utils;

import com.rsbweb.repeaterweb.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
@Slf4j
public class CommonUtils {

    public String getStartTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CommonConstants.START_TIME_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        Date date = new Date();
        String startTime = simpleDateFormat.format(date);
        return startTime;
    }
}
