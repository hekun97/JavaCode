package io.gitee.hek97.test;


import io.gitee.hek97.dao.UserDao;
import io.gitee.hek97.dao.impl.UserDaoImpl;
import io.gitee.hek97.domain.User;
import org.junit.Test;

import java.util.List;

public class QueryTest {
    @Test
    public void test1(){
        UserDao dao = new UserDaoImpl();
        List<User> users = dao.findAll();
        System.out.println(users);
    }
}
