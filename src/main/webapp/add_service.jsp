<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.service.ServiceDTO" %>
<%@page import="java.util.List" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">


    <title>Management</title>
</head>

<body>

<div class="wrapper">
    <form action="MainController" method="post">
        <div class="h5 font-weight-bold text-center mb-3">Adding Service</div>
        </br>
        <div class="">
            <input autocomplete="off" type="text" class="form-control" placeholder="Service Name" name="serviceName">
        </div>
        </br>
        <div class="">
            <input autocomplete="off" type="number" class="form-control" placeholder="Price" name="servicePrice">
        </div>
        </br>
        <div class="">
            <input autocomplete="off" type="text" class="form-control" placeholder="Service Description"
                   name="serviceDescription">
        </div>
        </br>
        <div>${requestScope.MESSAGE}</div>
        <div class="">
            <input type="submit" class="btn btn-info" name="action" value="AddService">
        </div>
        </br>

    </form>
</div>

<%------------------------------------------------------------------%>

<%
    String search = (String) request.getAttribute("SEARCH_SERVICE");
    if (search == null) search = "";
%>

<form action="MainController">
    <input name="SearchService" placeholder="Service title" type="text" value="<%=search%>"/>
    <input type="submit" name="action" value="SearchService" aria-label="submit form"/>

</form>

</br>

<section class="grid">
    <%
        List<ServiceDTO> serviceList = (List<ServiceDTO>) request.getAttribute("SERVICE_LIST");

        if (serviceList != null) {
            if (serviceList.size() > 0) {

    %>

    <table border="2" class="table table-striped table-bordered" style="box-sizing: border-box">
        <thead>
        <tr>
            <th>No</th>
            <th>ServiceID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>

        </tr>
        </thead>
        <tbody>
        <%
            int count = 1;
            for (ServiceDTO service : serviceList) {
        %>
        <form action="MainController" method="POST">
            <tr>
                <td scope="row"><%= count++%>
                </td>

                <input type="hidden" name="ServiceID" value="<%= service.getServiceID()%>" required=""/>

                <td>
                    <input type="text" name="ServiceName" value="<%= service.getServiceName()%>" required="" size="8"/>
                </td>
                <td>
                    <input type="text" name="ServicePrice" value="<%= service.getServicePrice()%>" required=""
                           size="5"/>
                </td>
                <td>
                    <input type="text" name="ServiceDescription" value="<%= service.getServiceDescription()%>"
                           required="" size="14"/>
                </td>

                <td>
                    <a href="MainController?action=DeleteService&serviceID=<%= service.getServiceID()%>&SearchService=<%= request.getParameter("SearchService")%>">Delete</a>
                </td>
                <td>
                    <input type="submit" name="action" value="UpdateService"/>
                    <input type="hidden" name="SearchService" value="<%=search%>">
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
</section>
</body>

</html>
