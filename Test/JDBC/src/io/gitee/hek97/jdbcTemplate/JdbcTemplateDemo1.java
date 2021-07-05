package io.gitee.hek97.jdbcTemplate;

import io.gitee.hek97.jdbcUtils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        //1. 导入 jar 包，并 add as library
        //2. 创建JDBCTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDa);
        //3. 调用方法
        String sql = "update account set balance = ? where id = ?";
        int count=template.update(sql,5000,2);
        System.out.println(count);
    }
}
