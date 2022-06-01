<%@ page import="com.blog.BlogDTO" %><%--
  Created by IntelliJ IDEA.
  User: iuuukhueeee
  Date: 5/31/2022
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        BlogDTO blog = (BlogDTO) request.getAttribute("BLOG_CONTENT");
        if (blog == null) {
            response.sendRedirect("blog");
            return;
        }
    %>
    <title><%=blog.getBlogTitle()%></title>
</head>
<body>
    <%=blog.getBlogContent()%>

</body>
</html>
