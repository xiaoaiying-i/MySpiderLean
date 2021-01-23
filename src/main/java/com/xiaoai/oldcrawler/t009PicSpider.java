package com.xiaoai.oldcrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class t009PicSpider {
		static String SendGet(String url)
		{
			// 定义一个字符串用来存储网页内容
			String result = "";
			// 定义一个缓冲字符输入流
			BufferedReader in = null;
			try
			{
				// 将string转成url对象
				URL realUrl = new URL(url);
				// 初始化一个链接到那个url的连接
				URLConnection connection = realUrl.openConnection();
				// 开始实际的连接
				connection.connect();
				
				// 初始化 BufferedReader输入流来读取URL的响应
				in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
				
				// 用来临时存储抓取到的每一行的数据
				String line;
				while ((line = in.readLine()) != null)
				{
					// 遍历抓取到的每一行并将其存储到result里面
					result += line+"\n";
				}
			} catch (Exception e)
			{
				System.out.println("发送GET请求出现异常！" + e);
				e.printStackTrace();
			}
			// 使用finally来关闭输入流
			finally
			{
				try
				{
					if (in != null)
					{
						in.close();
					}
				} catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}
			return result;
		}

		public static List<String> RegexResultArr(String targetStr, String regular){
			// 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
			// 相当于埋好了陷阱匹配的地方就会掉下去
			Pattern pattern = Pattern.compile(regular);//正则模式编译
			// 定义一个matcher(匹配器)用来做匹配
			Matcher matcher = pattern.matcher(targetStr);
			// 如果找到了
			List<String> matchStrs = new ArrayList<>();//定义数组存放匹配到的结果
			while (matcher.find()) {//匹配完一次指针下移一次
				matchStrs.add(matcher.group());
			}
			// 返回匹配结果集
			return matchStrs;
		}

		public static void main(String[] args)
		{
			long start = System.currentTimeMillis();
			System.out.println(start);
			// 定义即将访问的链接
			String url = "http://www.win4000.com/meinv198195.html";
			// 访问链接并获取页面内容
			String result = SendGet(url);
//			System.out.println(result);
			// 使用正则匹配图片的src内容
			List<String> hrefList = RegexResultArr(result, "https{0,}:.+?\\.jpg");
			int i = 1;
			for (String picurl : hrefList) {
				System.out.println(i+"--"+picurl);
				try {
					MyMethod.savePic(picurl, "D://spiderPic", i+"");
					System.out.println("----"+picurl+"已写入！");
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				i++;
			}
			System.out.println("\n----------end---------");
			long finish = System.currentTimeMillis();
			System.out.println("用时："+((start-finish)/1000)+"秒");
		}
	}
