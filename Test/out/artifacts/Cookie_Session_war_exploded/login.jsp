<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<script>
    window.onload=function () {
        //1.获取图片对象
        var img =document.getElementById("checkCode");
        //2.绑定单击事件
        img.onclick=function () {
            //加时间戳
            var date = new Date().getTime();
            img.src = "/today/demo?"+date;//时间戳可以防止浏览器认读取本地缓存的图片
        }

    }
</script>
<head>
    <title>登录</title>
</head>
<body>
<form action="/today/loginServlet" method="post">
    用户名:<input type="text" name="username"> <br>
    密码:<input type="password" name="password"><br>
    验证码：<input type="text" name="judeCode">
    <img id="checkCode" src="/today/checkCodeServlet"/><br>
    <input type="submit" value="登录">
</form>
<div><%=request.getAttribute("code_error")==null?"":request.getAttribute("code_error")%></div>
<div><%=request.getAttribute("login_error")==null?"":request.getAttribute("login_error")%></div>
</body>
</html>
