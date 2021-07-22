package io.gitee.hek97.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource ds;
    static {
        try {
            //1. 加载配置文件
            Properties pro = new Properties();
            InputStream rs = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(rs);
            //2. 初始化连接池对象
            ds= DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接池对象
     * @return 连接池对象
     */
    public static DataSource getDataSource(){
        return ds;
    }

    /**
     * 获取连接
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    public static void close(PreparedStatement ps,Connection conn){
        close(null,ps,conn);

    }

    /**
     * 释放资源
     * @param rs
     * @param ps
     * @param conn
     */
    public static void close(ResultSet rs, PreparedStatement ps, Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
