package io.gitee.hek97.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        //1. 导入驱动jar包
        //2. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //3. 获取数据库连接对象
        Connection coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3", "root", "root");
        //4. 定义SQL语句
        String sql = "update user set PASSWORD = 2345 where id = 1";
        //5. 获取执行SQL对象 statement
        Statement stmt = coon.createStatement();
        //5. 执行SQL
        int cout = stmt.executeUpdate(sql);
        System.out.println(cout);
        stmt.close();
        coon.close();


    }
}
