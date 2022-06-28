<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot my password</title>
</head>
<body>
  <form action="MainController" method="POST">
      <input type="text" name="username" placeholder="username"/>
      <input type="submit" name="action" value="ResetPassword" />
  </form>
</body>
</html>
