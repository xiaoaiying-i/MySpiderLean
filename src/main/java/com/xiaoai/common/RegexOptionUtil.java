package com.xiaoai.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式操作工具类
 * @Author xiaoaiying
 * @Date 2021-01-21 00:11
 */
public class RegexOptionUtil {

    private Pattern pattern; // 定义正则样式模板，编译正则表达式
    private Matcher matcher; // 创建一个匹配器，把模板传入

    /**
     * 正则匹配第一个结果返回
     * @param targetStr
     * @param regex
     * @return
     */
    public  String regexFirstResult(String targetStr, String regex){
        pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(targetStr);
        if (matcher.find()) {
            return matcher.group(); // 获取结果
        }
        return null;
    }

    /**
     * 正则匹配最后一个结果返回
     * @param targetStr
     * @param regex
     * @return
     */
    public  String regexLastResult(String targetStr, String regex){
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(targetStr);
        String res = null;
        while (matcher.find()){
            res = matcher.group();
        }
        return res;
    }

    /**
     * 正则匹配结果集合
     * @param targetStr 带匹配字符串
     * @param regex 正则表达式
     * @return 匹配结果集合
     */
    public List<String> regexResultArr(String targetStr, String regex){

        pattern = Pattern.compile(regex); //正则模式编译
        matcher = pattern.matcher(targetStr);
        //存放匹配到的结果
        List<String> matchResults = new ArrayList<>();
        while (matcher.find()) {//匹配完一次指针下移一个位置
            matchResults.add(matcher.group());
        }
        return matchResults;
    }



}
