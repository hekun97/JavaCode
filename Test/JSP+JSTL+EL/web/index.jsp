<%@ page import="io.gitee.hek97.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--1.导入jstl的相关jar包并引入jstl标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>JSP+JSTL+EL获取User对象的值</title>
</head>
<body>
<%
  List<User> users = new ArrayList<>();
  //第一名人员信息
  User user1 = new User();
  user1.setId(1001);
  user1.setUsername("张三");
  user1.setPassword("123");
  //第二名人员信息
  User user2 = new User();
  user2.setId(1002);
  user2.setUsername("李四");
  user2.setPassword("456");
  //将user添加到List集合中
  users.add(user1);
  users.add(user2);
  //将信息存储到共享域中
  request.setAttribute("users",users);
%>
<%--视图V:表格展示--%>
<table border="1">
 <%--表头--%>
  <tr>
    <th>索引</th>
    <th>循环次数</th>
    <th>用户id</th>
    <th>用户名</th>
    <th>用户密码</th>
  </tr>

<%--表体--%>
  <%--遍历users集合容器--%>
  <c:forEach items="${users}" var="user" varStatus="s">
    <tr>
      <td>${s.index}</td>
      <td>${s.count}</td>
      <td>${user.id}</td>
      <td>${user.username}</td>
      <td>${user.password}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>