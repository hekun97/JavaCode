<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.UnsupportedEncodingException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%
    //1.设置响应编码
    response.setContentType("text/html;charset=utf-8");
    //2.从浏览器获取Cookie的数组
    Cookie[] cs = request.getCookies();
    //3.判断数组中是否存在Cookie内容
    //3.1存在Cookie内容
    if (cs != null && cs.length > 0) {
        //4.设置标签判断是否存在名为lastTime的cookie，然后遍历Cookie数组
        boolean flag = false;
        for (Cookie c : cs) {
            //5.判断是否存在名为lastTime的cookie
            //5.1存在名为lastTime的cookie
            if (c.getName().equals("lastTime")) {
                //标签变为真
                flag = true;
                //5.1.1输出上次登录时间
                %>欢迎你，你上次登录的时间为<%= URLDecoder.decode(c.getValue())%><%
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
        if (flag == false) {
            firstTime(response);
            %>欢迎你，这是你的首次登录<%
        }
    }
    //不存在Cookie内容，用户首次登录
    else {
        firstTime(response);
        %>欢迎你，这是你的首次登录<%
        }
%>
<%!
    /**
     * 用户首次登录的方法
     * @param response lastTime的Cookie
     * @throws IOException
     */
    private void firstTime(HttpServletResponse response) throws IOException {
        //1.调用getCookie()的方法获取经过处理后的当前时间
        String nowTime = getCookie();
        //2.生成cookie
        Cookie lastTime = new Cookie("lastTime", nowTime);
        //3.设置过期时间为一个月
        lastTime.setMaxAge(60 * 60 * 24 * 30);
        //4.添加名为lastTime的cookie到浏览器
        response.addCookie(lastTime);
    }
%><%!
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
%>

</body>
</html>
