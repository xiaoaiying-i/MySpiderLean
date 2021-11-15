package com.xiaoai.crawler.win400;
import com.xiaoai.common.DateAndTimeUtil;
import com.xiaoai.common.HttpOptionUtil;
import com.xiaoai.common.RegexOptionUtil;
import com.xiaoai.defaultsetting.RequstHeadConfigration;
import com.xiaoai.oldcrawler.MyMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.win4000.com/
 * 获取一套图图片
 * @author xiaoaiying
 *
 */
public class PicSpiderOneGroup {

    static RegexOptionUtil regexOptionUtil = new RegexOptionUtil();


	private static String groupName;//套图名
	private static int picSize = 0;//图片张数
	private static int sum=0;//总数

	public static void main(String[] args) {
        Long starttime = DateAndTimeUtil.currentTimeMillis();//开始时间

        String detailurl = "http://www.win4000.com/meinv118497.html";//套图url
        String html=null; //网页源码
        try {
            html = MyMethod.getHTML(detailurl);//拿到详情页源码
        } catch (Exception e) {
            e.printStackTrace();
        }
		System.out.println("--"+detailurl);

		//--------------------------获取套图张数and名称
        String nameAndSizeStr = regexOptionUtil.regexFirstResult(html,"<h1>.+?\\）");//匹配带名称和图片张数的字符串
        picSize = Integer.parseInt(regexOptionUtil.regexLastResult(nameAndSizeStr,"[0-9]{1,3}"));//获取图片页数
        sum+=picSize;
        groupName = regexOptionUtil.regexFirstResult(nameAndSizeStr,"[\\u4e00-\\u9fa5]+");
		System.out.println("该套图共："+picSize+"张");
		//--------------------------获取套图张数and名称

		List<String> detailURLall = new ArrayList<String>();
		String picurl;
		for (int k = 1; k <= picSize; k++) {//获得图片详情url数组
			picurl = detailurl.substring(0,detailurl.lastIndexOf("."))+"_"+k+".html";
			detailURLall.add(picurl);
		}

	//第二次循环：循环图片详情url数组获得图片地址
		String picaddr = "";
		int picNamei = 1;// 图片张数标识
		for (String pul : detailURLall) {
			//拿到真正的图片地址读取并保存
			System.out.println("----"+pul);
			try {
				html = MyMethod.getHTML(pul);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//http://pic1.win4000.com/pic/8/cf/e1695d5870.jpg
            //http://pic1.win4000.com/pic/3/ff/d91b1547256.jpg
            //http://pic1.win4000.com/pic/5/b3/89101638061.jpg
            picaddr = regexOptionUtil.regexFirstResult(html,"http://pic1.win4000.com/pic/.{13,20}\\.jpg");//获取到图片地址
			System.out.println("------"+picNamei+"-"+picaddr);
			try {
				System.out.println("Thread:"+Thread.currentThread().getName());
				HttpOptionUtil.saveFile(picaddr, RequstHeadConfigration.getHeads(),
				"D://SpiderPic//meinv4//",groupName+picNamei,"jpg");//写入文件
				System.out.println("------"+picNamei+"-"+picaddr+"已写入！");
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
			picNamei++;//套图图片存放在不同文件夹（以套图名称命名文件夹），
		}

		System.out.println("\n--------------------------------");
		System.out.println(DateAndTimeUtil.spendTime(starttime));
		System.out.println("共抓数据："+sum);
	}
}
