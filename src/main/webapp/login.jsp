<%--
<<<<<<< HEAD
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
=======
    Document   : login
    Created on : Mar 11, 2022, 9:02:11 PM
    Author     : hd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>
<form action="MainController" method="POST">
    <fieldset>
        <legend><h2>Login</h2></legend>
        <table>
            <tr>
                <td>Username</td>
                <td>
                    <input type="text" required name="Username" placeholder="Username"/>
                </td>
            </tr>
            <tr>
                <td>Password</td>
                <td>
                    <input type="password" required name="Password" placeholder="Password"/>
                </td>
            </tr>
        </table>
        <p>
            <input type="submit" name="action" value="Login"/>
            <input type="reset" value="Reset"/>
        </p>
        <%
            String error = (String) request.getAttribute("ERROR");
            if (error == null) {
                error = "";
            }
        %>
        <p><%=error%></p>

    </fieldset>
</form>
>>>>>>> Login/out
</body>
</html>
