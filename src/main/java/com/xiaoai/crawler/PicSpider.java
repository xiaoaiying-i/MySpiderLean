package com.xiaoai.crawler;

/**
 * @Author
 * @Date 2021-01-21 20:42
 */

import com.xiaoai.common.DateAndTimeUtil;
import com.xiaoai.common.HttpOptionUtil;
import com.xiaoai.common.RegexOptionUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;


/**
 * http://www.win4000.com/meinv198195.html 单页照片
 *
 * @Author
 * @Date 2021-01-21 21:24 :
 */
public class PicSpider {

    static RegexOptionUtil regexOptionUtil = new RegexOptionUtil();

    public static void main(String[] args) throws Exception {
        spider();
    }

    public static void spider() throws Exception {
        long start = DateAndTimeUtil.currentTimeMillis();

        // 定义即将访问的链接
        String url = "http://www.win4000.com/meinv198195.html";
        String regex = "https{0,}:.+?\\.jpg";

        HashMap<String, String> heads = new HashMap<>();
        heads.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.106 Safari/537.36");

        // 访问链接并获取页面内容
        String result = HttpOptionUtil.getHTML(url,heads);
        // 使用正则匹配图片的src内容
        List<String> hrefList = regexOptionUtil.regexResultArr(result, regex);
        int i = 1;

        for (String picurl : hrefList) {
            System.out.println(i+"--"+picurl);
            try {
                HttpOptionUtil.savePic(picurl, heads,
                        "D://tem2//xx", i+"","jpg");
                System.out.println("----"+picurl+"已写入！");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
        System.out.println("\n----------end---------");
        System.out.println("用时："+DateAndTimeUtil.spendSecond(start)+"秒");
    }

    @Test
    public void t(){
        System.out.println("年后");
    }
}

