package hek.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    /**
     * 快速入门
     */
    @Test
    public void test(){
        //1.获取连接
        Jedis jedis = new Jedis("localhost",6379);
        //2.操作
        jedis.set("username","zhangsan");
        //3.关闭连接
        jedis.close();
    }
}
