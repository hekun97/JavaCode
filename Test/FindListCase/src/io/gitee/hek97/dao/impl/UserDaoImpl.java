package io.gitee.hek97.dao.impl;

import io.gitee.hek97.dao.UserDao;
import io.gitee.hek97.domain.User;
import io.gitee.hek97.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Properties;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库
        //1.创建JdbcTemplate对象
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        //2.定义SQL语句
        String sql="select * from user";
        //3.执行SQL语句,查询所有记录，将其封装为User对象的List集合，使用new BeanPropertyRowMapper对象简化操作
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        //4.返回数据
        System.out.println("dao"+users);
        return users;
    }
}
