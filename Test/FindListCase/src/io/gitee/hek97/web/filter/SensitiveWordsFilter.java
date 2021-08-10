package io.gitee.hek97.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    /**
     * 敏感词汇集合
     */
    private List<String> list = new ArrayList<>();

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1. 使用代理模式增强getParameter()方法
        Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //如果是获取参数的方法
                if (method.getName().equals("getParameter")) {
                    //获取返回值，并对返回值进行增强
                    String value = (String) method.invoke(req, args);
                    if (value != null) {
                        for (String str : list) {
                            if (value.contains(str)) {
                                value = value.replaceAll(str, "***");
                            }
                        }
                    }
                    return value;
                }
                //2. 不进行增强，原样返回
                return method.invoke(req, args);
            }
        });
        //放行
        chain.doFilter(req, resp);

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        //加载文件中的敏感词汇
    }

}
