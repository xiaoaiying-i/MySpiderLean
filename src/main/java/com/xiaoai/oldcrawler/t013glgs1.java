package com.xiaoai.oldcrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通过url获取每一章漫画（获取多个url）	
 * @author xiaoaiying
 *
 */
public class t013glgs1 {

	
	public static void main(String[] args) throws IOException {
		long starttime = System.currentTimeMillis();
		int sum = 0;
		
		String strHtmlUrl="http://www.hanhande.net/manga/114/7749.html";
		
		//---------保存路径
		File savePath = new File("D://spiderPic/GuanLanGaoShouMH/0-glgs-picUrl.txt");
		FileWriter fw = new FileWriter(savePath,true);
		//一、获取一级url列表
		List<String> listHtmlUrl = MyMethod.regexResultArr(strHtmlUrl, "http://www.hanhande.net/manga/114/[0-9]{3,15}.html");
		for (String oneHtmlUrl : listHtmlUrl) {
			System.out.println("----------------"+oneHtmlUrl);
			String html = null;
			//获取源码
			try {
				html = MyMethod.getHTML(oneHtmlUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//---获取标题
			Pattern pat = Pattern.compile("第[0-9]*?话");//匹配带名称和图片张数的字符串
			Matcher mat = pat.matcher(html);//例如：<h1>白皙娇嫩美女性感吊带裙诱人写真</h1>（<span>1</span>/<em>9</em>）
			String partName = "";
			while(mat.find()) {
				partName = mat.group();
			}
			//---获取文件夹路径
			pat = Pattern.compile("[0-9]{4}");//匹配带名称和图片张数的字符串
			mat = pat.matcher(oneHtmlUrl);//例如：<h1>白皙娇嫩美女性感吊带裙诱人写真</h1>（<span>1</span>/<em>9</em>）
			String numPath = "";
			if(mat.find()) {
				numPath = mat.group();
			}
			System.out.println(numPath);
			//二、获取图片最后字段列表
			List<String> list_jpg = MyMethod.regexResultArr(html, "[\\w]{10}.jpg");
			int i=1;
			partName = "z-十日谈";
			System.out.println(partName);

			fw.write("\n\n"+partName + "---------------------------------\n");
			
			for (int j=0;j<list_jpg.size()-1;j++) {
				String oneJpg = list_jpg.get(j);
				String picUrl = "http://img001.shaque.vip/images/comic/4/"+numPath+"/"+oneJpg;
				//System.out.println(picUrl);
				try {
					MyMethod.savePic(picUrl, "D://spiderPic/GuanLanGaoShouMH/", partName+i);
					System.out.println(i+"----"+picUrl+"已写入！");
					fw.write(i+"--"+picUrl+"\n");//写入文件保存路径
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				i++;
				sum+=1;//统计获取总数
			}
			fw.flush();
		}
		fw.close();
		
		
//		List<String> list_jpg = MyMethod.regexResultArr(html, "[\\w]{10}.jpg");
//		int i=1;
//		String partName = "第6话 第一个人";
//		
//		File savePath = new File("D://spiderPic/HaiZeiWngMH/0-picUrl.txt");
//		FileWriter fw = new FileWriter(savePath,true);
//		fw.write("\n\n"+partName + "---------------------------------\n");
//		
//		for (String oneJpg : list_jpg) {
//			String picUrl = "http://res.img.youzipi.net/images/2019/10/31/22/"+oneJpg+"/0";
//			//System.out.println(picUrl);
//			try {
//				MyMethod.savePic(picUrl, "D://spiderPic/HaiZeiWngMH", partName+i);
//				System.out.println(i+"----"+picUrl+"已写入！");
//				fw.write(i+"--"+picUrl+"\n");//写入文件保存路径
//				fw.flush();
//				Thread.sleep(500);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			i++;
//		}
//		fw.close();
		
		long finishtime = System.currentTimeMillis();
		System.out.println("\n--------------------------------");
		System.out.println("用时："+((finishtime-starttime)/1000)+"秒");
		System.out.println("共抓数据："+sum);
	}
}
