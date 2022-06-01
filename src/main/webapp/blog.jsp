<%@ page import="com.blog.BlogDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        List<BlogDTO> listBlog = (List<BlogDTO>) request.getAttribute("LIST_BLOG");

        if (listBlog != null && listBlog.size() > 0) {
            for (int i = 0; i < listBlog.size(); i++) {
    %>
            <div class="blog-container">
                <p><%=listBlog.get(i).getAuthor()%>
                </p>
                <img src="./img/<%=listBlog.get(i).getAuthorAvatar()%>"/>
                <p><%=listBlog.get(i).getBlogTitle()%>
                </p>
                <p><%=listBlog.get(i).getBlogDescription()%>
                </p>
                <p><%=listBlog.get(i).getWrittenDate()%>
                </p>
                <p><%=listBlog.get(i).getAuthor()%>
                </p>
                <a href="BlogContentController?blogID=<%=listBlog.get(i).getBlogID()%>">hiii</a>
            </div>
    <%
            }

    }
%>
</body>
</html>
