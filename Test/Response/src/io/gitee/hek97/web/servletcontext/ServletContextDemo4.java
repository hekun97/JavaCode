package io.gitee.hek97.web.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servletContextDemo4")
public class ServletContextDemo4 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过HttpServlet获取ServletContext对象
        ServletContext context = this.getServletContext();
        //2.获取文件的服务器路径
        String b = context.getRealPath("/b.txt");//web目录下资源访问
        System.out.println(b);
        //C:\Users\HK\IdeaProjects\Test\out\artifacts\Response_war_exploded\b.txt
        String c = context.getRealPath("/WEB-INF/c.txt");//WEB-INF目录下的资源访问
        System.out.println(c);
        //C:\Users\HK\IdeaProjects\Test\out\artifacts\Response_war_exploded\WEB-INF\c.txt
        String a = context.getRealPath("/WEB-INF/classes/a.txt");//src目录下的资源访问
        System.out.println(a);
        //C:\Users\HK\IdeaProjects\Test\out\artifacts\Response_war_exploded\WEB-INF\classes\a.txt
        //src下的文件经过编译后会被放到WEB-INF/classes文件下。

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
