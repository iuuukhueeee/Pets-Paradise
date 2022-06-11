<%@ page import="com.DTO.ServiceDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>shopping</title>
</head>
<body>
<%
    List<ServiceDTO> listService = (List<ServiceDTO>) request.getAttribute("LIST_SERVICE");
    if (listService == null) {
        response.sendRedirect("error.jsp");
    }

    for (ServiceDTO service : listService) {
%>
<%--    <form action="insertform.jsp" method="POST">--%>
<%--        <input type="hidden" name="ID" value="<%=service.getServiceID()%>" />--%>
<%--        <input type="hidden" name="serviceName" value="<%=service.getServiceName()%>"--%>
<%--        <input type="submit" name="service" value="InserForm" />--%>
<%--    </form>--%>
    <form action="MainController" method="POST">
        <input type="hidden" name="ID" value="<%=service.getServiceID()%>" />
        <input type="submit" name="action" value="InsertForm" />
    </form>
<%
    }
%>
</body>
</html>
