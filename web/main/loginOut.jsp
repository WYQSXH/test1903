<%--
  Created by IntelliJ IDEA.
  User: wangyongqi
  Date: 2021/5/14
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    out.print("<script>alert('账号已退出');window.location.href='/Demo13Web/main/login.html'</script>");
    session.invalidate();

%>
</body>
</html>
