package io.gitee.hek97.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
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
}
