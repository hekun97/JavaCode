package io.gitee.hek97.dao;

import io.gitee.hek97.domain.User;

import java.util.List;

/**
 * 用户操作的dao
 */
public interface UserDao {
    public List<User> findAll();
    public int update(User user);
    public int delete(Integer id);
    public int insert(User user);
}
