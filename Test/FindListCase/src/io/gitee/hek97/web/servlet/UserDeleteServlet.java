package io.gitee.hek97.web.servlet;

import io.gitee.hek97.service.UserService;
import io.gitee.hek97.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取请求参数的值
        Integer id = Integer.valueOf(request.getParameter("id"));
        //3.调用UserService对象中的删除方法
        UserService service = new UserServiceImpl();
        int i = service.delete(id);
        request.getRequestDispatcher("/userListServlet").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
