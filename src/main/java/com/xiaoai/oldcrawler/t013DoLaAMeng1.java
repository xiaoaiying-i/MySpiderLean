package com.xiaoai.oldcrawler;

import javax.naming.Name;
import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 哆啦A梦漫画
 * https://manhua.dmzj.com/jiqimao/
 */
public class t013DoLaAMeng1 {

	
	public static void main(String[] args) throws Exception {
		long starttime = System.currentTimeMillis();//开始时间
		int sum = 0;
		FileWriter fw = new FileWriter(new File("D:\\SpiderPic\\DoLaAMeng\\a_图片链接(21卷后).txt"),true);

		//准备第一级url
//		List<String> One_urls = new ArrayList<>();
		for (int i = 90916;i<=90929;i++){//90929
			int num =  dlam("https://manhua.dmzj.com/jiqimao/" + i + ".shtml#@page=1",fw);
			sum+=num;
		}

		fw.close();
		long finishtime = System.currentTimeMillis();
		System.out.println("\n--------------------------------");
		System.out.println("用时："+((finishtime-starttime)/1000)+"秒");
		System.out.println("共抓数据："+sum);
	}

	public static int dlam(String url_, FileWriter fw) throws Exception {
		int sum=0;
		String url = url_;
		//获取每一卷内容
		//----获取名称
		String html = MyMethod.getHTML(url);
//		System.out.println(html);
		System.out.println("----"+url);
		fw.write("--"+url+"\n");
		Pattern pat = Pattern.compile("第[0-9]{0,5}卷");
		Matcher mat = pat.matcher(html);
		String name="";
		if (mat.find()){
			name = mat.group();
			System.out.println("---"+name);
			fw.write("--"+name+"\n");
		}
		//-----获取页数
		pat = Pattern.compile("g_max_pic_count.*[0-9]{1,3};");
		mat = pat.matcher(html);
		String pages="";
		if (mat.find()){
			pages = mat.group();
			pages = pages.substring(pages.lastIndexOf(" ")+1,pages.lastIndexOf(";"));
			System.out.println("---"+pages+"页");
			fw.write("--"+pages+"图\n");
		}
		//拼接图片地址进行保存
		String pic_url = "";
		String page = "";
		for (int j = 1;j<=Integer.parseInt(pages);j++){
			if (j<10){
				page = "00"+j;
			}else if (j<100){
				page = "0"+j;
			}

			pic_url = "https://images.dmzj.com/j/%E6%9C%BA%E5%99%A8%E7%8C%AB/%E9%AB%98%E6%B8%85"
					+name.substring(1,name.indexOf("卷"))
					+"/DlAm"
					+name.substring(1,name.indexOf("卷"))+"-"+page+".jpg";
			System.out.println("--"+pic_url);
			fw.write(j+"----"+pic_url+"\n");
			MyMethod.savePic(pic_url,"D:\\SpiderPic\\DoLaAMeng","机器猫-"+name+"_"+ j);
			sum+=1;
			Thread.sleep(500);
		}
		fw.write("\n\n");
		fw.flush();
		return sum;
	}
}
