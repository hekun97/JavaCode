package io.gitee.hek97.jdbc;

import io.gitee.hek97.jdbcUtils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcDemo7 {
    public static void main(String[] args) {
        //1. 键盘录入
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        //2. 调用方法
        boolean user = new JdbcDemo7().login(username, password);
        //3. 处理结果
        if (user) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }
    }

    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1. 注册驱动、获取连接
            conn = JDBCUtils.getConnection();
            //2. 定义sql语句
            String sql = "select * from user where username = ? and password = ?";
            //3. 获取执行SQL对象并赋值
            ps = conn.prepareStatement(sql);
            ps.setObject(1, username);
            ps.setObject(2, password);
            //4. 查询数据
            rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5. 释放资源
            JDBCUtils.close(rs, ps, conn);
        }
        return false;
    }
}