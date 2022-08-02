<%@ page import="com.DTO.UserDTO" %>
<%@ page import="com.DTO.BlogDTO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
    />
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    />
    <link rel="stylesheet" href="./css/dropDownAdmin.css"/>
    <link rel="shortcut icon" type="img/png" href="./img/paw-solid.svg"/>
    <link rel="stylesheet" href="./css/addBlog.css"/>
    <script
            src="https://cdn.tiny.cloud/1/ebo3g8bht1kjiuwzixu450932i6t0uhja677ht67fe1xkp3r/tinymce/6/tinymce.min.js"
    ></script>
    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet"
    />
    <title>Admin - Blog</title>
</head>

<body>

<%
    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
    BlogDTO blog = (BlogDTO) request.getAttribute("BLOG");
    String method;
    if (blog == null) {
        method = "create";
        blog = new BlogDTO();
    } else {
        method = "update";
    }
%>

<div class="sidebar">
    <div class="LOGO">
        <img src="./img/paw-solid.svg" alt="" class="img-responsive"/>
        <div
                style="
            font-size: 20px;
            font-weight: 900;
            color: #68a7ad;
            justify-content: center;
            padding-left: 16px;
          "
        >
            Pet's Paradise
        </div>
    </div>
    <div>
        <a href="admin" class="collapsible ">DashBoard</a>
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

<div class="res_cust">
    <div class="content">
        <div class="container tm-mt-big tm-mb-big">
            <div class="row box_cust_rs">
                <div class="col-xl-9 col-lg-10 col-md-10 col-sm-10">
                    <div class="">
                        <div class="row">
                            <div class="col-12">
                                <h2 class="d-inline-block">Blog Management</h2>
                            </div>
                        </div>

                        <form action="MainController" method="POST" class="tm-edit-product-form">
                            <input type="hidden" name="ID" value="<%=blog.getBlogID()%>" />
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group mb-3">
                                        <div class="username row mb-4">
                                            <div class="col-md-2 d-flex align-items-center">
                                                Author
                                            </div>
                                            <div class="col-md-10">
                                                <div class="form-outline">
                                                    <input
                                                            type="text"
                                                            id="form3Example1cg"
                                                            required=""
                                                            class="custom-box form-control form-control-lg pt-1"
                                                            name="author"
                                                            value="<%=user.getName()%>"
                                                    />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="username row mb-4">
                                            <div class="col-md-2 d-flex align-items-center">
                                                Blog Title
                                            </div>
                                            <div class="col-md-10">
                                                <div class="form-outline">
                                                    <input
                                                            type="text"
                                                            id="form3Example1cg"
                                                            required=""
                                                            class="custom-box form-control form-control-lg pt-1"
                                                            name="title"
                                                            value="<%=blog.getBlogTitle()%>"
                                                    />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="username row mb-4">
                                            <div class="col-md-2 d-flex align-items-center">
                                                Description
                                            </div>
                                            <div class="col-md-10">
                                                <div class="form-outline">
                                                    <input
                                                            type="text"
                                                            id="form3Example1cg"
                                                            required=""
                                                            class="custom-box form-control form-control-lg pt-1"
                                                            name="description"
                                                            value="<%=blog.getBlogDescription()%>"
                                                    />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="username row mb-4">
                                            <div class="col-md-2 d-flex">Content</div>
                                            <div class="col-md-10">
                                                <form style="margin-bottom: 2rem">
                                                    <textarea name="message"
                                                              id="message"><%=blog.getBlogContent()%></textarea>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mb-4">
                                        <div class="col-md-2"></div>
                                        <div class="col-md-10">
                                            <%
                                                if ("create".equals(method)) {
                                            %>
                                            <button class="button" name="action" value="CreateBlog">Add Blog</button>
                                            <%
                                            } else {
                                            %>
                                                <button class="button" name="action" value="UpdateBlog">Update Blog</button>
                                            <%
                                                }
                                            %>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
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
