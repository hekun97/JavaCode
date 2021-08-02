<%@ page import="io.gitee.hek97.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<script>
    //删除单条用户的安全提示
    function deleteUser(id) {
        //confirm弹出确认框，确定返回true，取消返回false
        if (confirm("你确定要删除该条用户信息")) {
            //访问路径
            location.href = "${pageContext.request.contextPath}/userDeleteServlet?id=" + id;
        }
    }

    //当页面加载完成后
    window.onload = function () {
        //1.给删除选中按钮(id=delSelected)添加单击事件，触发函数function()
        document.getElementById("delSelected").onclick = function () {
            //2.提示信息
            if (confirm("你确定要删除选中信息吗？")) {
                //3.设置没有复选框被勾选(状态设置为假)
                var flag = false;
                //4.获取复选框(name="uid")地址的数组
                var cbs = document.getElementsByName("uid");
                //5.循环判断数组中的复选框的状态是否是被勾选
                for (var i = 0; i < cbs.length; i++) {
                    //6.如果存在被勾选的复选框(状态设置为真)，并结束循环
                    if (cbs[i].checked) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    //7.如果状态为真，将复选框的值(name=uid,value=用户的id)通过表单(id=form)提交到路径/delSelectUser，进行删除选中用户的操作
                    document.getElementById("form").submit();
                }
            }
        }
        //设置全选和反选
        document.getElementById("firstCb").onclick = function () {
            //获取用户复选框数组
            var cbs = document.getElementsByName("uid");
            //遍历用户复选框数组
            for (var i = 0; i < cbs.length; i++) {
                //设置用户复选框状态和firstCb的状态保持一致
                cbs[i].checked = this.checked;
            }
        }

    }
</script>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: left;">
        <form class="form-inline">
            <div class="form-group">
                <label for="exampleInputName1">姓名</label>
                <input type="text" class="form-control" id="exampleInputName1" placeholder="Jane Doe">
            </div>
            <div class="form-group">
                <label for="exampleInputName2">籍贯</label>
                <input type="text" class="form-control" id="exampleInputName2" placeholder="Jane Doe">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="email" class="form-control" id="exampleInputEmail2" placeholder="jane.doe@example.com">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right;margin: 5px">
        <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/add.jsp">添加用户</a>&nbsp;
        <%--这里通过单击事件调用JavaScript的方法进行判断后删除用户--%>
        <a class="btn btn-danger btn-sm" href="javascript:void(0);" id="delSelected">删除选中</a>
    </div>
    <%--通过一个form表单包裹用户列表，通过Post可以把多个复选框的值（用户id数组）提交到/delSelectServlet路径的servlet中，从而删除用户--%>
    <form id="form" action="${pageContext.request.contextPath}/delSelectServlet" method="post">
        <%--表格开始--%>
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <%--表格头部复选框--%>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <%--遍历request域中PageBean的List集合，获取用户信息--%>
            <c:forEach items="${pb.list}" var="user" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="uid" value="${user.id}"></td>
                        <%--表体复选框--%>
                    <td>${s.count + (pb.currentPage-1)*pb.rows}</td>
                        <%--计算用户的编号，计算公式：循环次数+(当前页码-1)*每页显示行数--%>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td>
                        <a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id});">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <%--表格结束--%>
    </form>
    <%--分页开始--%>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <%--上一页开始--%>
            <%--当request域中的当前页为1时，禁用上一页--%>
            <c:if test="${pb.currentPage==1}">
                <li class="previous disabled">
                    <a href="#" aria-label="Previous"><%--超链接内容为#号，可禁止当前页面刷新--%>
                        <span aria-hidden="false">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <%--不等于1时，可用上一页--%>
            <c:if test="${pb.currentPage!=1}">
                <li>
                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage-1}"
                       aria-label="Previous">
                        <span aria-hidden="false">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <%--上一页结束--%>
            <%--分页条开始--%>
            <%--分页条的多少为从request域对象中从1到总页码--%>
            <c:forEach begin="1" end="${pb.totalPage}" var="i" step="1">
                <%--当request域中的当前页码等于循环的临时变量时，该分页条为激活状态--%>
                <c:if test="${pb.currentPage==i}">
                    <li class="active">
                </c:if>
                <%--当不等于时，分页条为默认状态（非激活）--%>
                <c:if test="${pb.currentPage!=i}">
                    <li>
                </c:if>
                <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}">${i}</a></li>
            </c:forEach>
            <%--分页条结束--%>
            <%--下一页开始--%>
            <%--当当前页等于总页码时，禁用下一页--%>
            <c:if test="${pb.currentPage==pb.totalPage}">
                <li class="previous disabled">
                    <a href="#"
                       aria-label="Previous">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <%--不等于时，下一页可用--%>
            <c:if test="${pb.currentPage!=pb.totalPage}">
                <li>
                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage+1}"
                       aria-label="Previous">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <%--下一页结束--%>
            <%--从request域PageBean中获取总记录数和页数--%>
            <span style="font-size: 25px;margin-left: 5px">共 ${pb.totalCount} 条记录，共 ${pb.totalPage} 页</span>
        </ul>
    </nav>
    <%--分页结束--%>
</div>
</body>
</html>
