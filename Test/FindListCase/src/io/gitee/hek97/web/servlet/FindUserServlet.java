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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取请求参数数据
        Integer id = Integer.valueOf(request.getParameter("id"));
        //3.调用service方法
        UserService service = new UserServiceImpl();
        User user = service.findUser(id);
        //初始化List集合
        List<String> addresses = new ArrayList<>(){{
            add("广东");
            add("湖南");
            add("重庆");
        }
        };
        //获取当前用户的地址
        String address= user.getAddress();
        //从List中删除这个地址
        addresses.remove(address);
        //4.将用户信息存入request域
        request.setAttribute("user",user);
        //将地址信息存入request域
        request.setAttribute("addresses",addresses);
        //5.转发页面
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
