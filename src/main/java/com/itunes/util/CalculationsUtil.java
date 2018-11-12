package com.itunes.util;

import com.itunes.model.TimeValue;

public class CalculationsUtil {

    public static int parseTime(long timeMillis, TimeValue timeValue) {
        if(timeValue.equals(TimeValue.SECONDS)) {
            return (int) (timeMillis / 1000) % 60;
        }
        if(timeValue.equals(TimeValue.MINUTES)) {
            return (int) ((timeMillis / (1000*60)) % 60);
        }
        if(timeValue.equals(TimeValue.HOURS)) {
            return (int) ((timeMillis / (1000*60*60)) % 24);
        }
        return 0;
    }
}
