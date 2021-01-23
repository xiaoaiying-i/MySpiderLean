package com.xiaoai.oldcrawler;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://www.souutu.com/mnmm/图片网站
 * 单页的包含所有图片地址，正则匹配出所有地址并保存  主要用于保存头像，即所有图片地址都在一页上面
 * @author xiaoaiying
 *
 */
public class t011souyoutu_pageDownLoad {
	
	private static String pagename;//套图名
	private static int picsize = 0;//图片张数
	private static int sum=0;//总数
	
	public static void main(String[] args) {
		long starttime = System.currentTimeMillis();//开始时间
		String html=null;//网页源码
		//int fileAllPicNamei = 1;//保存在同一文件夹图片命名
		String detailurl = "https://www.souutu.com/touxiang/dmtx/19329.html";//套图url
		System.out.println("--"+detailurl);
		try {
			html = MyMethod.getHTML(detailurl);//拿到详情页源码
		} catch (Exception e) {
			e.printStackTrace();
		}
		//--------------------------获取套图张数and名称
		Pattern pat = Pattern.compile("<h2.+</h2>");//匹配带名称和图片张数的字符串
		Matcher mat = pat.matcher(html);//例如：<h1>白皙娇嫩美女性感吊带裙诱人写真</h1>
		if (mat.find()) {
			String onefind=mat.group();
			//获取套图名称
			pagename = mat.group().substring(onefind.indexOf(">")+1,onefind.length()-5);
		}
		List<String> detailURLall = new ArrayList<String>();
		try {
			detailURLall = MyMethod.regexResultArr(html,"https://img\\.souutu\\.com/2020/0821/[0-9]{10,20}\\.jpg\\.420\\.420\\.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//第二次循环：循环图片详情url数组获得图片地址
		int picNamei = 1;//图片编号
		for (String pul : detailURLall) {
			System.out.println("------"+picNamei+"-"+pul);
			try {
				MyMethod.savePic(pul,"D:\\SpiderPic\\OtherPic\\",pagename+picNamei);//写入文件
				System.out.println("------"+picNamei+"-"+pul+"已写入！");
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
			picNamei++;//套图图片存放在不同文件夹（以套图名称命名文件夹），
			//fileAllPicNamei++;//保存在同一文件夹图片命名
			sum++;
		}
		long finishtime = System.currentTimeMillis();
		System.out.println("\n--------------------------------");
		System.out.println("用时："+((finishtime-starttime)/1000)+"秒");
		System.out.println("共抓数据："+sum);
	}
}
