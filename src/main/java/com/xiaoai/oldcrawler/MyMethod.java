package com.xiaoai.oldcrawler;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyMethod {
	
	/**
	 * ###1文件复制
	 * @param inpath 待复制文件的路径
	 * @param outpath 复制文件目的路径
	 * @throws Exception
	 */
	public static void fileCopy(String inpath,String outpath) throws Exception  {
		File inFile = new File(inpath);//input path file
		File outFile = new File(outpath);//output path file
		if (outFile.exists()) {
			throw new Exception("the outpath is exists!");
		}
		InputStream inStream = null;
		OutputStream outStream =null;
		try {
			inStream = new FileInputStream(inFile);
			outStream = new FileOutputStream(outFile);
			byte[] flush = new byte[1024];
			int len = -1;
			while((len=inStream.read(flush))!=-1) {
				outStream.write(flush, 0, len);
				outStream.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (outStream!=null) {
				outStream.close();
			}else if (inStream!=null) {
				inStream.close();
			} 
			System.out.println("end!");
		}
	}

	/**
	 *
	 * ###2把文件转为字节数�?
	 *  1- file to byteArray
	 *  1) file--program
	 *  2) program byteArray
	 *
	 * @param filePath 文件路径
	 * @return 文件封装后的字节数组
	 */
	public static byte[] fileToByteArray(String filePath) {
		//1-源与目的�?
		File src = new File(filePath);
		byte[] dest = null;
		//2-选择�?
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {
			is = new FileInputStream(src);
			baos = new ByteArrayOutputStream();
			//3-操作
			byte[] flush = new byte[1024*10];
			int len = -1;
			while((len = is.read(flush)) != -1) {
				baos.write(flush,0,flush.length);//写出到字节数�?
			}
			baos.flush();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (is!=null) {
					is.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	/*
	 * 字节数组转文件
	 * byteArray to file
	 * byteArray - program
	 * program -file
	 *
	 * @param src 字节数组
	 * @param filePath 存储文件路径
	 *
	 */
	public static void byteArrayToFile(byte[] src,String filePath) {
		File srcout = new File(filePath);
		OutputStream os = null;
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(src);
			//方法1-通过bytearray实现分段写入文件
			os = new FileOutputStream(srcout);
			byte[] flush = new byte[5];//缓冲容器
			int len = -1;//接收标识
			while((len=is.read(flush))!=-1) {
				os.write(flush,0,flush.length);
 			}
			//方法2-直接写入文件
			//os.write(src);
			os.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (os!=null) {
					os.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
	/**
	 * ###4关闭资源
	 * @param args 可变参数，根据传入的数据库资源参数进行相应关�?
	 * @throws SQLException
	 */
	public static void close(Object...args) throws SQLException {
		for (Object obj : args) {
			if (obj instanceof Connection) {
				Connection conn  = (Connection)obj;
				if (conn!=null && !conn.isClosed()) {
					conn.close();
				}
			}
			if (obj instanceof Statement) {
				Statement stmt  = (Statement)obj;
				if (stmt!=null && !stmt.isClosed()) {
					stmt.close();
				}
			}
			if (obj instanceof ResultSet) {
				ResultSet rs  = (ResultSet)obj;
				if (rs!=null && !rs.isClosed()) {
					rs.close();
				}
			}
		}
	}
	
	/**
	 * ###5获取网址HTMP源码
	 * @param url 网址
	 * @return 网址源码
	 * @throws Exception
	 */
	public static String getHTML(String url) throws Exception {
		String html = "";
		//获取URL
		URL realurl = new URL(url);
		//下载资源
		HttpURLConnection conn = (HttpURLConnection)realurl.openConnection();//打开连接
		conn.setRequestMethod("GET");
		//模拟浏览器
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.106 Safari/537.36");
		conn.setRequestProperty("Accept", "text/html");
		conn.setRequestProperty("Accept-Language", "en-US,en");
		//豆瓣登录cookie
		conn.setRequestProperty("Cookie","bid=Yk4ypW8bQVo; douban-fav-remind=1; ll=\"118306\"; gr_user_id=17894f36-f21c-445d-b8d3-12479412fc38; _vwo_uuid_v2=DCCE40943EED2AA72DA344B77A09ECA44|0f061358b59b5c6013ef95a9b1cb80f5; trc_cookie_storage=taboola%2520global%253Auser-id%3Ddb87277a-3863-4611-9c59-bacb91f95aaa-tuct4b69e28; __yadk_uid=ylz4KMCg1wRWRaYRQGTXCm2cEtwruiqy; viewed=\"27174859_26184193_2057310_1440345\"; douban-profile-remind=1; _ga=GA1.2.1707112651.1588649402; push_noty_num=0; push_doumail_num=0; __gads=ID=67f69293c3704656-221a43b1eec20048:T=1597407748:RT=1597407748:S=ALNI_MZHPIKFj-wyLupO7e7M17UUTVUu1Q; ct=y; __utmc=30149280; __utmz=30149280.1598263223.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmc=223695111; __utmz=223695111.1598263223.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ap_v=0,6.0; __utma=30149280.1707112651.1588649402.1598270616.1598276236.3; __utmb=30149280.0.10.1598276236; __utma=223695111.1707112651.1588649402.1598270616.1598276236.3; __utmb=223695111.0.10.1598276236; _pk_ses.100001.4cf6=*; dbcl2=\"180519015:dtHQiNDSL0c\"; ck=F3uC; _pk_id.100001.4cf6=43f5df798ab5f65e.1598263223.3.1598276893.1598274427.");

		//创建输入缓冲流保存数�?
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		String msg = null;
		while((msg=br.readLine())!=null) {
			html+=msg+"\n";
		} 
		if (br!=null) {
			br.close();
		}
		return html;
	}
	/**
	 * ###5获取网址HTMP源码
	 * @param url 网址
	 * @return 网址源码
	 * @throws Exception
	 */
	public static String getHTML(String url, String referer) throws Exception {
		String html = "";
		//获取URL
		URL realurl = new URL(url);
		//下载资源
		HttpURLConnection conn = (HttpURLConnection)realurl.openConnection();//打开连接
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Referer",referer);
		//模拟浏览器
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.106 Safari/537.36");
		conn.setRequestProperty("Accept", "text/html");
		conn.setRequestProperty("Accept-Language", "en-US,en");
		//豆瓣登录cookie
		conn.setRequestProperty("Cookie","bid=Yk4ypW8bQVo; douban-fav-remind=1; ll=\"118306\"; gr_user_id=17894f36-f21c-445d-b8d3-12479412fc38; _vwo_uuid_v2=DCCE40943EED2AA72DA344B77A09ECA44|0f061358b59b5c6013ef95a9b1cb80f5; trc_cookie_storage=taboola%2520global%253Auser-id%3Ddb87277a-3863-4611-9c59-bacb91f95aaa-tuct4b69e28; __yadk_uid=ylz4KMCg1wRWRaYRQGTXCm2cEtwruiqy; viewed=\"27174859_26184193_2057310_1440345\"; douban-profile-remind=1; _ga=GA1.2.1707112651.1588649402; push_noty_num=0; push_doumail_num=0; __gads=ID=67f69293c3704656-221a43b1eec20048:T=1597407748:RT=1597407748:S=ALNI_MZHPIKFj-wyLupO7e7M17UUTVUu1Q; ct=y; __utmc=30149280; __utmz=30149280.1598263223.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmc=223695111; __utmz=223695111.1598263223.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ap_v=0,6.0; __utma=30149280.1707112651.1588649402.1598270616.1598276236.3; __utmb=30149280.0.10.1598276236; __utma=223695111.1707112651.1588649402.1598270616.1598276236.3; __utmb=223695111.0.10.1598276236; _pk_ses.100001.4cf6=*; dbcl2=\"180519015:dtHQiNDSL0c\"; ck=F3uC; _pk_id.100001.4cf6=43f5df798ab5f65e.1598263223.3.1598276893.1598274427.");

		//创建输入缓冲流保存数�?
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"GBK"));
		String msg = null;
		while((msg=br.readLine())!=null) {
			html+=msg+"\n";
		}
		if (br!=null) {
			br.close();
		}
		return html;
	}
	
	/**
	 * 传入url保存文件
	 * @param picurl 图片路径
	 * @param savePath 保存目的路径
	 * @param picname 图片名称
	 * @throws Exception
	 */
	public static void savePic(String picurl,String savePath,String picname) throws Exception{
		URL url = new URL(picurl);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		//这个网站要模拟浏览器才行
//		conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko");
//		conn.setRequestProperty("User-Agent", "Mozilla/31.0 (compatible; MSIE 10.0; Windows NT; DigExt)"); //防止报403错误。
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.106 Safari/537.36");
		//哆啦A梦
		conn.setRequestProperty("Referer","https://manhua.dmzj.com/jiqimao/90883.shtml");
//		conn.setRequestMethod("GET");
		//打开连接
		conn.connect();
		//打开这个网站的输入流
		InputStream inStream = conn.getInputStream();
		//用这个做中转 ，把图片数据都放在了这里，再调用toByteArray()即可获得数据的byte数组
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte [] buf = new byte[1024];
		//为什么是1024  1024Byte=1KB， 这个就是循环读取，是个临时空间，多大都没关系 当然，设得小，说明I/O操作会比较频繁，I/O操作耗时比较长，
		//这多少会有点性能上的影响．这看你是想用空间换时间，还是想用时间换空间了．时间慢比内存溢出程序崩溃强．如果内存足够的话，我会考虑设大点．
		int len = 0;
		//读取图片数据
		while((len=inStream.read(buf))!=-1){
			outStream.write(buf,0,len);
		}
		inStream.close();
		outStream.close();
		if (outStream.toByteArray() != null) {
			//把图片数据填入文件中
			File dir = new File(savePath);
			if (!dir.exists()) {//目录不存在，创建目录
				dir.mkdirs();
			}
			File file = new File( dir+"//"+picname+".jpg");//picurl.substring(picurl.lastIndexOf("."),picurl.lastIndexOf(".")+4));//拼接图片路径
			FileOutputStream op = new FileOutputStream(file);
			op.write(outStream.toByteArray());//写出图片
			op.close();
		}
	}
	
	/**
	 * ###6正则匹配结果�?
	 * @param targetStr 带匹配字符串
	 * @param regex 正则表达�?
	 * @return 匹配结果�?
	 */
	public static List<String> regexResultArr(String targetStr, String regex){
		// 定义�?个样式模板，此中使用正则表达式，括号中是要抓的内�?
		// 相当于埋好了陷阱匹配的地方就会掉下去
		Pattern pattern = Pattern.compile(regex);//正则模式编译
		// 定义�?个matcher(匹配�?)用来做匹�?
		Matcher matcher = pattern.matcher(targetStr);
		// 如果找到�?
		List<String> matchStrs = new ArrayList<>();//定义数组存放匹配到的结果
		while (matcher.find()) {//匹配完一次指针下移一�?
			matchStrs.add(matcher.group());
		}
		// 返回匹配结果�?
		return matchStrs;
	}
	
	/**
	 * ###7重定向打印输出位置
	 * @param path 指定输出的路径
	 * @param isAppend 是否追加
	 * @return 
	 * @throws IOException
	 */
	public static PrintStream redirectPrint(String path ,boolean isAppend) throws IOException {
		PrintStream ps=null;
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		try {
			ps = new PrintStream(new FileOutputStream(file,isAppend));
			if (ps!=null) {
				System.setOut(ps);
				return ps;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
