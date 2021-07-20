package io.gitee.hek97.loginTest.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource ds;
    //创建数据库连接池
    static {
        //加载配置文件
        try {
            Properties pro = new Properties();
            //使用ClassLoader加载配置文件字节输入流
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取数据库连接池
    public static DataSource getDataSource(){
        return ds;
    }
    //获取数据库连接
    public static Connection getConnection() throws SQLException {
       return ds.getConnection();
    }

}
