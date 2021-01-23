package com.xiaoai.oldcrawler;

import java.util.ArrayList;
import java.util.List;

public class t011SpiderVdoTest {
	public static void main(String[] args) {//img[0-9]{1,}\\.imgtn\\.bdimg\\.com/it/
		String url = "http://image.baidu.com/search/index?tn=baiduimage&ct=201326592&lm=-1&cl=2&ie=gb18030&word=%D5%D4%DE%C8%BB%B6&fr=ala&ala=1&alatpl=star&pos=0&hs=2&xthttps=000000";
		String html = "";
		String regex = "https?://.{1,300}?\\.jpg";
		try {
			html = MyMethod.getHTML(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(html);
		int i=1;//条数
		int namei = 1;//图片名
		int sum = 0;
		List<String> picList = new ArrayList<String>();
		List<String> reList = MyMethod.regexResultArr(html, regex);
		for (String picurl : reList) {
			System.out.println(i+"--"+picurl);
//			picList.add("http:"+picurl);
			try {
//				MyMethod.savePic(picurl, "D://SpiderPic//zhaoyihuan//", namei+"");
				System.out.println("-----"+picurl+"已写入");
				sum+=1;
				namei++;
			} catch (Exception e) {
				e.printStackTrace();
			}
			i++;
		}
//		//下载
//		for (String picadd : picList) {
//			System.out.println(i+"--"+picadd);
//			try {
////				MyMethod.savePic(picadd, "D://SpiderPic//zhaosilu//", namei+"");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			namei++;
//		}
		
		System.out.println("-----------------------------");
		System.out.println("共抓："+sum);
	}

}
