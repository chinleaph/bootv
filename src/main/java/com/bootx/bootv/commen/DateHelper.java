package com.bootx.bootv.commen;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public static String getDate(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
//        ParsePosition pos = new ParsePosition(8);
//        Date currentTime_2 = formatter.parse(dateString, pos);
        return dateString;
    }
}
