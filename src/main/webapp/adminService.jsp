<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.DTO.UserDTO" %>
<%@ page import="com.DTO.ServiceDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.DTO.ShopDTO" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%
        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
        if (loginUser == null || !loginUser.getRoleID().equals("AD")) {
            response.sendRedirect("login.jsp");
            return;
        }
        String search = request.getParameter("SearchService");
        if (search == null) {
            search = "";
        }
        List<ShopDTO> listShop = (List<ShopDTO>) request.getAttribute("SHOP_LIST");
    %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
    <link rel="stylesheet" href="css/dropDownAdmin.css">
    <link rel="shortcut icon" type="img/png" href="img/paw-solid.svg"/>
    <link rel="stylesheet" href="css/addService.css">
    <link rel="stylesheet" href="css/editService.css">
    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet">
    <title>Admin - Service</title>

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
        <img src="img/paw-solid.svg" alt="" class="img-responsive">
        <div style="font-size: 20px;font-weight: 900;color: #68A7AD; justify-content: center; padding-left: 16px;">
            Pet's Paradise
        </div>
    </div>
    <div>
        <a href="AdminProduct" class="collapsible">Product</a>
    </div>
    <div>
        <a href="#AdminService" class="collapsible active">Service</a>
    </div>
    <div>
        <a href="AdminUser" class="collapsible">User</a>
    </div>
    <div>
        <a href="AdminBlog" class="collapsible">Blog</a>
    </div>

    <a class="collapsible" href="AdminOrder">Order</a>

    <a class="collapsible Addmin_414rs">Admin</a>
    <div class="contentCollapse">
        <a href="" class="unhoverContent text-muted">
            <p>Profile</p>
        </a>
        <a href="./.html" class="unhoverContent text-muted">
            <p>Logout</p>
        </a>
    </div>

</div>
<div class="content">
    <div class="container">
        <div class="row height d-flex justify-content-center align-items-center">

            <div class=" searchBar col-xl-9 col-lg-10 col-md-10 col-sm-10 mx-auto">
                <form action="MainController" method="post">
                    <div class="form">
                        <input type="text" class="form-control form-input" placeholder="Search..." name="SearchService"
                               value="<%=search%>">
                        <span class="left-pan btn ">
                            <button name="action" value="SearchService">
                                <i class="fa-solid fa-magnifying-glass"></i>
                            </button>

                        </span>
                    </div>
                </form>
                <div></div>
                <div style="display: flex; align-items:center;" class="Search_390rs">
                    <img class="adminIcon" src="" alt="">
                    <!-- <div class="dropdown"> -->
                    <p onclick="myFunction()" class="dropbtn" style="padding-left:12px; padding-top: 12px;">Admin</p>
                    <div id="myDropdown" class="dropdown-content">
                        <a href="./user.jsp">Profile</a>
                        <a href="#">Logout</a>
                    </div>
                    <!-- </div> -->

                </div>
            </div>
        </div>
    </div>
    <br>
    <div class="container tm-mt-big tm-mb-big Add_992rs">
        <div class="row">
            <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
                <div class="tm table-h-autotm-block-h-auto">
                    <div class="row">
                        <div class="col-12">
                            <h2 class="d-inline-block">Service Management
                            </h2>
                        </div>
                    </div>
                    <div class="col-xl-9 col-lg-10 col-md-10 col-sm-10">
                        <form action="">
                            <div class="username row mb-4">
                                <div class="col-md-2 d-flex align-items-center">
                                    Service
                                </div>
                                <div class="col-md-10">
                                    <div class="form-outline">
                                        <input type="text" id="form3Example1cg1" required=""
                                               class="custom-box form-control form-control-lg pt-1"/>
                                    </div>
                                </div>
                            </div>
                            <div class="username row mb-4">
                                <div class="col-md-2 d-flex align-items-center">
                                    Price
                                </div>

                                <div class="col-md-10">
                                    <div class="form-outline">
                                        <input type="number" id="form3Example1cg" required=""
                                               class="custom-box form-control form-control-lg pt-1"/>
                                    </div>
                                </div>
                            </div>
                            <div class="username row mb-4">
                                <div class="col-md-2 d-flex align-items-center">
                                    Adjust Price
                                </div>

                                <div class="col-md-10">
                                    <div class="form-outline">
                                        <input type="number" id="form3Example1cg" required=""
                                               class="custom-box form-control form-control-lg pt-1"/>
                                    </div>
                                </div>
                            </div>

                            <div class="username row mb-4">
                                <div class="col-md-2 d-flex align-items-center">
                                    Service
                                </div>
                                <div class="col-md-10">
                                    <textarea class="form-control validate" rows="3" required></textarea>
                                </div>
                            </div>
                            <div class="username row mb-4">
                                <div class="col-md-2 d-flex align-items-center">
                                    Supported Store
                                </div>
                                <div class="col-md-10">
                                    <select class="form-select" aria-label="Default select example">
                                        <option selected>Choose a nearby store</option>
                                        <%
                                            for (ShopDTO shop : listShop) {
                                        %>
                                            <option value="<%=shop.getShopID()%>"><%=shop.getShopName()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>


                            <div class="row mb-4">
                                <div class="col-md-2"></div>
                                <div class="col-md-10">
                                    <button class="button" name="action" value="AddServiceController">UPLOAD!</button>
                                    <button class="button">CLEAR</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="t-container">
            <div class="container">
                <ul class="responsive-table">
                    <li class="table-header">
                        <div class="col col-1">#</div>
                        <div class="col col-2">Service</div>
                        <div class="col col-3">Price</div>
                        <div class="col col-4">Description</div>
                        <div class="col col-5">Action</div>
                    </li>
                    <%
                        List<ServiceDTO> list = (List<ServiceDTO>) request.getAttribute("SERVICE_LIST");
                        int index = 1;
                        if (list == null) {
                            response.sendRedirect("error.jsp");
                            return;
                        }
                        for (ServiceDTO service : list) {
                    %>
                    <form method="post" action="MainController">
                        <li class="table-row">
                            <input type="hidden" name="serviceID" value="<%= service.getServiceID()%>">
                            <div class="col col-1" data-label="Number"><%= index++%>
                            </div>
                            <div class="col col-2" data-label="Service"><%= service.getServiceName()%>
                            </div>
                            <div class="col col-3" data-label="Price"><%= service.getServicePrice()%>
                            </div>
                            <div class="col col-4" data-label="Description"><%= service.getServiceDescription()%>
                            </div>
                            <div class="col col-5" data-label="Action">
                                <i class="fa-solid fa-arrow-up"
                                   style='font-size:24px; cursor: pointer; padding-right: 5px;'></i>
                                <button type="submit" name="action" value="DeleteService"><i class='far fa-trash-alt'
                                                                                             style='font-size:24px ; cursor: pointer;'></i>
                                </button>
                            </div>
                        </li>
                    </form>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
    </div>
</div>


<script src="js/admin.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="js/handleGetService.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>