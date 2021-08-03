package io.gitee.hek97.dao;

import io.gitee.hek97.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户操作的dao
 */
public interface UserDao {
    List<User> findAll();

    int update(User user);

    int delete(Integer id);

    int insert(User user);

    User findUser(Integer id);

    int findTotalCount(Map<String, String[]> condition);

    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
