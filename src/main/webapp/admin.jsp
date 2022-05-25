<%@page import="com.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin Page</title>
</head>
<body>
<%
    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
    if (user == null || !user.getRoleID().equals("AD")) {
        response.sendRedirect("login.jsp");
        return;
    }
    if (user == null) {
        user = new UserDTO();
    }
    String search = request.getParameter("search");
    if (search == null) {
        search = "";
    }
%>
<h1>Welcome Manager: <%=user.getName()%>!</h1>
<a href="MainController?action=Logout">Logout</a>
<form action="MainController">
    <p>Search</p>
    <p>
        <input type="text" name="search" required value="<%=search%>"/>
        <input type="submit" name="action" value="Search"/>
    </p>
</form>
</p>
</body>
</html>
