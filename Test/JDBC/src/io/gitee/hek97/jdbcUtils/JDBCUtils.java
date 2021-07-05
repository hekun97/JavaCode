package io.gitee.hek97.jdbcUtils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * JdbcUtils 工具类
 */
public class JDBCUtils {
    //声明静态字段，只有静态字段才能被静态代码块和静态方法访问。
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String driver = null;

    /**
     * 静态方法会随着 JDBCutils 类加载进内存时（使用该类时）被自动执行，不用放到方法中每次调用都会被执行，达到仅执行一次的目的。
     */
    static {
        Properties pro = new Properties();
        try {
            //该相对路径不能被识别
            //pro.load(new FileReader("src/jdbc.properties"));

            //绝对路径可以被正确识别和使用。
            //pro.load(new FileReader("C:\\Users\\HK\\IdeaProjects\\Test\\JDBC\\src\\jdbc.properties"));

            /*在开发时，不能直接使用绝对路径（避免文件位置变化后导致不能使用），
            需要使用classLoader（类加载器）动态获取src目录下文件（jdbc.properties）在电脑中的绝对路径。*/
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
            URL res = classLoader.getResource("jdbc.properties");
            //System.out.println(res);//获取的url为：file:/C:/Users/HK/IdeaProjects/Test/out/production/JDBC/jdbc.properties
            String path = res.getPath();//获取jdbc.properties文件的绝对路径
            //System.out.println(path); //C:/Users/HK/IdeaProjects/Test/out/production/JDBC/jdbc.properties
            pro.load((new FileReader(path)));//将jdbc.properties文件加载进内存
            //根据名字获取配置文件中的值
            url = (String) pro.get("url");
            user = (String) pro.get("user");
            password = (String) pro.get("password");
            driver = (String) pro.get("driver");
            //注册驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取连接
     *
     * @return 返回连接对象
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源
     * @param ps 释放 PreparedStatement
     * @param conn 释放 Connection
     */
    public static void close(PreparedStatement ps, Connection conn) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 重载方法释放资源
     * @param rs 释放 ResultSet
     * @param ps 释放 PreparedStatement
     * @param conn 释放 Connection
     */
    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
