package io.gitee.hek97.datasource.template;

import io.gitee.hek97.domain.Account;
import io.gitee.hek97.util.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 执行DML，DQL语句
 */
public class JdbcTemplateDemo2 {
    //导入jar包
    //创建template对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //Junit单元测试，可以让方法独立运行
    //1. 修改1号数据的 balance 为 10000
    @Test
    public void test1() {
        String sql = "update account set balance = ? where id = ?";
        int count = template.update(sql, 10000, 1);
        System.out.println(count);
    }

    //2. 添加一条记录
    @Test
    public void test2() {
        String sql = "insert into account values(null,'麻子',2000)";
        int count = template.update(sql);
        System.out.println(count);
    }

    //3. 删除刚刚添加的记录
    @Test
    public void test3() {
        String sql = "delete from account where id =?";
        int count = template.update(sql, 4);
        System.out.println(count);
    }

    //4. 查询id为1的记录，将其封装为Map集合
    @Test
    public void test4() {
        String sql = "select * from account where id = ?";
        Map<String, Object> map = template.queryForMap(sql, 1);
        System.out.println(map);//{id=1, name=张三, balance=3000}
    }
    //4.2 查询id为1的记录，将其封装为Account对象
    @Test
    public void test44() {
        String sql = "select * from account where id = ?";
        Account account = template.queryForObject(sql, new BeanPropertyRowMapper<Account>(Account.class),1);
        System.out.println(account);//Account{id=1, name='张三', balance=500}
    }

    //5. 查询所有记录，将其封装为List
    @Test
    public void test5() {
        String sql = "select * from account";
        List<Map<String, Object>> maps = template.queryForList(sql);
        System.out.println(maps);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }

    //6.1 查询所有记录，将其封装为Emp对象的List集合，传统方式
    @Test
    public void test6_1() {
        String sql = "select * from account";
        List<Account> accounts = template.query(sql, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int i) throws SQLException {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int balance = rs.getInt("balance");
                Account account = new Account();
                account.setId(id);
                account.setName(name);
                account.setBalance(balance);
                return account;
            }
        });
        System.out.println(accounts);
    }

    //6.2 查询所有记录，将其封装为Emp对象的List集合，使用new BeanPropertyRowMapper对象简化操作。
    @Test
    public void test6_2() {
        String sql = "select * from account";
        List<Account> accounts = template.query(sql, new BeanPropertyRowMapper<Account>(Account.class));
        System.out.println(accounts);
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    //7. 查询总记录数
    @Test
    public void test7() {
        String sql = "select count(id) from account";
        Long count = template.queryForObject(sql, Long.class);
        System.out.println(count);
    }
}