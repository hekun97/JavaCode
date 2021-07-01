package io.gitee.hek97.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        //1. 导入驱动jar包
        //2. 注册驱动，mysql5之后的驱动jar包可以省略该步骤
        Class.forName("com.mysql.jdbc.Driver");
        //3. 获取数据库连接对象
        Connection coon = DriverManager.getConnection("jdbc:mysql:///db3", "root", "root");
        //4.1 定义第一条SQL语句
        String sql1 = "update account set balance = 4500 where id = 1";
        //4.2 定义第二条防注入SQL语句
        String sql2 = "update account set balance = ? where id = ?";
        //5.1 获取执行SQL对象 statement
        Statement stmt = coon.createStatement();
        //5.2 获取执行防注入SQL对象 prepareStatement
        PreparedStatement ps = coon.prepareStatement(sql2);//注意：这里放SQL语句
        ps.setObject(1,3500);
        ps.setObject(2,2);
        //6. 执行 sql，接受返回结果
        int count1 = stmt.executeUpdate(sql1);
        int count2 = ps.executeUpdate();//注意：这里直接执行，不用放SQL语句
        //7. 处理结果
        System.out.println("SQL1："+count1);
        System.out.println("SQL2："+count2);
        //8. 释放资源
        stmt.close();
        ps.close();
        coon.close();
    }
}
