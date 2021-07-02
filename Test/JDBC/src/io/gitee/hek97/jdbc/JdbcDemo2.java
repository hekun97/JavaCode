package io.gitee.hek97.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * account 表，添加一条记录，使用 insert 语句。
 */
public class JdbcDemo2 {
    public static void main(String[] args){
        Connection coon=null;
        PreparedStatement ps=null;
        //1. 注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2. 获取链接
            coon = DriverManager.getConnection("jdbc:mysql:///db3", "root", "root");
            //3. 定义SQL语句
            String sql1="insert into account values (null,?,?)";
            //4. 获取PreparedStatement对象
            ps = coon.prepareStatement(sql1);
            ps.setObject(1,"麻子");
            ps.setObject(2,6000);
            //5. 执行
            int count = ps.executeUpdate();
            //6. 处理结果
            if(count>0){
                System.out.println("添加成功");
            }
            else{
                System.out.println("添加失败");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            //7. 释放资源
            //避免空指针异常
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(coon!=null){
                try {
                    coon.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
