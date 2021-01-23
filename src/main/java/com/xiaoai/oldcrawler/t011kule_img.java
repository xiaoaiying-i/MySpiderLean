package com.xiaoai.oldcrawler;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * http://www.kole8.com/
 * 获取一套图图片,把第一张图片地址复制进来即可
 *以及循环地址时k从第一张地址.html前面的数字
 * @author xiaoaiying
 *
 */
public class t011kule_img {
	
	private static String pagename;//套图名
	private static int picsize = 0;//图片张数
	private static int sum=0;//总数
	
	public static void main(String[] args) {
		long starttime = System.currentTimeMillis();//开始时间

        //-----------------------------------------------------------------获取源码
        String html=null;//网页源码
		String detailurl = "http://www.kole8.com/html/hv20191/101843.html";//套图url
		System.out.println("--"+detailurl);
		try {
			html = MyMethod.getHTML(detailurl);//拿到详情页源码
		} catch (Exception e) {
			e.printStackTrace();
		}
//        System.out.println(html);
		//-----------------------------------------------------------------获取套图张数and名称
		Pattern pat = Pattern.compile("<title>.+</title>");//匹配带名称和图片张数的字符串
		Matcher mat = pat.matcher(html);//例如：<h1>白皙娇嫩美女性感吊带裙诱人写真</h1>
        String titleAndPage="";
        if (mat.find()) {
            titleAndPage=mat.group();
			//获取套图名称
			pagename = titleAndPage.substring(titleAndPage.indexOf(">")+1,titleAndPage.indexOf("("));
//			System.out.println(pagename);
		}
		//获取图片页数
		pat = Pattern.compile("/[0-9]{1,3}\\)");//匹配页数
		mat = pat.matcher(titleAndPage);
		if(mat.find()){
			String str_picsize = mat.group();
			picsize = Integer.parseInt(str_picsize.substring(1, str_picsize.length()-1));
			sum+=picsize;
		}
		System.out.println("标题:"+pagename+"--该套图共："+picsize+"张");
		//-----------------------------------------------------------------获取套图张数and名称
//
        //-----------------------------------------------------------------拼接图片html地址
		List<String> detailURLall = new ArrayList<String>();
		int picNum = 101843; //第一张图片的编号 ###############要改
		String picurl;
		for (int k = 0; k < picsize; k++) {//获得图片详情url数组
			picurl = detailurl.substring(0,detailurl.lastIndexOf("/")+1)+(picNum+k)+".html";
			detailURLall.add(picurl);
		}

        //-----------------------------------------------------------------循环html地址匹配图片jpg地址
	//第二次循环：循环图片详情url数组获得图片地址
		String picadd = "";
		int picNamei = 1;//图片命名:标题名称+编号  编号从1开始
		for (String pul : detailURLall) {
			//拿到真正的图片地址读取并保存
			System.out.println("----"+pul);
			try {
				html = MyMethod.getHTML(pul,pul);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//http://desktop.kole8.com/handset_desktop/desk_file-11/4/42/2019/1/20191413552219.jpg###############要改
			pat = Pattern.compile("http://desktop\\.kole8\\.com/handset_desktop/desk_file-11/4/42/2019/1/[0-9]{11,18}\\.jpg");//匹配图片张数
			mat = pat.matcher(html);
			if (mat.find()) {
				picadd = mat.group();//获取到图片地址
			}
			System.out.println("------"+picNamei+"-"+picadd);
			try {
				MyMethod.savePic(picadd,"D:\\SpiderPic\\ssyy\\",pagename+picNamei);//写入文件
				System.out.println("------"+picNamei+"-"+picadd+"已写入！");
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
			picNamei++;
		}
		long finishtime = System.currentTimeMillis();
		System.out.println("\n--------------------------------");
		System.out.println("用时："+((finishtime-starttime)/1000)+"秒");
		System.out.println("共抓数据："+sum);
	}
}
