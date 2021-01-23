package com.xiaoai.oldcrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ???url???????????????????url??	??????
 * @author xiaoaiying
 *
 */
public class t013qigongzhu1 {

	
	public static void main(String[] args) throws IOException {
		long starttime = System.currentTimeMillis();
		int sum = 0;
		
		String strHtmlUrl="http://www.hanhande.net/manga/840/84817.html";
		
		//---------????·??
		File savePath = new File("D://spiderPic/QiGongZhuMH/0-qgz-picUrl.txt");
		FileWriter fw = new FileWriter(savePath,true);
		//?????????url?б?
		List<String> listHtmlUrl = MyMethod.regexResultArr(strHtmlUrl, "http://www.hanhande.net/manga/840/[0-9]{3,15}.html");
		for (String oneHtmlUrl : listHtmlUrl) {
			System.out.println("----------------"+oneHtmlUrl);
			String html = null;
			//??????
			try {
				html = MyMethod.getHTML(oneHtmlUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//---???????
			Pattern pat = Pattern.compile("??[0-9]*???");//??????????????????????
			Matcher mat = pat.matcher(html);//???磺<h1>??????????е????????д??</h1>??<span>1</span>/<em>9</em>??
			String partName = "";
			while(mat.find()) {
				partName = mat.group();
			}
			//????????????????б?
			List<String> list_jpg = MyMethod.regexResultArr(html, "[\\w]{10}.jpg");
			int i=1;
			partName = "??????";
			System.out.println(partName);

			fw.write("\n\n"+partName + "---------------------------------\n");
			
			for (int j=0;j<list_jpg.size();j++) {
				String oneJpg = list_jpg.get(j);
				String picUrl = "http://res.img.youzipi.net/2017/08/22/23/"+oneJpg;
				//System.out.println(picUrl);
				try {
					MyMethod.savePic(picUrl, "D://spiderPic/QiGongZhuMH/", partName+i);
					System.out.println(i+"----"+picUrl+"??д??");
					fw.write(i+"--"+picUrl+"\n");//д?????????·??
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				i++;
				sum+=1;//?????????
			}
			fw.flush();
		}
		fw.close();
		
		
//		List<String> list_jpg = MyMethod.regexResultArr(html, "[\\w]{10}.jpg");
//		int i=1;
//		String partName = "??6?? ???????";
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
//				System.out.println(i+"----"+picUrl+"??д??");
//				fw.write(i+"--"+picUrl+"\n");//д?????????·??
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
		System.out.println("?????"+((finishtime-starttime)/1000)+"??");
		System.out.println("????????"+sum);
	}
}
