package com.xiaoai.regex;

import com.xiaoai.common.*;
import com.xiaoai.defaultsetting.RequstHeadConfigration;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author
 * @Date 2021-02-21 17:31
 */
public class SingerImg {
    public static void main(String[] args) throws IOException {
        Long aLong = DateAndTimeUtil.currentTimeMillis();

        // 获取文件流
        InputStream is = RegexCurriculimHM.class
                .getClassLoader()
                .getResourceAsStream("singerImg.txt");
//        System.out.println(is);
        BufferedReader bfr = new BufferedReader(new InputStreamReader(is));
        String line = "";
//        System.out.println(bfr.readLine());

        String url="";
        String singerName = "";
        while (bfr.read() != -1){
            line = bfr.readLine();
//            System.out.println(line);

            //匹配img链接
            url = RegexOptionUtil.regexFirstResult(line,"http[s]{0,1}://.*?jpg");
            System.out.print(url);

            // 匹配名称
            singerName = RegexOptionUtil.regexFirstResult(line,"\"\">.*?<");
            singerName = singerName.substring(3,singerName.length()-1);
            System.out.println(singerName);
//            break;
            //保存img
            try {
                HttpOptionUtil.saveFile(url,
                        RequstHeadConfigration.getHeads(),
                        "D:\\CurriculumNeeds\\4-1毕设\\my\\myDemo\\singerImg\\",
                        singerName,
                        "jpg"
                );
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        bfr.close();
        is.close();
        System.out.println(DateAndTimeUtil.spendTime(aLong));
    }


    @Test
    public void test(){
        try {
            HttpOptionUtil.saveFile(
                    "http://m701.music.126.net/20210221193618/49c5a9549c28d9010c96d78ad914fee7/jdymusic/obj/wo3DlMOGwrbDjj7DisKw/4680568491/0d96/3d9a/2d50/ac0bcb0463c9d03053873eca1a2bd9ea.mp3",
                    RequstHeadConfigration.getHeads(),
                    "D:\\CurriculumNeeds\\4-1毕设\\my\\myDemo\\",
                    "不屑完美",
                    "mp3"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
