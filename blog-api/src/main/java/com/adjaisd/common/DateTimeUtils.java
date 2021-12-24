package com.adjaisd.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateTimeUtils {
    static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;

    public static Date getCurrentDate(){
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }
    public static Long DateToLong(Date date) {
        return date.getTime() / 1000; // 返回毫秒数
    }

    public static Date LongToDate(long cur) throws ParseException {
        Date date = new Date(cur * 1000);
        String datetime = sdf.format(date);
        return sdf.parse(datetime);
    }
}
