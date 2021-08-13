package io.gitee.hek97.loginTest.web.servlet;


import io.gitee.hek97.loginTest.dao.UserDao;
import io.gitee.hek97.loginTest.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.从请求头中获取输入的验证码
        String judeCode = request.getParameter("judeCode");
        //3.获取Session对象
        HttpSession session = request.getSession();
        //4.获取图片验证码的数字
        String jude = (String) session.getAttribute("jude");
        session.removeAttribute("jude");//移除键为jude的session
        //5.对输入的验证码和图片验证码进行对比
        //5.1不一致，请求转发到验证码有误页面
        if (jude.equalsIgnoreCase(judeCode) == false) {
            request.setAttribute("code_error", "验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        //5.2数据一致
        else {
            //6.获取数据
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            //7.封装数据
            User loginUser = new User();
            loginUser.setUsername(username);
            loginUser.setPassword(password);
            //8.调用登录方法判断
            UserDao userDao = new UserDao();
            User user = userDao.login(loginUser);
            if (user == null) {
                //9.登录失败，转发失败页面
                request.setAttribute("login_error", "用户名或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {
                //10.登录成功，将user存入共享域对象
                request.setAttribute("user", user);
                //11.转发到成功页面
                request.getRequestDispatcher("/successServlet").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
