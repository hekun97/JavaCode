package io.gitee.hek97.datasource.template;

import io.gitee.hek97.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        //1. 导入jar包
        //2. 创建JdbcTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //3. 调用方法
        String sql="update account set balance =? where id = ?";
        int count = template.update(sql,500,1);//第一个参数为SQL语句，后面的参数为?的值。
        System.out.println(count);//1
    }
}