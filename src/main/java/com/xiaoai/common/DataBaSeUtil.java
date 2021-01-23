package com.xiaoai.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author xiaoaiying
 * @Date 2021-01-21 00:50
 */
public class DataBaSeUtil {

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
