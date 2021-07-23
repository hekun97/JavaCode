package io.gitee.hek97.service.impl;

import io.gitee.hek97.dao.UserDao;
import io.gitee.hek97.dao.impl.UserDaoImpl;
import io.gitee.hek97.domain.User;
import io.gitee.hek97.service.UserService;

import java.util.List;

/**
 * 用户管理的实体类
 */
public class UserServiceImpl implements UserService {
    private UserDao dao =new UserDaoImpl();//调用UserService类时，获取一个UserDaoImpl对象。
    @Override
    public List<User> findAll() {
        //调用dao查询数据
        List<User> users = dao.findAll();
        //返回查询的数据
        return users;
    }

    @Override
    public int update(User user) {
        //调用dao查询数据
        int i = dao.update(user);
        return i;
    }

    @Override
    public int delete(Integer id) {
        //调用dao删除数据
        int i = dao.delete(id);
        return i;
    }

    @Override
    public int insert(User user) {
        //调用dao添加数据
        int i = dao.insert(user);
        return i;
    }
}
