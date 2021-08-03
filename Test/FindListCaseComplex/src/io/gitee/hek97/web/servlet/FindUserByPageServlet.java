package io.gitee.hek97.web.servlet;

import io.gitee.hek97.domain.PageBean;
import io.gitee.hek97.domain.User;
import io.gitee.hek97.service.UserService;
import io.gitee.hek97.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取分页的请求参数
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        //对请求参数进行判断
        if (currentPage == null || currentPage.equals("")) {
            currentPage = "1";
        }
        if (rows == null || rows.equals("")) {
            rows = "5";
        }
        //3.获取所有请求参数（包括分页请求参数，这里主要获取复杂条件查询提交的请求参数）
        Map<String, String[]> condition = request.getParameterMap();
        //4.调用service查询到pageBean
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage, rows, condition);
        //5.存入request域中
        request.setAttribute("pb", pb);
        request.setAttribute("condition",condition);
        //6.请求转发页面到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
