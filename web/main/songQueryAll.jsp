<%@ page import="com.FinalTerm.dao.SongDao" %>
<%@ page import="com.FinalTerm.entity.Songs" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: wangyongqi
  Date: 2021/5/18
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
<table border="1">
    <tr>
        <td>名称</td>
        <td>歌手</td>
        <td>图片</td>
        <td>歌曲</td>
        <td>操作</td>
    </tr>
    <%
        List<Songs> list =(List)request.getAttribute("songs");
        for(Songs s:list){
    %>
    <tr>
        <td><%=s.getSongName() %></td>
        <td><%=s.getSinger() %></td>
        <td><img  style="width:50px;height:50px"  src="<%=s.getImgUrl()%>"></td>
        <td><audio src="<%=s.getSongUrl()%>" controls='controls'>
            您的浏览器不支持 audio 标签。
        </audio></td>
        <td><a href="/Demo13Web/song/delete?songId=<%=s.getId()%>">删除</a> </td>
    </tr>
    <% }%>
</table>
</center>
</body>
</html>
