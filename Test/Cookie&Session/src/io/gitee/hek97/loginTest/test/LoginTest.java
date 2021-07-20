package io.gitee.hek97.loginTest.test;


import io.gitee.hek97.loginTest.dao.UserDao;
import io.gitee.hek97.loginTest.domain.User;
import org.junit.Test;

public class LoginTest {
    @Test
    public void test(){
        User loginUser = new User();
        loginUser.setUsername("lisi");
        loginUser.setPassword("456");
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        System.out.println(user);
    }
}
