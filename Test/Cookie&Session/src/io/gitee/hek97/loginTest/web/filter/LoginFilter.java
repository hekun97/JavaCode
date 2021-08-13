package io.gitee.hek97.loginTest.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录验证的过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.强制将ServletRequest类型转化为HttpServletRequest类型（为了使用获取getRequestURI方法）
        HttpServletRequest request = (HttpServletRequest) req;
        //2.获取资源请求路径
        String uri = request.getRequestURI();
        //3.用户就是想登录，放行。这里应该过滤掉登录需要的页面、Servlet、Css、js等代码，如排除css：uri.contains("/css/")
        if (uri.contains("/login.jsp") || uri.contains("/loginServlet") || uri.contains("/checkCodeServlet")) {
            chain.doFilter(req, resp);
        }
        //4.其它情况，判断是否登录，未登录则跳转到登录页面，已登录则放行
        else {
            HttpSession session = request.getSession();
            Object user = session.getAttribute("user");
            //未登录
            if (user == null) {
                request.setAttribute("code_error", "你尚未登录，请登录！");
                request.getRequestDispatcher("/login.jsp").forward(request, resp);
            } else {
                chain.doFilter(req, resp); //已登录，放行
            }
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    public void destroy() {

    }


}
