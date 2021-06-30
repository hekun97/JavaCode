package io.gitee.hek97.web.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/demo6")
public class RequestDemo6 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post 获取请求参数

        //1. 根据参数名称获取参数值
        String username = request.getParameter("username");
        System.out.println("1. post请求方式："+username);

        //2. 根据参数名称获取参数值大的数组
        String[] hobbies = request.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println("2. "+hobby);
        }

        //3. 获取所有请求的参数名称
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            String value = request.getParameter(name);
            System.out.println("3. "+name +":"+value);
            //此处存在问题是复选框的值只打印了一个。
        }
        //4. 获取所有参数的map集合
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (String key :
                parameterMap.keySet()) {
            String[] value = parameterMap.get(key);
            System.out.println("4. "+key+":"+ Arrays.toString(value));
            //完美解决了复选框只打印一个值的问题
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        //get 获取请求参数
        //根据参数名称获取参数值
        String username = request.getParameter("username");
        System.out.println("get请求方式："+username);
        */
        this.doPost(request,response);
    }
}
