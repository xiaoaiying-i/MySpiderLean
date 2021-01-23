package com.xiaoai.oldcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MySpiderTest {
	public static void main(String[] args) {
		String url = "http://www.baidu.com";
		
		String result = "";
		BufferedReader in = null;
		
		try {
			//获取真实URL
			URL realUrl = new URL(url);
			//初始化链接到那个url链接
			URLConnection connection = realUrl.openConnection();
			//开始实际链接
			connection.connect();
			//获取网页输入流
			InputStream pagein = connection.getInputStream();
			//有些网址需要模拟浏览器进行连接
//			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko");
			
			//用BufferedReader输入流来存储抓取到的数据
			in = new BufferedReader(new InputStreamReader(pagein,"UTF-8"));
			String line;
			while ((line=in.readLine())!=null) {
				result+=line+"\n";
				System.out.println(line);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("发送GET请求出现异常！"+e);
			e.printStackTrace();
		}finally {
			if (in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
