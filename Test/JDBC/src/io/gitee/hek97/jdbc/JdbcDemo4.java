package io.gitee.hek97.jdbc;

import io.gitee.hek97.domian.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 将查询的同一类型（account表）的数据封装后装入 List 集合，方便调用
 */
public class JdbcDemo4 {
    public List<Account> findAll() {
        Connection coon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> accounts=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //1. 获取连接
            coon = DriverManager.getConnection("jdbc:mysql:///db3", "root", "root");
            //2. 定义SQL
            String sql = "select * from account";
            //3. 执行SQL
            ps = coon.prepareStatement(sql);
            //4. 获取结果
            rs = ps.executeQuery();
            //5. 处理结果
            //建立
            accounts = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int balance = rs.getInt(3);
                //创建account对象，并赋值
                Account account = new Account();
                account.setId(id);
                account.setName(name);
                account.setBalance(balance);

                //装载集合
                accounts.add(account);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //6. 释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (coon != null) {
                try {
                    coon.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //7. 输出结果
        return accounts;
    }
}
