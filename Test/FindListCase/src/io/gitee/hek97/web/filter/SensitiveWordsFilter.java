package io.gitee.hek97.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词汇过滤器，过滤路径：testServlet
 */
@WebFilter("/testServlet")
public class SensitiveWordsFilter implements Filter {
    /**
     * 敏感词汇集合
     */
    private List<String> list = new ArrayList<>();

    @Override
    public void destroy() {
    }

    /**
     * 过滤器
     * @param req 请求对象(真实对象)
     * @param resp 响应对象
     * @param chain 过滤器
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //使用代理模式增强getParameter()方法
        //1. 创建代理对象（proxyReq）
        ServletRequest proxyReq = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            /**
             * 代理对象调用的所有方法都会触发该方法执行
             * @param proxy 代理对象
             * @param method 代理对象调用的方法，被封装为的对象
             * @param args 代理对象调用的方法时，传递的实际参数
             * @return 替换敏感词后的数据
             * @throws Throwable 避免敏感词汇.txt加载错误
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //2. 判断代理对象(proxyReq)调用的是否是getParameter()方法？
                //2.1 代理对象(proxyReq)调用的是getParameter()方法，进行增强
                if (method.getName().equals("getParameter")) {
                    //a. 获取原始值，使用真实对象(req)调用getParameter()方法
                    String value = (String) method.invoke(req, args);
                    //b. 有原始值，遍历敏感词汇集合，和原始值进行对比
                    if (value != null) {
                        for (String str : list) {
                            //原始值包含敏感词，对敏感词进行替换
                            if (value.contains(str)) {
                                value = value.replaceAll(str, "***");
                            }
                        }
                    }
                    //c. 返回替换敏感词后的值
                    return value;
                }
                //2.2. 代理对象(proxyReq)调用的不是getParameter()方法，不进行增强，原样返回
                return method.invoke(req, args);
            }
        });
        /*
        逻辑分析：
            1.浏览器请求 /testServlet 路径下的资源；
            2.过滤器放行时，doFilter方法中传入的 请求对象（request） 为 代理对象（proxyReq）;
            3.放行后，/testServlet 路径下的Servlet中使用代理对象（proxyReq）调用getParameter()方法时，代理对象（proxyReq）增强getParameter()方法（替换敏感词）;
            4.代理对象（proxyReq）将增强后的数据（替换了敏感词的数据）返回给getParameter()方法。
        */
        chain.doFilter(proxyReq, resp);//放行
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        try {
            //加载文件中的敏感词汇
            //1. 获取文件的真实路径
            ServletContext servletContext = config.getServletContext();
            //src下的文件经过编译后会被放到 /WEB-INF/classes 目录下
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
            //2.读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            //3.添加读取的数据到list集合
            String line = null;
            //4.文件中存在数据时，将数据放入list集合
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            //5.释放资源
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
