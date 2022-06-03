
<%@page import="com.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <title>Register Page</title>
</head>
<body>
<%
  UserError error = (UserError) request.getAttribute("USER_ERROR");
  if (error == null) {
    error = new UserError();
  }
%>

<div class="container">
  <div class="content">
    <div class="title">REGISTER</div>

    <b style="color:red"><%= error.getUserNameError()%></b>
    <b style="color:red"><%= error.getNameError()%></b>
    <b style="color:red"><%= error.getPasswordError()%></b>
    <b style="color:red"><%= error.getEmailError()%></b>
    <b style="color:red"><%= error.getPhoneNumberError()%></b>
    <b style="color:red"><%= error.getRoleIDError()%></b>


    <form action="MainController" method="POST">
      <input type="text" name="userName" required="" placeholder="Create UserName"/>
      <input type="text" name="name" required="" placeholder="Your Full Name"/>
      <input type="password" name="password" required="" placeholder="Password"/>
      <input type="text" name="email" required="" placeholder="email"/>
      <input type="text" name="phoneNumber" required="" placeholder="Your phone number"/>
      <input type="text" name="roleID" value="US" readonly="" placeholder="RoleID"/>

      <input class="button btn btn-success p-3" type="submit" name="action" value="AddUser"/>
      <div class="rounded">
        <input class="btn btn-light mx-auto w-100 " type="reset" value="Reset"/>
        <a class="btn btn-light mx-auto w-100 " type="reset" href="login.jsp">Login</a>
      </div>

    </form>
  </div>
</div>
</body>
</html>
