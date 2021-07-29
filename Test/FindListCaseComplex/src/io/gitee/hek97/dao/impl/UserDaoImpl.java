package io.gitee.hek97.dao.impl;

import io.gitee.hek97.dao.UserDao;
import io.gitee.hek97.domain.User;
import io.gitee.hek97.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    //使用JDBC操作数据库
    //1.创建JdbcTemplate对象
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {

        //2.定义SQL语句
        String sql = "select * from user";
        //3.执行SQL语句,查询所有记录，将其封装为User对象的List集合，使用new BeanPropertyRowMapper对象简化操作
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        //4.返回数据
        return users;
    }

    @Override
    public int update(User user) {
        //2.定义sql语句
        String sql = "update user set name = ? , gender = ?, age = ? ,address = ? ,qq=?,email=? where id =? ";
        //3.执行sql语句
        int i = template.update(sql,
                user.getName(), user.getGender(), user.getAge(),
                user.getAddress(), user.getQq(), user.getEmail(), user.getId());
        return i;
    }

    @Override
    public int delete(Integer id) {
        //2.定义sql语句
        String sql = "delete from user where id =? ";
        //3.执行sql语句
        int i = template.update(sql, id);
        return i;
    }

    @Override
    public int insert(User user) {
        //2.定义sql语句
        String sql = "insert into user values(?,?,?,?,?,?,?)";
        int i = template.update(sql, user.getId(), user.getName(), user.getGender(), user.getAge(),
                user.getAddress(), user.getQq(), user.getEmail());
        return i;
    }

    @Override
    public User findUser(Integer id) {
        //2.定义sql语句
        String sql = "select * from user where id =?";
        //3.执行sql
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return user;
    }

    @Override
    public int findTotalCount() {
        //2.定义sql语句
        String sql = "select count(*) from user";
        //3.执行sql
        Integer count = template.queryForObject(sql, Integer.class);
        return count;
    }

    @Override
    public List<User> findByPage(int start, int rows) {
        //2.定义sql语句
        String sql = "select * from user limit ? , ?";
        //3.执行sql
        List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class), start, rows);
        return list;
    }
}
