package io.gitee.hek97.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/cookieDemo7")
public class CookieDemo7 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置响应编码
        response.setContentType("text/html;charset=utf-8");
        //2.从浏览器获取Cookie的数组
        Cookie[] cs = request.getCookies();
        //3.判断数组中是否存在Cookie内容
        //3.1存在Cookie内容
        if (cs != null && cs.length > 0) {
            boolean flag=false;
            //4.遍历Cookie
            for (Cookie c : cs) {
                //5.判断是否存在名为lastTime的cookie
                //5.1存在名为lastTime的cookie
                if (c.getName().equals("lastTime")) {
                    flag=true;
                    //5.1.1输出上次登录时间
                    response.getWriter().write("欢迎你，你上次登录的时间为：" + URLDecoder.decode(c.getValue()));
                    //5.1.2获取当前登录的时间
                    //获取当前时间戳
                    Date date = new Date();
                    //获取SimpleDateFormat对象格式化时间戳
                    SimpleDateFormat sdf = new SimpleDateFormat();
                    sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
                    String format = sdf.format(date);
                    //将时间进行URL编码
                    String encode = URLEncoder.encode(format,"utf-8");
                    //5.1.3将当前登录的时间存到名为lastTime的cookie中，对上次登录的时间进行更新
                    c.setValue(encode);
                    response.addCookie(c);
                    //5.1.4停止当前循环
                    break;
                }
            }
            //5.2不存在名为lastTime的cookie，用户首次登录
            if(flag==false){
                firstTime(response);
            }

        }
        //不存在Cookie内容，用户首次登录
        else {
            firstTime(response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    //首次登录的方法
    private void firstTime(HttpServletResponse response) throws IOException {
        //输出首次登录内容
        response.getWriter().write("欢迎你，这是你的首次登录");
        //获取当前时间戳
        Date date = new Date();
        //对时间进行格式化
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        //将格式化后的时间进行URL编码，防止乱码
        String encode = URLEncoder.encode(format,"utf-8");
        //生成cookie
        Cookie lastTime = new Cookie("lastTime", encode);
        //添加名为lastTime的cookie到浏览器
        response.addCookie(lastTime);
    }
}

