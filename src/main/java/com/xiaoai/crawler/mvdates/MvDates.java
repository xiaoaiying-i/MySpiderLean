package com.xiaoai.crawler.mvdates;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * 获取 http://119.29.193.127/#/recom 一些mv数据
 *
 * @Author
 * @Date 2021-03-15 00:14
 */
public class MvDates {

    public static void main(String[] args) throws Exception {
        Document document = Jsoup.parse(new URL("http://119.29.193.127/#/mvDetails?id=5570087"),5000);
        System.out.println(document);
    }


}
