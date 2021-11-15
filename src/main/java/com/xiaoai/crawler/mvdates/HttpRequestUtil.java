package com.xiaoai.crawler.mvdates; /**
 * Created by david on 2017-7-5.
 */

import com.alibaba.fastjson.JSONObject;
import com.xiaoai.common.DataBaSeUtil;
import com.xiaoai.common.HttpOptionUtil;
import com.xiaoai.defaultsetting.RequstHeadConfigration;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class HttpRequestUtil {
    /**
     * 发起http请求并获取结果
     * @param requestUrl 请求地址
     */
    public static JSONObject getXpath(String requestUrl){
        String res="";
        JSONObject object = null;
        StringBuffer buffer = new StringBuffer();
        try{
            URL url = new URL(requestUrl);
            HttpURLConnection urlCon= (HttpURLConnection)url.openConnection();
            if(200==urlCon.getResponseCode()){
                InputStream is = urlCon.getInputStream();
                InputStreamReader isr = new InputStreamReader(is,"utf-8");
                BufferedReader br = new BufferedReader(isr);

                String str = null;
                while((str = br.readLine())!=null){
                    buffer.append(str);
                }
                br.close();
                isr.close();
                is.close();
                res = buffer.toString();
                JSONObject parse =new JSONObject();
                object = (JSONObject) parse.parse(res);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return object;
    }

   public static JSONObject postDownloadJson(String path,String post){
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            printWriter.write(post);//post的参数 xx=xx&yy=yy
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            byte[] arr = new byte[1024];
            while((len=bis.read(arr))!= -1){
                bos.write(arr,0,len);
                bos.flush();
            }
            bos.close();
            JSONObject parse = new JSONObject();
            return (JSONObject)parse.parse(bos.toString("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    //测试
//    public static void main(String args [] ) {
//        JSONObject res = null;
////      res = getXpath("http://ip.taobao.com/service/getIpInfo.php?ip=63.223.108.42");
//        res = postDownloadJson("http://119.29.193.127/mv/all?area=%E5%85%A8%E9%83%A8&type=%E5%85%A8%E9%83%A8&order=%E4%B8%8A%E5%8D%87%E6%9C%80%E5%BF%AB&limit=16&offset=0","ip=63.223.108.42");
//        System.out.println(res);
////      System.out.println(res.get("code"));
////      System.out.println(res.get("data"));
//    }

    //测试
    public static void main(String args [] ) throws Exception {
        JSONObject res = null;

        //http://119.29.193.127/mv/all?area=%E5%85%A8%E9%83%A8&type=%E5%85%A8%E9%83%A8&order=%E4%B8%8A%E5%8D%87%E6%9C%80%E5%BF%AB&limit=16&offset=0
        //http://119.29.193.127/mv/all?area=%E5%85%A8%E9%83%A8&type=%E5%85%A8%E9%83%A8&order=%E4%B8%8A%E5%8D%87%E6%9C%80%E5%BF%AB&limit=16&offset=32
        //http://119.29.193.127/mv/all?area=%E6%B8%AF%E5%8F%B0&type=%E5%85%A8%E9%83%A8&order=%E4%B8%8A%E5%8D%87%E6%9C%80%E5%BF%AB&limit=16&offset=0
        //http://119.29.193.127/mv/all?area=%E6%97%A5%E6%9C%AC&type=%E5%85%A8%E9%83%A8&order=%E4%B8%8A%E5%8D%87%E6%9C%80%E5%BF%AB&limit=16&offset=0
        //http://119.29.193.127/mv/all?area=%E6%AC%A7%E7%BE%8E&type=%E5%85%A8%E9%83%A8&order=%E4%B8%8A%E5%8D%87%E6%9C%80%E5%BF%AB&limit=16&offset=0
        // 中文：http://119.29.193.127/mv/all?area=%E5%86%85%E5%9C%B0&type=%E7%8E%B0%E5%9C%BA%E7%89%88&order=%E4%B8%8A%E5%8D%87%E6%9C%80%E5%BF%AB&limit=16&offset=48


        // 内地：http://119.29.193.127/mv/all?area=%E5%86%85%E5%9C%B0&type=%E5%85%A8%E9%83%A8&order=%E4%B8%8A%E5%8D%87%E6%9C%80%E5%BF%AB&limit=16&offset=0
        // 港台：http://119.29.193.127/mv/all?area=%E6%B8%AF%E5%8F%B0&type=%E5%85%A8%E9%83%A8&order=%E4%B8%8A%E5%8D%87%E6%9C%80%E5%BF%AB&limit=16&offset=0
        // 欧美：http://119.29.193.127/mv/all?area=%E6%AC%A7%E7%BE%8E&type=%E5%85%A8%E983%A8&order=%E4%B8%8A%E5%8D%87%E6%9C%80%E5%BF%AB&limit=16&offset=0
        // 日本：http://119.29.193.127/mv/all?area=%E6%97%A5%E6%9C%AC&type=%E5%85%A8%E9%83%A8&order=%E4%B8%8A%E5%8D%87%E6%9C%80%E5%BF%AB&limit=16&offset=0
        //　韩国：http://119.29.193.127/mv/all?area=%E9%9F%A9%E5%9B%BD&type=%E5%85%A8%E9%83%A8&order=%E4%B8%8A%E5%8D%87%E6%9C%80%E5%BF%AB&limit=16&offset=0


        res = getXpath(
               "http://119.29.193.127/mv/all?area=%E9%9F%A9%E5%9B%BD&type=%E5%85%A8%E9%83%A8&order=%E4%B8%8A%E5%8D%87%E6%9C%80%E5%BF%AB&limit=16&offset=0"
        );
//        System.out.println(res);
//        System.out.println(res.get("code"));
//        System.out.println(res.get("data"));

        // 获取数据库连接
        Connection conn = DataBaSeUtil.getConn();

        // 获取json数据集合封装成java对象
        List<MvBean> mvBeans = JSONObject.parseArray(res.get("data").toString(),MvBean.class);
        for (MvBean mb: mvBeans) {
            // 获取mv视频url
//            res = getXpath("http://119.29.193.127/mv/url?id="+mb.getId());
//            JSONObject data = (JSONObject) res.get("data");
//            mb.setMvUrl(data.get("url").toString());
            System.out.println("--------------------------------------");
            System.out.println(mb);

            // 更新数据库
            try {
                DataBaSeUtil.executeSQL(conn,
                        "insert into mv values(null,?,?,?,?,\"韩国\",null,?,?,?);",
                        mb.getName(),
                        "system",
                        mb.getCover(),
                        mb.getId(),
                        new Date(System.currentTimeMillis()-56000),
                        mb.getPlayCount(),
                        mb.getArtistName());
                Thread.sleep(1500);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            break;
        }


//        res = getXpath("http://119.29.193.127/mv/url?id=5934014");
//        JSONObject data = (JSONObject) res.get("data");
//        System.out.println(data.get("url").toString());



//        RequstHeadConfigration.setHead("Cookie","NMTID=00OtchCfcyVbjEjfU-CnS9GOWfty3gAAAF4NE6UMw");
//        RequstHeadConfigration.setHead("If-None-Match","W/\"969-177fc9b872c\"");
//        RequstHeadConfigration.setHead("Upgrade-Insecure-Requests","1");

//        System.out.println(HttpOptionUtil.getHTML("http://119.29.193.127/#/mvDetails?id=5570087",RequstHeadConfigration.getHeads() ));
//        String mvUrl = RegexOptionUtil.regexFirstResult(
//                HttpOptionUtil.getHTML("http://119.29.193.127/#/mvDetails?id=" + 5570087),
//                "http.*?\\.mp4.*&.*controls="
//        );
//        System.out.println(mvUrl);

    }
}