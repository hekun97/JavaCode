package io.gitee.hek97.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Demo1 {
    public static void main(String[] args) throws SQLException {
        //1. 导入jar包
        //2. 定义配置文件
        //3. 创建数据库连接池对象
        ComboPooledDataSource ds = new ComboPooledDataSource();
        //4. 获取连接对象
        Connection conn = ds.getConnection();
        //打印
        System.out.println(conn);
        //com.mchange.v2.c3p0.impl.NewProxyConnection@4de4b452 [wrapping: com.mysql.jdbc.JDBC4Connection@50b5ac82]
    }
}
