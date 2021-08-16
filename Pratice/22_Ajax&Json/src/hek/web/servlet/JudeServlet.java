package hek.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/judeServlet")
public class JudeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.设置响应编码
        response.setContentType("text/html;charset=utf-8");
        //2.获取参数
        String username = request.getParameter("username");
        //3.设置map集合
        Map<String, Object> map = new HashMap<>(2);
        //3.1存在
        if("tom".equals(username)){
            map.put("userExsit",true);
            map.put("msg","用户名重复");
        }
        //3.2不存在
        else{
            map.put("userExsit",false);
            map.put("msg","用户名可用");
        }
        //4.把Java对象(map集合)转为JSON数据
        //4.1创建jackson核心对象ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper);
        //4.2转换，把map集合转换为JSON后，写入到response.getWriter()流内
        mapper.writeValue(response.getWriter(),map);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
