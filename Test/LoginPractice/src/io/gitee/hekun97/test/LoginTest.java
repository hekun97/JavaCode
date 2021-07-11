package io.gitee.hekun97.test;

import io.gitee.hekun97.dao.UserDao;
import io.gitee.hekun97.domain.User;
import org.junit.Test;

public class LoginTest {
    @Test
    public void test(){
        User loginUser = new User();
        loginUser.setUsername("lisi");
        loginUser.setPassword("123");
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        System.out.println(user);
    }
}
