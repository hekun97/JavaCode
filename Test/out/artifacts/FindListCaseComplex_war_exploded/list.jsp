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


    window.onload = function () {
        //给删除选中按钮添加单击事件
        //给删除选中按钮(id=delSelected)添加单击事件，触发函数function()
        document.getElementById("delSelected").onclick = function () {
            if (confirm("你确定要删除选中信息吗？")) {
                var flag = false;
                var cbs = document.getElementsByName("uid");
                for (var i = 0; i < cbs.length; i++) {
                    if (cbs[i].checked) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    //将复选框的值(name=uid,value=用户的id)通过表单(id=form)提交到路径/delSelectUser
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
        <a class="btn btn-danger btn-sm" href="javascript:void(0);" id="delSelected">删除选中</a>
    </div>
    <form id="form" action="${pageContext.request.contextPath}/delSelectServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pb.list}" var="user" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="uid" value="${user.id}"></td>
                    <td>${s.count + (pb.currentPage-1)*pb.rows}</td>
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
    </form>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${pb.currentPage==1}">
                <li class="previous disabled">
                    <a href="#"
                       aria-label="Previous">
                        <span aria-hidden="false">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${pb.currentPage!=1}">
                <li>
                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage-1}"
                       aria-label="Previous">
                        <span aria-hidden="false">&laquo;</span>
                    </a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${pb.totalPage}" var="i" step="1">
                <c:if test="${pb.currentPage==i}">
                    <li class="active">
                </c:if>
                <c:if test="${pb.currentPage!=i}">
                    <li>
                </c:if>
                <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}">${i}</a></li>
            </c:forEach>
            <c:if test="${pb.currentPage==pb.totalPage}">
                <li class="previous disabled">
                    <a href="#"
                       aria-label="Previous">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${pb.currentPage!=pb.totalPage}">
                <li>
                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage+1}"
                       aria-label="Previous">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <span style="font-size: 25px;margin-left: 5px">共 ${pb.totalCount} 条记录，共 ${pb.totalPage} 页</span>
        </ul>
    </nav>

</div>
</body>
</html>
