package io.gitee.hek97.datasource.template;

import io.gitee.hek97.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        //1. 导入jar包
        //2. 创建JdbcTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //3. 定义SQL语句
        String sql="update account set balance =? where id = ?";
        //4. 执行SQL语句
        int count = template.update(sql,500,1);//第一个参数为SQL语句，后面的参数为?的值。
        //5. 处理结果
        System.out.println(count);//1
    }
}