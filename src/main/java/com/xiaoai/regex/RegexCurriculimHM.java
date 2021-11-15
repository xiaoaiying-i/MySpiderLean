package com.xiaoai.regex;

import com.xiaoai.common.RegexOptionUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 筛选课程
 * @Author
 * @Date 2021-02-08 12:36
 */
public class RegexCurriculimHM {
    
    public static void main(String[] args) throws IOException {
        InputStream is = RegexCurriculimHM.class.getClassLoader().getResourceAsStream("curriculimHM.txt");
        System.out.println(is);
        BufferedReader bfr = new BufferedReader(new InputStreamReader(is));
        String line = "";
        while (bfr.read() != -1){
            line = bfr.readLine();
//            System.out.println(line);
            System.out.println(
                    "https:"+RegexOptionUtil.regexFirstResult(line,"//www.bilibili.com/video/av[0-9]{0,}")+"\t"+
                    RegexOptionUtil.regexFirstResult(line,">.{0,}[\\u4e00-\\u9fa5]+.*?")
                    );


        }

        bfr.close();
        is.close();
    }
}
