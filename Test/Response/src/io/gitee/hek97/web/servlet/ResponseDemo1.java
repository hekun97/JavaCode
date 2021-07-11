package io.gitee.hek97.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/responseDemo1")
public class ResponseDemo1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo1...");
        //重定向，访问 /responseDemo1 路径会自动跳转到 /responseDemo2 路径。
        /*
        //1.设置状态码为302
        resp.setStatus(302);
        //2.设置响应头location
        resp.setHeader("location","/today/responseDemo2");
        */
        //更简单的重定向，一般都使用该种方式
        //resp.sendRedirect("today/responseDemo2");
        //使用req.getContextPath()动态获取虚拟目录
        resp.sendRedirect(req.getContextPath()+"/responseDemo2");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
