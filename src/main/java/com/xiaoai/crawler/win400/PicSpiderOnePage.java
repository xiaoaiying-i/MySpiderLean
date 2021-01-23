package com.xiaoai.crawler.win400;

import com.xiaoai.oldcrawler.MyMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * http://www.win4000.com/
 * 获取一页图片
 * @author xiaoaiying
 *
 */
public class PicSpiderOnePage {

    private static int pagesize = 17;//获取套图数量
    private static String pagename;//套图名
    private static int picsize = 0;//图片张数
    private static int sum=0;//总数


    public static void main(String[] args) {
        long starttime = System.currentTimeMillis();

        String html=null;
        try {
            html = MyMethod.getHTML("http://www.win4000.com/meinvtag4_1.html");//一级url
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> detailURL;//套图详情url
        detailURL = MyMethod.regexResultArr(html, "http://www.win4000.com/meinv[0-9]{6,7}.html");//获得套图详情url
        //第一次循环：循环套图url数组获得图片url
        String detailurl;//套图url
        System.out.println("套图共有："+detailURL.size());
        for (int j = 0; j < detailURL.size(); j++) {
            detailurl= detailURL.get(j);
            System.out.println(j+1+"--"+detailurl);
            try {
                html = MyMethod.getHTML(detailurl);//拿到详情页源码
            } catch (Exception e) {
                e.printStackTrace();
            }
            //--------------------------获取套图张数and名称
            Pattern pat = Pattern.compile("<h1>.+?\\）");//匹配带名称和图片张数的字符串
            Matcher mat = pat.matcher(html);//例如：<h1>白皙娇嫩美女性感吊带裙诱人写真</h1>（<span>1</span>/<em>9</em>）
            if (mat.find()) {
                String onefind=mat.group();//保存待名称和图片张数的字符串
                //获取图片页数
                pat = Pattern.compile("[0-9]{1,3}");//匹配页数
                mat = pat.matcher(onefind);
                while (mat.find()){
                    picsize = Integer.parseInt(mat.group());
                }
                sum+=picsize;
                //获取套图名称
                pat = Pattern.compile("[\\u4e00-\\u9fa5]+");//匹配图片张数
                mat = pat.matcher(onefind);
                if (mat.find()) {
                    pagename = mat.group();
                }
            }
            //--------------------------获取套图张数and名称
            List<String> detailURLall = new ArrayList<String>();
            String picurl;
            for (int k = 1; k <= picsize; k++) {//获得图片详情url数组
                picurl = detailurl.substring(0,detailurl.lastIndexOf("."))+"_"+k+".html";
                detailURLall.add(picurl);
            }

            //第二次循环：循环图片详情url数组获得图片地址
            String picadd = "";
            int pici = 1;
            for (String pul : detailURLall) {
                //拿到真正的图片地址读取并保存
                System.out.println("----"+pul);
                try {
                    html = MyMethod.getHTML(pul);
                } catch (Exception e) {
                    e.printStackTrace();
                }					  //http://pic1.win4000.com/pic/8/cf/e1695d5870.jpg
                //http://pic1.win4000.com/pic/3/ff/d91b1547256.jpg
                pat = Pattern.compile("http://pic1.win4000.com/pic/.{13,20}\\.jpg");//匹配图片张数
                mat = pat.matcher(html);
                if (mat.find()) {
                    picadd = mat.group();//获取到图片地址
                }
                System.out.println("------"+pici+"-"+picadd);
                try {
                    MyMethod.savePic(picadd,"D://SpiderPic//meinv3//" , pagename+pici+"");//写入文件
                    System.out.println("------"+pici+"-"+picadd+"已写入！");
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pici++;
            }
        }
        long finishtime = System.currentTimeMillis();
        System.out.println("\n--------------------------------");
        System.out.println("用时："+((finishtime-starttime)/1000)+"秒");
        System.out.println("共抓数据："+sum);
    }
}
