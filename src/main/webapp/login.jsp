<%--
  Created by IntelliJ IDEA.
  User: iuuukhueeee
  Date: 5/24/2022
  Time: 2:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form method="POST" action="MainController">
        Username: <input type="text" name="username">
        Password: <input type="password" name="password">
        <input type="submit" name="action" value="Login"/>
    </form>
</body>
</html>
