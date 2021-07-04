package io.gitee.hek97.jdbc;

import java.sql.*;

/**
 * 结果集对象 ResultSet
 */
public class JdbcDemo3 {
    public static void main(String[] args) throws SQLException {
        Connection coon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //1. 获取连接
            coon = DriverManager.getConnection("jdbc:mysql:///db3", "root", "root");
            //2. SQL语句
            String sql = "select * from account";
            //3. 执行SQL的对象 prepareStatement
            ps = coon.prepareStatement(sql);
            //4. 获取结果
            rs = ps.executeQuery();
            //5. 处理结果
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString("name");
                int balance = rs.getInt("balance");
                System.out.println(id + "---" + name + "---" + balance);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
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
    }
}
