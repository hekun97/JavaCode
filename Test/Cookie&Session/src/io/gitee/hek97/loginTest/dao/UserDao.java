package io.gitee.hek97.loginTest.dao;

import io.gitee.hek97.loginTest.domain.User;
import io.gitee.hek97.loginTest.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
    //创建JdbcTemplate对象，让所有的方法都能共用该对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 登录方法
     * @param loginUser 登录页面提交的用户名和密码
     * @return 数据库查询的用户的所有数据
     */
    public User login(User loginUser){
        try {
            System.out.println(loginUser.getUsername()+":"+loginUser.getPassword());
            //定义SQL语句
            String sql ="select * from user where username = ? and PASSWORD = ?";
            //执行SQL语句，将查询的结果封装为User对象，并将User对象装入到List集合中。
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getUsername(),loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();//记录日志
            return null;
        }

    }
}
