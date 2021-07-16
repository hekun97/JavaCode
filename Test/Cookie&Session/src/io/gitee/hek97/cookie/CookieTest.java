package io.gitee.hek97.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
            //4.设置标签判断是否存在名为lastTime的cookie，然后遍历Cookie数组
            boolean flag=false;
            for (Cookie c : cs) {
                //5.判断是否存在名为lastTime的cookie
                //5.1存在名为lastTime的cookie
                if (c.getName().equals("lastTime")) {
                    //标签变为真
                    flag=true;
                    //5.1.1输出上次登录时间
                    response.getWriter().write("欢迎你，你上次登录的时间为：" + URLDecoder.decode(c.getValue()));
                    //5.1.2调用getCookie()的方法获取经过处理后的当前时间
                    String nowTime = getCookie();
                    //5.1.3将当前的时间存到名为lastTime的cookie中，对上次登录的时间进行更新
                    c.setValue(nowTime);
                    //5.1.4更新名为lastTime的cookie的值
                    response.addCookie(c);
                    //5.1.5停止当前循环
                    break;
                }
            }
            //5.2如果标签为假，不存在名为lastTime的cookie，用户首次登录
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

    /**
     * 用户首次登录的方法
     * @param response lastTime的Cookie
     * @throws IOException
     */
    private void firstTime(HttpServletResponse response) throws IOException {
        //1.输出首次登录内容
        response.getWriter().write("欢迎你，这是你的首次登录");
        //2.调用getCookie()的方法获取经过处理后的当前时间
        String nowTime = getCookie();
        //3.生成cookie
        Cookie lastTime = new Cookie("lastTime", nowTime);
        //4.设置过期时间为一个月
        lastTime.setMaxAge(60*60*24*30);
        //5.添加名为lastTime的cookie到浏览器
        response.addCookie(lastTime);
    }

    /**
     * 获取当前的时间
     * @return 处理后的当前时间
     * @throws UnsupportedEncodingException
     */
    private String getCookie() throws UnsupportedEncodingException {
        //1.获取当前时间戳
        Date date = new Date();
        //2.对时间进行格式化
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy年MM月dd日 HH:mm:ss");
        String formatTime = sdf.format(date);
        //3.将格式化后的时间进行URL编码，防止乱码
        String nowTime = URLEncoder.encode(formatTime);
        return nowTime;
    }
}

