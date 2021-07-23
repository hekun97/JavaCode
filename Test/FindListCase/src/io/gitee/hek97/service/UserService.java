package io.gitee.hek97.service;

import io.gitee.hek97.domain.User;

import java.util.List;

/**
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有用户信息的方法
     * @return List集合中的User实例
     */
    public List<User> findAll();

    /**
     * 更新用户信息的方法
     * @return 返回受影响的行数
     */
    public int update(User user);

    /**
     * 删除用户信息的方法
     * @param id
     * @return 返回受影响的行数
     */
    public int delete(Integer id);

    /**
     * 添加用户信息的方法
     * @param user 返回受影响的行数
     * @return
     */
    public int insert(User user);
}
