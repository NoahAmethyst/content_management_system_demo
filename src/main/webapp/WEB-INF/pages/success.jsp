<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="amethyst.po.sys.User " %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>success page</h2>
    <%
        User user = (User)SecurityUtils.getSubject().getPrincipal();
    %>
<%=user%>
</body>
</html>
