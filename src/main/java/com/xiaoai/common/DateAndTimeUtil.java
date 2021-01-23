package com.xiaoai.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author
 * @Date 2021-01-18 23:34
 */
public class DateAndTimeUtil {

    private static final String DATEPATTERN = "yyyy-MM-dd HH:mm:ss";


    /**
     * 当前时间毫秒数
     * @return
     */
    public static Long currentTimeMillis(){
        return System.currentTimeMillis();
    }

    /**
     * 花费秒数
     * @param startTimeMillis 开始时的毫秒数
     * @return
     */
    public static Long spendSecond(Long startTimeMillis){
        return ((System.currentTimeMillis()-startTimeMillis)/1000);
    }

    /**
     * 花费秒数
     * @param startTimeMillis 开始时的毫秒数
     * @return
     */
    public static String spendTime(Long startTimeMillis){
        int days = 0;
        int hour = 0;
        int minute = 0;
        int seconde = 0;
        Long passTimeMillis = System.currentTimeMillis() - startTimeMillis;
        TimeUnit timeUnitMillSeconds = TimeUnit.MILLISECONDS;
        days = (int)timeUnitMillSeconds.toDays(passTimeMillis);
        hour = (int) timeUnitMillSeconds.toHours(passTimeMillis - TimeUnit.DAYS.toMillis(days));
        minute = (int) timeUnitMillSeconds.toMinutes(passTimeMillis -TimeUnit.HOURS.toMillis(hour));
        seconde = (int)timeUnitMillSeconds.toSeconds(passTimeMillis-TimeUnit.MINUTES.toMillis(minute));

        return "About time：" + days + " day " + pZone(hour) + ":" + pZone(minute) + ":" + pZone(seconde);
    }

    /**
     * 小于10在前补0
     * @param num
     * @return
     */
    public static String pZone (int num) {
        return num<10 ? ("0"+num) : (num+"");
    }


    /**
     * 格式化时间
     */
    public static String formatDate(){
        return formatDate(System.currentTimeMillis(),DATEPATTERN);
    }

    public static String formatDate(Long TimeMillis){
        return formatDate(TimeMillis,DATEPATTERN);
    }

    public static String formatDate(String format){
        return formatDate(System.currentTimeMillis(),format);
    }

    public static String formatDate(Long TimeMillis,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(TimeMillis);
    }

    public static String formatDate(Date date){
        return formatDate(date,DATEPATTERN);
    }

    public static String formatDate(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }




}
