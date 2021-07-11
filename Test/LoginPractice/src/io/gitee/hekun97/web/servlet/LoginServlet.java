package io.gitee.hekun97.web.servlet;

import io.gitee.hekun97.dao.UserDao;
import io.gitee.hekun97.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        /*//2.获取数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //3.封装数据
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);*/
        //2.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //3.使用BeanUtils工具类封装map集合的数据到JavaBean对象loginUser
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用登录方法判断
        User user = new UserDao().login(loginUser);
        if(user==null){
            //登录失败，转发失败页面
            request.getRequestDispatcher("/failServlet").forward(request,response);
        }else{
            //登录成功，将user存入共享域对象
            request.setAttribute("user",user);
            //转发到成功页面
            request.getRequestDispatcher("/successServlet").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
