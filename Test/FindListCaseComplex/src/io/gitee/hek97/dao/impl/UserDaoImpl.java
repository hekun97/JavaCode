package io.gitee.hek97.dao.impl;

import io.gitee.hek97.dao.UserDao;
import io.gitee.hek97.domain.User;
import io.gitee.hek97.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    //使用JDBC操作数据库
    /**
     * 1.创建JdbcTemplate对象
     */
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
    public int findTotalCount(Map<String, String[]> condition) {
        /*
        2.定义sql语句，需要的sql类似：select count(*) from user where 1=1 and name=? and address=? and email=?
          这里需要一个动态sql，通过动态拼接得到合适的sql查询语句
        */
        String sql = "select count(*) from user where 1 = 1 ";
        //3.定义params集合用于存放sql语句中?的值
        List<Object> params = new ArrayList<>();
        //4.调用抽取的复杂条件查询的方法
        StringBuffer sb = conditionSql(sql, params, condition);
        //5.执行sql， params.toArray()是将List集合转化为数组
        int count = template.queryForObject(sb.toString(), int.class, params.toArray());
        //6.返回总记录数
        return count;
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        //2.定义sql语句
        String sql = "select * from user where 1 = 1 ";
        //3.定义params集合用于存放sql语句中?的值
        List<Object> params = new ArrayList<>();
        //4.调用抽取的复杂条件查询的方法
        StringBuffer sb = conditionSql(sql, params, condition);
        //5.在可变长sql语句最后加上分页的sql语句。
        sb.append("limit ? , ?");
        //6.在params集合中存放sql语句中分页开始的索引（start）和每页显示条数（rows）
        params.add(start);
        params.add(rows);
        //7.执行sql
        List<User> list = template.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class), params.toArray());
        //8.返回封装到集合中的用户信息
        return list;
    }

    /**
     * 用于拼接条件查询SQL的方法
     *
     * @param sql       原始sql
     * @param condition 复杂查询条件
     * @return 返回拼接好查询条件的SQL
     */
    public StringBuffer conditionSql(String sql, List<Object> params, Map<String, String[]> condition) {
        //将sql转为线程安全的可变长字符串
        StringBuffer sb = new StringBuffer(sql);
        //遍历循环Map集合，获取List页面中传过来的复杂条件查询的键和值
        for (String key : condition.keySet()) {
            //如果键是当前页码（currentPage）或每页显示的条数（rows），则什么都不做，结束这次循环，开始下一次循环
            if (key.equals("currentPage") || key.equals("rows")) {
                continue;
            }
            //根据键（name、address、email）获取Map集合中存放的值（String类型的数组）,然后获取数组中第1个位置的值。
            String value = condition.get(key)[0];
            /*
            如果值不为null且值不为空字符串。
                将该键所需的模糊查询sql语句拼接到sb后面；
                将该值作为该键模糊查询sql语句中?的值存放到params的List集合内。
            */
            if (value != null && !value.equals("")) {
                sb.append(" and " + key + " like ?");
                params.add("%" + value + "%");
            }
        }
        return sb;
    }
}
