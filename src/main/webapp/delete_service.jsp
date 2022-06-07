<%-- Document : admin Created on : Mar 5, 2022, 2:10:55 PM Author : PC --%>


<%@page import="java.util.List"%>
<%@page import="com.service.ServiceDTO"%>
<%@page import="com.service.ServiceDAO"%>
<%@page import="com.user.UserDTO"%>
<%@page import="com.user.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Admin</title>
</head>

<body>

        <form action="MainController">
            <input type="text" placeholder="Search Service" name="searchService" >
            <button type="submit" name="action" value="SearchService" aria-label="submit form">
                <svg aria-hidden="true">
                    <use xlink:href="#search"></use>
                </svg>
            </button>
        </form>


    <section class="grid">
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"AD".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }

        %>
        <h1 class="admin" >Admin: <%= loginUser.getName()%> </h1> </br>

        <%
            List<ServiceDTO> listService = (List<ServiceDTO>)request.getAttribute("LIST_SERVICE");
            if (listService != null) {
                if (listService.size() > 0) {
        %>
        <table border="2" class="table table-striped ">
            <thead>
            <tr>
                <th>No</th>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Description</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <%
                int count = 1;
                for (ServiceDTO service : listService) {
            %>
            <tr>
                <td scope="row"><%= count++%></td>
                <td><%= user.getServiceID()%></td>
                <td><%= user.getServiceName()%></td>
                <td><%= user.getServicePrice()%></td>
                <td><%= user.getServiceDescription()%></td>

                <!--delete-->
                <td>
                    <a href="MainController?action=DeleteService&serviceID=<%= service.getServiceID()%>&search=<%= search%>">Delete</a></td>
            </tr>
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
    </section>


</body>

</html>