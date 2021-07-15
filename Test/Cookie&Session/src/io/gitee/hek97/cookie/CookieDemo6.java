package io.gitee.hek97.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/cookieDemo6")
public class CookieDemo6 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //4.从浏览器获取cookie数据
        Cookie[] cs = request.getCookies();
        if(cs!=null){
            for (Cookie c : cs) {
                //5.输出
                System.out.println(c.getName()+":"+ URLDecoder.decode(c.getValue()));//对获取的信息进行解码
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
