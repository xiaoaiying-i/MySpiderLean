package com.xiaoai.oldcrawler;

import java.io.File;
import java.io.FileWriter;

/**
 * 哆啦A梦漫画 下载其他版本
 *
 * https://manhua.dmzj.com/jiqimao/
 */
public class t013DoLaAMeng3 {

	
	public static void main(String[] args) throws Exception {
		FileWriter fw = new FileWriter("D:\\SpiderPic\\DoLaAMeng\\a.txt",true);
		//拼接图片地址进行保存
		String url = "";
		String pic_url = "";
		String page = "";
		for (int j = 1;j<=91;j++){
			if (j<10){
				page = "0"+j;
			}else if (j<100){
				page = ""+j;
			}else{
				page = j+"";
			}
			pic_url = "https://images.dmzj.com/d/%E5%93%86%E5%95%A6A%E6%A2%A6%E6%B2%A1%E6%9C%89%E7%BC%9D%E9%9A%99%E7%9A%84%E6%8A%BD%E5%B1%89/%E5%85%A81%E5%8D%B7_1564700829/"+page+"%5B1%5D.jpg";
			System.out.println("--"+pic_url);
			fw.write(pic_url+"\n");
			MyMethod.savePic(pic_url,"D:\\SpiderPic\\DoLaAMeng\\没有缝隙的抽屉","哆啦A梦没有缝隙的抽屉_"+ (j));

			Thread.sleep(500);
		}
		fw.write("\n\n");
		fw.flush();
		fw.close();
	}
}
