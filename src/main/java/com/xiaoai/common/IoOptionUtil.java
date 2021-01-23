package com.xiaoai.common;

import java.io.*;

/**
 * IO相关操作封装方法
 * @Author xiaoaiying
 * @Date 2021-01-20 23:59
 */
public class IoOptionUtil {

    /**
     * 重定向打印输出位置
     * @param path 指定输出的路径
     * @param isAppend 是否追加
     * @return
     * @throws IOException
     */
    public PrintStream redirectPrint(String path ,boolean isAppend) throws IOException {
        PrintStream ps=null;
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            ps = new PrintStream(new FileOutputStream(file,isAppend));
            if (ps!=null) {
                System.setOut(ps);
                return ps;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
