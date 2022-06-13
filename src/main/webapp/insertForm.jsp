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
    <textarea placeholder="Pet's information"></textarea>
    <input type="file" name="picture" />
    <input type="hidden" name="ID" value="<%=service.getServiceID()%>" />
    <input type="submit" name="action" value="AddToCart" />
</form>
</body>
</html>
