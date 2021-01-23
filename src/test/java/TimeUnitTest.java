import org.junit.Test;

import java.sql.Date;
import java.text.DateFormat;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @Description 时间工具类测试 <BR>
 *
 * @author shenhaiwen
 *
 * @since 2017年4月13日上午9:34:05
 */
public class TimeUnitTest {

    public static void main(String[] args) throws InterruptedException {
        // 1、时间转换功能
        TimeUnit timeUnit = TimeUnit.DAYS; //单位为天
        printTime(timeUnit,1);
        timeUnit = TimeUnit.HOURS; //单位为小时
        printTime(timeUnit,1);
        timeUnit = TimeUnit.MINUTES; //单位为分钟
        printTime(timeUnit,1);
        timeUnit = TimeUnit.SECONDS; //单位为秒
        printTime(timeUnit,1);
        // 2、线程暂停操作功能
        System.out.println("Start Sleeping for 1 minutes using Thread.sleep(),Time:"+System.currentTimeMillis());
        Thread.sleep(1 * 60 * 1000);
        System.out.println("Sleeping for 1 second using TimeUnit sleep()"+System.currentTimeMillis());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Sleeping for 1 minute1 using TimeUnit sleep()"+System.currentTimeMillis());
        TimeUnit.MINUTES.sleep(1);
        System.out.println("End Sleeping for 1 minute1 using TimeUnit sleep()"+System.currentTimeMillis());
         //TimeUnit.HOURS.sleep(1);
        // TimeUnit.DAYS.sleep(1);

    }

    public static void printTime(TimeUnit timeUnit,int time){
        System.out.println("======= begin =======,input:"+time+timeUnit.name());
        System.out.println("timeUnit.toNanos:"+timeUnit.toNanos(time));
        System.out.println("timeUnit.toMicros:"+timeUnit.toMicros(time));
        System.out.println("timeUnit.toDays:"+timeUnit.toDays(time));
        System.out.println("timeUnit.toHours:"+timeUnit.toHours(time));
        System.out.println("timeUnit.toMinutes:"+timeUnit.toMinutes(time));
        System.out.println("timeUnit.toSeconds:"+timeUnit.toSeconds(time));
        System.out.println("timeUnit.toMillis:"+timeUnit.toMillis(time));
        System.out.println("1天是"+(timeUnit.convert(1, TimeUnit.DAYS))+timeUnit.name());
        System.out.println("12小时是"+(timeUnit.convert(12, TimeUnit.HOURS))+timeUnit.name());
        System.out.println("3600秒是"+(timeUnit.convert(36000, TimeUnit.MINUTES))+timeUnit.name());
        System.out.println("======= end =======");


    }


    @Test
    public void test11(){
        TimeUnit timeUnitSeconds = TimeUnit.SECONDS;

        System.out.println(timeUnitSeconds.name());
        System.out.println(timeUnitSeconds.toMillis(10));
        System.out.println(timeUnitSeconds.toSeconds(10));
        System.out.println(timeUnitSeconds.toMinutes(10));
        System.out.println(timeUnitSeconds.toHours(10));
        System.out.println(timeUnitSeconds.toDays(10));

        System.out.println(TimeUnit.MILLISECONDS.toSeconds(2000));


    }
}