<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<c:set var="LOGIN_USER" value="${sessionScope['LOGIN_USER']}" />
<c:choose>
    <c:when test="${LOGIN_USER.getRoleID().equals('AD') == false}">
        <c:redirect url="login.jsp"/>
    </c:when>
    <c:when test="${LOGIN_USER.getRoleID() == null}">
        <c:redirect url="login.jsp"/>
    </c:when>
</c:choose>
<head>
    <script src="https://cdn.tiny.cloud/1/ebo3g8bht1kjiuwzixu450932i6t0uhja677ht67fe1xkp3r/tinymce/6/tinymce.min.js"
            referrerpolicy="origin"></script>
</head>

<body>

<form action="MainController" method="POST">
    <input type="text" name="title" id="title"/>
    <textarea name="message" id="message" placeholder="WRITE BLOG HERE!">
    </textarea>
    <input type="text" name="description" id="description"/>
    <button type="submit" name="action" value="UploadBlog">UPLOAD!</button>

</form>

<c:set var="blog" value="${requestScope['LIST_BLOG']}" />

<table>
    <thead>
        <tr>yeyeyey</tr>
    </thead>
    <tbody>
        <c:forEach var="i" items="${blog}">
            <tr>
                <td>${i.getAuthor()}</td>
                <td>${i.getWrittenDate()}</td>
                <td>${i.getBlogTitle()}</td>
                <td>
                    <form method="POST">
                        <input type="hidden" id="blogID" name="ID" value="${i.getBlogID()}" class="blogID"/>
                        <input type="button" id="getContent" name="GET" value="GET" class="GET"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<script>
    tinymce.init({
        selector: 'textarea',
        plugins: 'advlist autolink lists link image code charmap preview anchor pagebreak',
        toolbar_mode: 'floating | image code | undo redo',
        entity_encoding: 'raw',

        images_upload_url: 'UploadBlogImageController',
    });
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="./js/handleGetBlog.js"></script>
</body>
</html>