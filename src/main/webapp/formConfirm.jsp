<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>cart</title>
</head>
<body>
    <%
        String url = (String) request.getAttribute("IMG_URL");
    %>
    <img src="<%=url%>"/>
</body>
</html>
