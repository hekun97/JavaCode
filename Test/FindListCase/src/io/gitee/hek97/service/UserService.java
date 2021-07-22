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

}
