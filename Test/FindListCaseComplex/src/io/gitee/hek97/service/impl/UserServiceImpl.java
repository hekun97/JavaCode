package io.gitee.hek97.service.impl;

import io.gitee.hek97.dao.UserDao;
import io.gitee.hek97.dao.impl.UserDaoImpl;
import io.gitee.hek97.domain.PageBean;
import io.gitee.hek97.domain.User;
import io.gitee.hek97.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的实体类
 */
public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();//调用UserService类时，获取一个UserDaoImpl对象。

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

    @Override
    public User findUser(Integer id) {
        //调用dao查找数据
        User user = dao.findUser(id);
        return user;
    }

    @Override
    public void delUsers(String[] ids) {
        //调用dao删除用户信息
        for (String uid : ids) {
            dao.delete(Integer.parseInt(uid));
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //传入值不合理时
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (rows <= 0) {
            rows = 5;
        }
        //1.创建空的PageBean对象
        PageBean<User> pb = new PageBean<>();
        //2.给PageBean对象的当前页码(currentPage)和每页显示的条数(rows)赋值
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //3.调用dao查询总记录数totalCount，并给PageBean对象的总记录数(totalCount)赋值
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //4.计算开始的索引
        int start = (currentPage - 1) * rows;
        // 5.调用dao查询每页数据的list集合，并赋值给pageBean对象
        List<User> list = dao.findByPage(start, rows, condition);
        pb.setList(list);
        //6.计算总页数,并赋值给pageBean对象
        int totalPage = (totalCount % rows == 0) ? (totalCount / rows) : (totalCount / rows + 1);
        pb.setTotalPage(totalPage);
        //7.返回pageBean对象
        return pb;
    }
}
