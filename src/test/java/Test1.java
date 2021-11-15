import com.xiaoai.common.DataBaSeUtil;
import com.xiaoai.common.DateAndTimeUtil;
import com.xiaoai.common.FileOptionUtil;
import com.xiaoai.common.RegexOptionUtil;
import org.junit.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author
 * @Date 2021-01-18 23:45
 */
public class Test1 {

    @Test
    public void test1(){
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());
//        System.out.println(date.getDate());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String format = sdf.format(date);
        System.out.println(format);

    }

    @Test
    public void test2(){
        String date = DateAndTimeUtil.formatDate(System.currentTimeMillis());
        System.out.println(date);
        System.out.println(DateAndTimeUtil.formatDate(new Date()));
        System.out.println(DateAndTimeUtil.formatDate(1231212312332l));
        System.out.println(DateAndTimeUtil.formatDate());
        System.out.println(DateAndTimeUtil.formatDate("yyyy-MM-dd hh-mm-ss"));
    }

    @Test
    public void test3() throws InterruptedException {
        long l = System.currentTimeMillis();
        Thread.sleep(3000);

        System.out.println(DateAndTimeUtil.spendSecond(l)+"秒");
    }

    @Test
    public void testMyTime() throws InterruptedException {
        long l = System.currentTimeMillis();
        Thread.sleep(133*1000);

        System.out.println(DateAndTimeUtil.spendTime(l));
    }


    @Test
    public void testEncode(){
        long l = System.currentTimeMillis();
        System.out.println(l);
        System.out.println(TimeUnit.MILLISECONDS.toSeconds(60));
        System.out.println(TimeUnit.MINUTES.toSeconds(1));
        System.out.println("nihao你好！！");
    }


    @Test
    public void testRegex(){
//        System.out.println(RegexOptionUtil.regexSingleResult("12asf2adf43","[0-9]{1,2}"));
//        System.out.println(RegexOptionUtil.regexLastResult("12asf2adf43","[0-9]{1,2}"));
    }

    @Test
    public void filetest() {
        File file = new File("D:\\tem2\\1716040507符金辉（405班级网站）");
        FileOptionUtil fileOptionUtil = new FileOptionUtil();
        fileOptionUtil.dirErgodic(file);
    }

    @Test
    public void filedirtest() {
        File file = new File("D:\\tem2\\1716040507符金辉（405班级网站）"); //D:\Program_my\IntelliJ IDEA 2017.3.4
        FileOptionUtil fileOptionUtil = new FileOptionUtil();
        fileOptionUtil.printDir(file,0);
    }

    @Test
    public void testDB(){
        System.out.println(DataBaSeUtil.getConn());
    }

}
