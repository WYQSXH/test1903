<%--
  Created by IntelliJ IDEA.
  User: wangyongqi
  Date: 2021/5/14
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的信息</title>
</head>

<link rel="stylesheet" type="text/css" href="/Demo13Web/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/Demo13Web/css/nav.css" />
<body>

<!-- 导航栏 -->
<div class="container">
    <div class="nav col-md-12">
        <ul class="nav-ul col-md-8">
            <li class="nav-li col-md-3 col-sm-3">
                <a href="/Demo13Web/main/index.jsp">首页</a>
            </li>
            <li class="nav-li col-md-3 col-sm-3">
                <a href="/Demo13Web/main/songAdd.jsp">添加歌曲</a>
            </li>
            <li class="nav-li col-md-3 col-sm-3">
                <a href="/Demo13Web/song/query">我的歌曲</a>
            </li>
            <li class="nav-li col-md-3 col-sm-3">
                <a href="/Demo13Web/main/userQuery.jsp">个人信息</a>
            </li>
        </ul>
        <ul class="nav-ul col-md-4">
            <li class="nav-li col-md-2 col-sm-2 col-md-offset-2">
                <img src="/Demo13Web/images/登录.png">
            </li>
            <li class="nav-li col-md-7 col-sm-7">
                <a href="/Demo13Web/main/loginOut.jsp" id="loginname">欢迎您：${sessionScope.user.getUserName()}</a>
            </li>
        </ul>
    </div>
</div>
<center>
<form action="/Demo13Web/user/update" method="post">
    <table border="2">
        <tr>
            <td>账号</td>
            <td><input type="text" value="${sessionScope.user.getLoginName()}" readonly  name="loginName"></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td><input type="text" value="${sessionScope.user.getUserName()}" name="userName"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="text" value="${sessionScope.user.getPassword()}" name="password"></td>
        </tr>
        <tr>
            <td>性别</td>
            <td><input type="text" value="${sessionScope.user.getSex()}" name="sex"></td>
        </tr>
        <tr>
            <td>邮箱</td>
            <td><input type="text" value="${sessionScope.user.getEmail()}" name="email"></td>
        </tr>
        <tr>
            <td>操作</td>
            <td align="center"><input type="submit" value="修改"></td>
        </tr>
    </table>
</form>
</center>
</body>
</html>
