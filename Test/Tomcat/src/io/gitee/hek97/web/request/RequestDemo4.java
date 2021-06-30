package io.gitee.hek97.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/demo4")
public class RequestDemo4 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取请求方式
        String method = request.getMethod();
        System.out.println("请求方式："+method);
        //2. 获取虚拟目录
        String contextPath = request.getContextPath();
        System.out.println("虚拟目录："+contextPath);
        //3. 获取Servlet路径
        String servletPath = request.getServletPath();
        System.out.println("Servlet路径："+servletPath);
        //4. 获取get方式请求参数
        String queryString = request.getQueryString();
        System.out.println("get方式请求参数："+queryString);
        //5.获取请求URI URL
        String requestURI = request.getRequestURI();
        System.out.println("URI："+requestURI);
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("URL："+requestURL);
        //6. 获取协议以及版本
        String protocol = request.getProtocol();
        System.out.println("协议以及版本："+protocol);
        //7. 获取客户机的IP地址
        String remoteAddr = request.getRemoteAddr();
        System.out.println("IP地址"+remoteAddr);
    }
}
