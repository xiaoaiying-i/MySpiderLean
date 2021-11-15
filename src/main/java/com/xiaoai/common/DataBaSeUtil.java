package com.xiaoai.common;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author xiaoaiying
 * @Date 2021-01-21 00:50
 */
public class DataBaSeUtil {

    private static Connection conn;
    private static String driverName;
    private static String url;
    private static String username;
    private static String password;

    /**
     * 获取数据库连接
     */
    public static Connection getConn(){
        try{
            // 加载dbinfo.properties配置文件
            InputStream in = DataBaSeUtil.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);

            // 获取驱动名称、url、用户名以及密码
            String driverName = properties.getProperty("driverName");
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            Class.forName(driverName);
            conn = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return conn;
        }
    }

    /**
     * 执行sql命令
     * @param sqlcomm  sql
     * @param para  参数
     * @return
     */
    public static Integer executeSQL(Connection conn,String sqlcomm,Object...para) throws Exception{
            PreparedStatement psta=conn.prepareStatement(sqlcomm);
            if (para!=null){
                for (int i=0;i<para.length;i++){// 设置参数
                    psta.setObject(i+1,para[i]);
                }
            }
            int i=psta.executeUpdate();  // 执行sql
            return i;
    }

//    /**
//     * 封装结果返回
//     * @param cls  封装类对象
//     * @param sql  sql
//     * @param para 参数
//     * @return
//     */
//    public List<T> executeSQL2ObjList(Class<T> cls, String sql, Object...para) throws Exception{
//        List<T> list=new ArrayList<T>();
//        conn = getConn();
//        PreparedStatement psta = conn.prepareStatement(sql);
//        if (para==null){
//            para=new Object[0];
//        }
//        // 设置参数
//        for (int i=0;i<para.length;i++){
//            psta.setObject(i+1,para[i]);
//        }
//        ResultSet rs=psta.executeQuery();
//        ResultSetMetaData rsmd=rs.getMetaData();
//
//        while (rs.next()){
//            T obj=cls.newInstance();
//            int col=rsmd.getColumnCount();
//            for (int i=1;i<=col;i++){
//                String DBField = rsmd.getColumnLabel(i);
//                Field field = cls.getDeclaredField(DBField);
//                field.setAccessible(true);
//                field.set(obj,rs.getObject(i));
//            }
//            list.add(obj);
//        }
//        close(rs,psta,conn);
//        return list;
//    }

    /**
     * 关闭资源
     * @param args 可变参数，根据传入的数据库资源参数进行相应关闭
     * @throws SQLException
     */
    public static void close(Object...args) throws SQLException {
        for (Object obj : args) {
            if (obj instanceof Connection) {
                Connection conn  = (Connection)obj;
                if (conn!=null && !conn.isClosed()) {
                    conn.close();
                }
            }
            if (obj instanceof Statement) {
                Statement stmt  = (Statement)obj;
                if (stmt!=null && !stmt.isClosed()) {
                    stmt.close();
                }
            }
            if (obj instanceof ResultSet) {
                ResultSet rs  = (ResultSet)obj;
                if (rs!=null && !rs.isClosed()) {
                    rs.close();
                }
            }
        }
    }
}
