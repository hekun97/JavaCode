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
        System.out.println("Service"+users);
        //返回查询的数据
        return users;
    }
}
