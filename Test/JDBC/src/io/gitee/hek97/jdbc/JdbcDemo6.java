package io.gitee.hek97.jdbc;

import io.gitee.hek97.domian.Account;
import io.gitee.hek97.jdbcUtils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 将查询的同一类型（account表）的数据封装后装入 List 集合，方便调用
 */
public class JdbcDemo6 {
    public static void main(String[] args) {
        List<Account> list = new JdbcDemo6().findAll();
        System.out.println(list);
    }
    public List<Account> findAll() {
        Connection conn=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> accounts=null;
        try {
            //1. 获取连接
            conn = JDBCUtils.getConnection();
            //2. 定义SQL
            String sql = "select * from account";
            //3. 执行SQL
            ps = conn.prepareStatement(sql);
            //4. 获取结果
            rs = ps.executeQuery();
            //创建Account集合对象
            accounts = new ArrayList<Account>();
            //5. 处理结果
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //6. 释放资源
            JDBCUtils.close(rs,ps,conn);
        }
        //7. 输出结果
        return accounts;
    }
}
