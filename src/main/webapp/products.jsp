<%@ page import="com.DTO.ProductDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ADMINS
  Date: 6/26/2022
  Time: 7:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>shopping2</title>
</head>
<body>
<%
    List<ProductDTO> listProduct = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
    if (listProduct == null) {
        response.sendRedirect("error.jsp");
    }
    for (ProductDTO product : listProduct) {
%>
<%--    <form action="insertForm.jsp" method="POST">--%>
<%--        <input type="hidden" name="ID" value="<%=service.getServiceID()%>" />--%>
<%--        <input type="hidden" name="serviceName" value="<%=service.getServiceName()%>"--%>
<%--        <input type="submit" name="service" value="InserForm" />--%>
<%--    </form>--%>
<form action="MainController" method="POST" enctype="multipart/form-data">
    <input type="hidden" name="ID" value="<%=product.getProductID()%>" />
     <div><%= product.getName() %></div>
    <div><%= product.getPrice() %></div>
    <div><%= product.getImportDate() %></div>
    <div><%= product.getExpiredDate() %></div>
    <input type="number" name="quantity" value="" placeholder="quantity">
    <input type="file" name="picture" />
    <input type="submit" name="action" value="AddToCart" />
</form>
<%
    }
%>
<a href="checkout.jsp">checkout</a>
</body>
</html>
