<%@ page import="com.DTO.ShopDTO" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.DTO.ServiceDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>form</title>
</head>
<body>

<form action="MainController" method="POST" enctype="multipart/form-data">
    <select name="shop">
        <%
            Map<String, ShopDTO> map = (Map<String, ShopDTO>) request.getAttribute("MAP_SUPPORTED_SHOP");
            if (map != null) {
                for (String key : map.keySet()) {
        %>
        <option value="<%=map.get(key).getShopID()%>"><%=map.get(key).getShopName()%>
        </option>
        <%
                }
            }
        %>
    </select>
    <%

        ServiceDTO service = (ServiceDTO) request.getAttribute("SERVICE");

    %>
    <p><%=service.getServiceName()%>
    </p>
    <input type="file" name="picture" />
    <select id="cars" name="animalType" >
        <option value="cat">cat</option>
        <option value="dog">dog</option>
    </select>
    <input type="text" name="animalName" placeholder="animalName" value="">
    <input type="text" name="animalAge" placeholder="animalAge" value="">
    <input type="text" name="animalDescription" placeholder="animalDescription" value="">
    Date<input type="date" name="bookingTime" value="">
    <input type="hidden" name="ID" value="<%=service.getServiceID()%>" />
    <input type="submit" name="action" value="AddToCart" />
</form>
</body>
</html>
