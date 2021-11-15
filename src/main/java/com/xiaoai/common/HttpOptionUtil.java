package com.xiaoai.common;

import java.beans.Encoder;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author
 * @Date 2021-01-21 00:29
 */
public class HttpOptionUtil {

    private static final String DEFAULT_ENCODE = "utf-8";
    private static final String DEFAULT_METHOD = "GET";

    /**
     * 设置请求头
     * @param conn
     * @param heads
     */
    private static void setHeads(HttpURLConnection conn, Map<String, String> heads) {
        Iterator<Map.Entry<String, String>> iterator = heads.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> entry = iterator.next();
            conn.setRequestProperty(entry.getKey(),entry.getValue());
        }
    }

    public static String getHTML(String url) throws Exception {
        return getHTML(url,DEFAULT_METHOD,new HashMap<String, String>(),DEFAULT_ENCODE);
    }
    /**
     *  获取网址HTMP源码
     * @param url 网址
     * @param heads 请求头消息
     * @return 网址源码
     * @throws Exception
     */
    public static String getHTML(String url, Map<String,String> heads) throws Exception {
        return getHTML(url,DEFAULT_METHOD,heads,DEFAULT_ENCODE);
    }

    public static String getHTML(String url, String method, Map<String,String> heads) throws Exception {
        return getHTML(url,method,heads,DEFAULT_ENCODE);
    }

    public static String getHTML(String url, String method, Map<String,String> heads, String encoder) throws Exception {
        String html = "";
        URL realurl = new URL(url);//获取URL
        HttpURLConnection conn = (HttpURLConnection)realurl.openConnection();//打开连接
        conn.setRequestMethod(method); // 设置请求方式
        setHeads(conn,heads);// 设置请求头

        //创建输入缓冲流保存数组
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),encoder));
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
     *  网络url请求文件数据，并保存到本地
     * @param targetUrl 目标url
     * @param heads 请求头信息
     * @param savePath 保存目的路径
     * @param fileName 文件名称
     * @param fileSuffix 文件后缀
     * @throws Exception
     */
    public static void saveFile(String targetUrl,Map<String,String> heads,
                               String savePath,String fileName, String fileSuffix) throws Exception{
        URL url = new URL(targetUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        setHeads(conn,heads);// 设置请求头

        conn.connect();//打开连接
        InputStream inStream = conn.getInputStream();//获取url的输入流

        //用这个做中转 ，把图片数据都放在了这里，再调用toByteArray()即可获得数据的byte数组
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte [] buf = new byte[1024];
        int len = 0;
        //读取图片数据
        while((len=inStream.read(buf))!=-1){
            outStream.write(buf,0,len);
        }
        inStream.close();
        outStream.close();
        if (outStream.toByteArray() != null) {
            File dir = new File(savePath);
            if (!dir.exists()) { //目录不存在，创建目录
                dir.mkdirs();
            }
            File file = new File( dir+"//"+fileName+"."+fileSuffix);
            FileOutputStream op = new FileOutputStream(file);
            op.write(outStream.toByteArray());
            op.flush();
            op.close();
        }
    }
}
