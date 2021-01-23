package com.xiaoai.crawler;

/**
 * @Author
 * @Date 2021-01-21 21:25
 */
import com.xiaoai.oldcrawler.MyMethod;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 豆瓣三上悠亚图片
 * @author xiaoaiying
 *
 */
public class DoubanSsyyImg {

    private static String pagename = "db_ssyy_";//套图名
    private static int picsize = 0;//图片张数
    private static int sum = 0;//总数

    public static void main(String[] args) {
        long starttime = System.currentTimeMillis();//开始时间

        //-----------------------------------------------------------------获取源码
        String html = null;//网页源码https://movie.douban.com/celebrity/1350998/photos/?type=C&start=60&sortby=like&size=a&subtype=a
        String detailurl = "https://movie.douban.com/celebrity/1350998/photos/?type=C&start=600&sortby=like&size=a&subtype=a";//套图url
        System.out.println("--" + detailurl);
        try {
            html = MyMethod.getHTML(detailurl,detailurl);//拿到详情页源码
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(html);

        //https://movie.douban.com/celebrity/1350998/photo/2500282549/
        //https://movie.douban.com/celebrity/1350998/photo/2523782564/
        //https://movie.douban.com/celebrity/1350998/photo/2563768065/
        //-----------------------------------------------------------------拼接图片html地址
        List<String> detailURLall = MyMethod.regexResultArr(html,"https://movie.douban.com/celebrity/1350998/photo/[0-9]{8,13}/");

        //-----------------------------------------------------------------循环html地址匹配图片jpg地址
        //第二次循环：循环图片详情url数组获得图片地址
        String picadd = "";
        int picNamei = 601;//图片命名:标题名称+编号  编号从1开始
        Pattern pat;
        Matcher mat;
        String upUrl="";
        for (int i=0;i<detailURLall.size()-10;i++) {
//            System.out.println("----" +detailURLall.get(i));
            if (detailURLall.get(i).equals(upUrl)){
                continue;
            }
            upUrl=detailURLall.get(i);
            //拿到真正的图片地址读取并保存
            System.out.println("----" + detailURLall.get(i));
            try {
                html = MyMethod.getHTML(detailURLall.get(i),detailURLall.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
//            System.out.println(html);
            //
            //https://img1.doubanio.com/view/photo/l/public/p2523782527.webp
            //https://img1.doubanio.com/view/photo/l/public/p2318281297.webp
            // ###############要改
            pat = Pattern.compile("https://img[0-9]{0,5}\\.doubanio\\.com/view/photo/l/public/p[0-9]{8,13}\\.jpg");//匹配图片张数
            mat = pat.matcher(html);
            if (mat.find()) {
                picadd = mat.group();//获取到图片地址
            }
            System.out.println("------" + picNamei + "-" + picadd);
            try {
                MyMethod.savePic(picadd, "D:\\SpiderPic\\ssyy\\", pagename + picNamei);//写入文件
                System.out.println("------" + picNamei + "-" + picadd + "已写入！");
                Thread.sleep(1300);
            } catch (Exception e) {
                e.printStackTrace();
            }
            picNamei++;
        }
        long finishtime = System.currentTimeMillis();
        System.out.println("\n--------------------------------");
        System.out.println("用时：" + ((finishtime - starttime) / 1000) + "秒");
        System.out.println("共抓数据：" + picNamei);
    }


    @Test
    public void test() throws Exception {
        MyMethod.savePic("https://img9.doubanio.com/view/photo/l/public/p2260456984.webp","D:\\SpiderPic\\ssyy\\","xxx");
    }
}
