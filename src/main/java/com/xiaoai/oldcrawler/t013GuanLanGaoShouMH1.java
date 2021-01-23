package com.xiaoai.oldcrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class t013GuanLanGaoShouMH1 {
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

		public static void main(String[] args) throws Exception
		{
			long start = System.currentTimeMillis();
			
			//
			String html = " <div class=\"chapter-body clearfix\">\r\n" + 
					"                            <ul id=\"chapter-list-4\" data-sort=\"asc\">\r\n" + 
					"                                                                    <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7020.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第01话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7047.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第02话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7048.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第03话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7049.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第04话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7074.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第05话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7093.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第06话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7113.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第07话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7128.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第08话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7146.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第09话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7160.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第10话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7167.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第11话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7175.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第12话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7183.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第13话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7190.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第14话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7198.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第15话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7207.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第16话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7215.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第17话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7223.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第18话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7231.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第19话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7239.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第20话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7246.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第21话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7253.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第22话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7260.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第23话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7268.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第24话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7276.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第25话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7284.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第26话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7290.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第27话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7296.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第28话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7303.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第29话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7310.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第30话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7322.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第31话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7330.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第32话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7338.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第33话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7345.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第34话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7352.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第35话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7360.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第36话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7368.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第37话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7376.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第38话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7383.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第39话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7390.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第40话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7398.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第41话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7406.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第42话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7416.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第43话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7426.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第44话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7434.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第45话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7441.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第46话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7451.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第47话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7459.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第48话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7466.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第49话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7474.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第50话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7482.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第51话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7493.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第52话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7505.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第53话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7514.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第54话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7526.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第55话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7533.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第56话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7540.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第57话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7547.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第58话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7557.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第59话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7565.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第60话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7573.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第61话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7582.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第62话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7596.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第63话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7605.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第64话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7614.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第65话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7622.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第66话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7634.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第67话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7642.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第68话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7651.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第69话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7658.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第70话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7671.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第71话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7679.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第72话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7686.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第73话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7694.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第74话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7704.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第75话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7712.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第76话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7722.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第77话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7730.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第78话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7741.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>第79话</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                        <li>\r\n" + 
					"                                        <!--<a href=\"javascript:void();\"-->\r\n" + 
					"                                        <a href=\"/manga/114/7749.html\"\r\n" + 
					"                                           class=\"\">\r\n" + 
					"                                            <span>十日谈</span>\r\n" + 
					"                                        </a>\r\n" + 
					"                                                                            </li>\r\n" + 
					"                                                                </ul>\r\n" + 
					"                        </div>";
			List<String> list_url = MyMethod.regexResultArr(html, "/manga/114/[0-9]{2,15}.html");
			for (int i=0;i<list_url.size();i++) {
				
				System.out.println("http://www.hanhande.net"+list_url.get(i));
				
			}
			
			
			System.out.println("\n----------end---------");
			long finish = System.currentTimeMillis();
			System.out.println("用时："+((start-finish)/1000)+"秒");
		}
	}
