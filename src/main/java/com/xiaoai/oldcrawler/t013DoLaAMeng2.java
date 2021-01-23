package com.xiaoai.oldcrawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 哆啦A梦漫画 出错使用
 * https://manhua.dmzj.com/jiqimao/
 */
public class t013DoLaAMeng2 {

	
	public static void main(String[] args) throws Exception {
		//拼接图片地址进行保存
		String pic_url = "";
		String page = "";
		for (int j = 92;j<=98;j++){
			if (j<10){
				page = "00"+j;
			}else if (j<100){
				page = "0"+j;
			}

			pic_url = "https://images.dmzj.com/j/%E6%9C%BA%E5%99%A8%E7%8C%AB/%E9%AB%98%E6%B8%8521/DlAm21-"+page+".jpg";
			System.out.println("--"+pic_url);
			MyMethod.savePic(pic_url,"D:\\SpiderPic\\DoLaAMeng","机器猫-第21卷_"+ j);

			Thread.sleep(500);
		}
	}
}
