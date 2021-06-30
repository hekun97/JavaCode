package io.gitee.hek97.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/demo3")
public class RequestDemo3 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String referer = request.getHeader("referer");
        System.out.println(referer);//http://localhost:8080/today/login.html
        //防盗链
        if(referer!=null){
            if(referer.contains("/today")){
                //正常访问
                System.out.println("播放电影...");
            }
            else{
                //盗链
                System.out.println("想看电影吗？来优酷吧！");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
