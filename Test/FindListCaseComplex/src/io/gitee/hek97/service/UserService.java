package io.gitee.hek97.service;

import io.gitee.hek97.domain.PageBean;
import io.gitee.hek97.domain.User;

import java.util.List;

/**
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有用户信息的方法
     *
     * @return List集合中的User实例
     */
    List<User> findAll();

    /**
     * 更新用户信息的方法
     *
     * @return 返回受影响的行数
     */
    int update(User user);

    /**
     * 删除用户信息的方法
     *
     * @param id
     * @return 返回受影响的行数
     */
    int delete(Integer id);

    /**
     * 添加用户信息的方法
     *
     * @param user 返回受影响的行数
     * @return
     */
    int insert(User user);

    /**
     * 查找用户
     *
     * @param id
     * @return
     */
    public User findUser(Integer id);

    /**
     * 删除选中用户信息
     *
     * @param ids
     * @return
     */
    void delUsers(String[] ids);

    /**
     * 通过当前页码和每页的记录数来实现分页
     * @param _currentPage 当前页码
     * @param _rows 每页的记录数
     * @return 分页的对象
     */
    PageBean<User> findUserByPage(String _currentPage,String _rows);
}
