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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取请求参数数据
        Integer id = Integer.valueOf(request.getParameter("id"));
        //3.调用service方法通过用户id查询用户信息
        UserService service = new UserServiceImpl();
        User user = service.findUser(id);
        //4.初始化List集合，装载所有地址信息
        List<String> addresses = new ArrayList<>(){{
            add("广东");
            add("湖南");
            add("重庆");
        }
        };
        //5.获取当前用户的地址
        String address= user.getAddress();
        //6.如果集合中包含这个地址，从List中删除这个地址
        if(addresses.contains(address)){
            addresses.remove(address);
        }
        //7.将用户当前的地址放入到第一位
        addresses.add(0,address);
        //8.将用户信息存入request域
        request.setAttribute("user",user);
        //9.将地址信息存入request域
        request.setAttribute("addresses",addresses);
        //10.转发页面
        request.getRequestDispatcher("/update.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
