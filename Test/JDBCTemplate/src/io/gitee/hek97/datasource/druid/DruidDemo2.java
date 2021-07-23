package io.gitee.hek97.datasource.druid;

import io.gitee.hek97.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DruidDemo2 {
    public static void main(String[] args){
        Connection conn = null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            //1. 获取连接
            conn = JDBCUtils.getConnection();
            //2. 定义SQL
            String sql ="select * from account";
            //3. 获取执行SQL对象
            ps = conn.prepareStatement(sql);
            //4. 执行SQL
            rs = ps.executeQuery();
            //5. 处理结果
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int balance = rs.getInt(3);
                System.out.println(id+"---"+name+"---"+balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6. 释放资源
            JDBCUtils.close(rs,ps,conn);
        }
    }
}
