<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
        <p><%=error%>
        </p>

    </fieldset>
</form>
</body>
</html>
