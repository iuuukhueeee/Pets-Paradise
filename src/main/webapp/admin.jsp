<%@page import="java.util.List" %>
<%@page import="com.DTO.UserDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
<form action="MainController" method="POST">
    <input name="SearchUser" placeholder="User title" type="text"/>
    <input type="submit" name="action" value="SearchUser" aria-label="submit form"/>

</form>
</br>
<%
    List<UserDTO> listUser = (List<UserDTO>) request.getAttribute("LIST_USER");
    if (listUser != null) {
        if (listUser.size() > 0) {
%>
<table border="2" class="table table-striped ">
    <thead>
    <tr>
        <th>No</th>
        <th>Username</th>
        <th>Name</th>
        <th>Password</th>
        <th>Email</th>
        <th>Phone</th>
        <th>RoleID</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <%
        int count = 1;
        for (UserDTO User : listUser) {
    %>
    <form action="MainController" method="POST">
        <tr>
            <td scope="row"><%= count++%>
            </td>
            <td>
                <input type="text" name="userName" value="<%= User.getUsername()%>" required=""/>
            </td>
            <td>
                <input type="text" name="name" value="<%= User.getName()%>" required="" size="8"/>
            </td>
            <td>
                <input type="password" name="password" value="<%= User.getPassword()%>" required="" size="8"/>
            </td>
            <td>
                <input type="text" name="email" value="<%= User.getEmail()%>" required="" size="8"/>
            </td>
            <td>
                <input type="text" name="phoneNumber" value="<%= User.getPhoneNumber()%>" required="" size="10"/>
            </td>
            <td>
                <input type="text" name="roleID" value="<%= User.getRoleID()%>" required="" size="5"/>
            </td>

            <td>
                <a href="MainController?action=DeleteUser&userName=<%= User.getUsername()%>&SearchUser=<%= request.getParameter("SearchUser")%>">Delete</a>
            </td>
            <td>
                <input type="submit" name="action" value="UpdateUser"/>
            </td>
        </tr>
    </form>

    <%
        }
    %>
    </tbody>
</table>

<%
    }
%>

<%
    }
%>
<a href="MainController?action=Logout">Logout</a>


<a href="add_service.jsp">Service</a>
<a href="product.jsp">Product</a>
</body>
</html>
