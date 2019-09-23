<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: choco
  Date: 2019/8/26
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>user list page</h2>
    <shiro:hasRole name="role1">role1</shiro:hasRole> <br>
    <shiro:hasRole name="role3">role3</shiro:hasRole> <br>
    <shiro:hasPermission name="user:add">user-add</shiro:hasPermission> <br>
    <shiro:hasPermission name="admin:add">admin-add</shiro:hasPermission> <br>

    <a href="/user/logout">退出</a>
</body>
</html>
