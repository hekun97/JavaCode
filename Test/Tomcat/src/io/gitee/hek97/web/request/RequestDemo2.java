package io.gitee.hek97.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/demo2")
public class RequestDemo2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String agent = request.getHeader("user-agent");
        if(agent.contains("Chrome")){
            //谷歌浏览器
            System.out.println("谷歌来了...");
        }
        if(agent.contains("Firefox")){
            //火狐浏览器
            System.out.println("火狐来了...");
        }
    }
}
