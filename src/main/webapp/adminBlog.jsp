<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
    <link rel="stylesheet" href="css/dropDownAdmin.css">
    <link rel="shortcut icon" type="img/png" href="img/paw-solid.svg"/>
    <link rel="stylesheet" href="css/addBlog.css">
    <link rel="stylesheet" href="css/editBlog.css">
    <script src="https://cdn.tiny.cloud/1/ebo3g8bht1kjiuwzixu450932i6t0uhja677ht67fe1xkp3r/tinymce/6/tinymce.min.js"
            referrerpolicy="origin"></script>
    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet">
    <title>Admin - Blog</title>

</head>

<body>

<body>

<div class="sidebar">
    <div class="LOGO">
        <img src="img/paw-solid.svg" alt="" class="img-responsive">
        <div style="font-size: 20px;font-weight: 900;color: #68A7AD; justify-content: center; padding-left: 16px;">
            Pet's Paradise
        </div>
    </div>
    <div>
        <a href="./adminPoduct.jsp" class="collapsible">Product</a>
    </div>
    <div>
        <a href="./adminService.jsp" class="collapsible">Service</a>
    </div>
    <div>
        <a href="./adminUser.jsp" class="collapsible">User</a>
    </div>
    <div>
        <a href="#Blog" class="collapsible active">Blog</a>
    </div>

    <a class="collapsible" href="./adminOrder.jsp">Order</a>
</div>

<div class="res_cust">
    <div class="content">
        <div class="container">
            <div class="row height" style="padding-left:12px; padding-right:12px;">
                <br>
                <div class="searchBar col-md-12">

                    <div class="form">
                        <input type="text" class="form-control form-input" placeholder="Search...">

                        <span class="left-pan btn "><i class="fa-solid fa-magnifying-glass"></i></span>
                    </div>
                    <div></div>
                    <div style="display: flex; align-items:center;" class="Search_414rs">
                        <img class="adminIcon" src="" alt="">
                        <!-- <div class="dropdown"> -->
                        <p onclick="myFunction()" class="dropbtn" style="padding-left:12px; padding-top: 12px;">
                            Admin</p>
                        <div id="myDropdown" class="dropdown-content">
                            <a href="../../../../user.html">Profile</a>
                            <a href="#">Logout</a>
                        </div>
                        <!-- </div> -->
                    </div>
                </div>
            </div>

            <div class="container tm-mt-big tm-mb-big">
                <div class="row box_cust_rs">
                    <div class="col-xl-9 col-lg-10 col-md-10 col-sm-10">
                        <div class="">
                            <div class="row">
                                <div class="col-12">
                                    <h2 class="d-inline-block">Blog Magagement</h2>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <form action="" class="tm-edit-product-form">
                                        <div class="form-group mb-3">
                                            <div class="username row mb-4">
                                                <div class="col-md-2 d-flex align-items-center">
                                                    Author
                                                </div>
                                                <div class="col-md-10">
                                                    <div class="form-outline">
                                                        <input type="text" id="form3Example1cg" required=""
                                                               class="custom-box form-control form-control-lg pt-1"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="username row mb-4">
                                                <div class="col-md-2 d-flex align-items-center">
                                                    Blog Title
                                                </div>
                                                <div class="col-md-10">
                                                    <div class="form-outline">
                                                        <input type="text" id="form3Example1cg" required=""
                                                               class="custom-box form-control form-control-lg pt-1"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="username row mb-4">
                                                <div class="col-md-2 d-flex align-items-center">
                                                    Description
                                                </div>
                                                <div class="col-md-10">
                                                    <div class="form-outline">
                                                        <input type="text" id="form3Example1cg" required=""
                                                               class="custom-box form-control form-control-lg pt-1"/>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="username row mb-4">
                                                <div class="col-md-2 d-flex">
                                                    Content
                                                </div>
                                                <div class="col-md-10">
                                                    <form style="margin-bottom: 2rem;">
                                                        <textarea name="message" id="message" placeholder="WRITE BLOG HERE!"></textarea>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row mb-4">
                                            <div class="col-md-2"></div>
                                            <div class="col-md-10">
                                                <button class="button">Add User</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
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
                    <c:forEach var="i" items="${blog}" varStatus="loop">
                        <li class="table-row">
                            <div class="col col-1" data-label="Number">${loop.count}</div>
                            <div class="col col-2" data-label="Author">${i.getAuthor()}</div>
                            <div class="col col-3" data-label="Title">${i.getBlogTitle()}</div>
                            <div class="col col-4" data-label="Date">${i.getWrittenDate()}</div>
                            <div class="col col-5 description" data-label="Description">${i.getBlogDescription()}
                            </div>
                            <div class="col col-6" data-label="Action">
                                <i class="fa-solid fa-arrow-up"
                                   style='font-size:24px; cursor: pointer; padding-right: 5px;'></i>
                                <i class='far fa-trash-alt' style='font-size:24px ; cursor: pointer;'></i>
                            </div>
                        </li>
                    </c:forEach>

                    <%--                    <li class="table-row">--%>
                    <%--                        <div class="col col-1" data-label="Number">1</div>--%>
                    <%--                        <div class="col col-2" data-label="Author">Nguyen Van A</div>--%>
                    <%--                        <div class="col col-3" data-label="Title">Cat Running</div>--%>
                    <%--                        <div class="col col-4" data-label="Date">24/06/2022</div>--%>
                    <%--                        <div class="col col-5" data-label="Description">Lorem ipsum dolor, sit amet consectetur--%>
                    <%--                            adipisicing elit. Accusantium aut quia enim dignissimos voluptatibus eligendi, laborum natus--%>
                    <%--                            vel incidunt perspiciatis?--%>
                    <%--                        </div>--%>
                    <%--                        <div class="col col-6" data-label="Action">--%>
                    <%--                            <i class="fa-solid fa-arrow-up"--%>
                    <%--                               style='font-size:24px; cursor: pointer; padding-right: 5px;'></i>--%>
                    <%--                            <i class='far fa-trash-alt' style='font-size:24px ; cursor: pointer;'></i>--%>
                    <%--                        </div>--%>
                    <%--                    </li>--%>


                </ul>
            </div>
        </div>
    </div>
    <script src="js/admin.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        tinymce.init({
            selector: 'textarea',
            plugins: 'advlist autolink lists link image code charmap preview anchor pagebreak',
            toolbar_mode: 'floating | image code | undo redo',
            entity_encoding: 'raw',

            images_upload_url: 'UploadBlogImageController',
        });
    </script>
</div>
</body>
</body>

</html>