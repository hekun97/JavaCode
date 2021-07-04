package io.gitee.hek97.jdbc;

import io.gitee.hek97.domian.Account;

import java.util.List;

public class JdbcDemo5 {
    public static void main(String[] args) {
        List<Account> list = new JdbcDemo4().findAll();
        System.out.println(list);
    }
}
