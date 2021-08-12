package io.gitee.hek97.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/testServlet")
public class TestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 代理对象(proxyReq)调用getParameter()方法获取请求参数
        String name = request.getParameter("name");
        String msg = request.getParameter("msg");
        //2. 打印经过代理对象(proxyReq)增强后的请求参数
        System.out.println(name+":"+msg);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
