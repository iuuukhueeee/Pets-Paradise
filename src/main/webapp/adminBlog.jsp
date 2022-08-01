<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
    <link rel="stylesheet" href="./css/dropDownAdmin.css"/>
    <link rel="shortcut icon" type="img/png" href="../img/paw-solid.svg"/>
    <link rel="stylesheet" href="./css/addBlog.css"/>
    <script src="https://cdn.tiny.cloud/1/ebo3g8bht1kjiuwzixu450932i6t0uhja677ht67fe1xkp3r/tinymce/6/tinymce.min.js"
            referrerpolicy="origin"></script>
    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet"/>
    <title>Admin - Blog</title>
</head>

<body>

<style>
    button {
        background-color: transparent;
        background-repeat: no-repeat;
        border: none;
        cursor: pointer;
        overflow: hidden;
        outline: none;
    }
</style>

<div class="sidebar">
    <div class="LOGO">
        <img src="./img/paw-solid.svg" alt="" class="img-responsive"/>
        <div style="
            font-size: 20px;
            font-weight: 900;
            color: #68a7ad;
            justify-content: center;
            padding-left: 16px;
          ">
            Pet's Paradise
        </div>
    </div>
    <div>
        <a href="admin" class="collapsible">DashBoard</a>
    </div>
    <div>
        <a class="collapsible" href="AdminOrder">Order</a>
    </div>
    <div>
        <a href="AdminProduct" class="collapsible">Product</a>
    </div>
    <div>
        <a href="AdminService" class="collapsible">Service</a>
    </div>
    <div>
        <a href="AdminUser" class="collapsible">User</a>
    </div>
    <div>
        <a href="AdminBlog" class="collapsible active">Blog</a>
    </div>
</div>

<div class="content">
    <div class="container">
        <div class="row height d-flex align-items-center">
            <div class="col-md-12 searchBar">

                <%
                    String search = request.getParameter("search");
                    if (search == null) search = "";
                %>

                <div class="form">
                    <form action="MainController" method="POST">
                        <input type="text" class="form-control form-input" placeholder="Search..." name="search"
                               value="<%=search%>"/>
                        <span class="left-pan btn"><i class="fa-solid fa-magnifying-glass"></i></span>
                    </form>
                </div>
                <div style="display: flex; align-items: center; padding-left: 10px">
                    <p onclick="myFunction()" class="dropbtn" style="padding-left: 12px; padding-top: 12px">
                        Admin
                    </p>
                    <div id="myDropdown" class="dropdown-content">
                        <a href="./user.jsp">Profile</a>
                        <a href="#">Logout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br/>
    <div class="container tm-mt-big tm-mb-big">
        <div class="row">
            <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12">
                <div class="row">
                    <div class="col-12">
                        <h2 class="d-inline-block">Blog Management</h2>
                    </div>
                </div>
                <div class="tm table-h-auto tm-block-h-auto">
                    <div class="col-xl-6 col-lg-6 col-md-12"></div>
                    <div class="mt-3 mb-3">
                        <a href="./adminAddBlog.jsp" target="popup"
                           onclick="window.open('./adminAddBlog.jsp','_blank','fullscreen=yes','true')">
                            <button class="button" style="margin-left: 2px; background-color: #51969c">
                                Create Blog
                            </button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="t-container">
        <div class="container">
            <ul class="responsive-table">
                <li class="table-header">
                    <div class="col col-1">#</div>
                    <div class="col col-2">Author</div>
                    <div class="col col-3">Title</div>
                    <div class="col col-4">Date</div>
                    <div class="col col-5 text-center">Description</div>
                    <div class="col col-6">Action</div>
                </li>


                <c:set var="blog" value="${requestScope['LIST_BLOG']}"/>
                <c:if test="${blog.size() > 0 && blog != null}">
                    <c:forEach var="i" items="${blog}" varStatus="loop">
                        <li class="table-row">
                            <div class="col col-1" data-label="Number">${loop.count}</div>
                            <div class="col col-2" data-label="Author">${i.getAuthor()}</div>
                            <div class="col col-3" data-label="Title">${i.getBlogTitle()}</div>
                            <div class="col col-4" data-label="Date">${i.getWrittenDate()}</div>
                            <div class="col col-5 description" data-label="Description">
                                    ${i.getBlogDescription()}
                            </div>
                            <div class="col col-6" data-label="Action">
                                <a href="AdminAddBlog?method=update&ID=${i.getBlogID()}" target="popup"
                                   style="text-decoration: none"
                                   onclick="window.open('AdminAddBlog?method=update&ID=${i.getBlogID()}','_blank','fullscreen=yes','true')">
                                    <i class="fa-solid fa-arrow-up"
                                       style="font-size: 24px;cursor: pointer;padding-right: 5px;color: black;"></i>
                                </a>
                                <form method="POST" action="MainController">
                                    <input type="hidden" id="blogID" name="ID" value="${i.getBlogID()}"
                                           class="blogID"/>
                                    <button name="action" value="DeleteBlog">
                                        <i class="far fa-trash-alt" style="font-size: 24px; cursor: pointer"></i>
                                    </button>
                                </form>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<script src="./js/admin.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    tinymce.init({
        selector: "textarea",
        plugins:
            "advlist autolink lists link image code charmap preview anchor pagebreak",
        toolbar_mode: "floating | image code | undo redo",
        entity_encoding: "raw",

        images_upload_url: "UploadBlogImageController",
    });
</script>
</body>

</html>
