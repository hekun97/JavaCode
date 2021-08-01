package io.gitee.hek97.web.servlet;

import io.gitee.hek97.domain.User;
import io.gitee.hek97.service.UserService;
import io.gitee.hek97.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/userUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取update页面请求体中的所有数据
        Map<String, String[]> map = request.getParameterMap();
        //3.使用BeanUtils工具类封装map集合的数据到JavaBean对象User
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用UserService中的update()方法进行更新
        UserService service = new UserServiceImpl();
        int i = service.update(user);
        //5.将更新结果存入request域
        request.setAttribute("update",i);
        //6.请求转发并给出更新结果
        if(i==1){
            request.getRequestDispatcher("/findUserByPageServlet").forward(request,response);
        }
        else {
            request.getRequestDispatcher("/update.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
