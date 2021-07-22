package io.gitee.hek97.web.servlet;

import io.gitee.hek97.domain.User;
import io.gitee.hek97.service.UserService;
import io.gitee.hek97.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        response.setCharacterEncoding("utf-8");
        System.out.println("nihao");
        //2.调用UserService中的findAll()方法完成查询
        UserService UserService = new UserServiceImpl();
        List<User> users = UserService.findAll();
        System.out.println("servlet"+users);
        //3.将List集合存入request域中
        request.setAttribute("users",users);

        //4.转发到list.jsp页面
        request.getRequestDispatcher("list.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
