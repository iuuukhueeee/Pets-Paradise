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
    <link rel="stylesheet" href="./css/addService.css"/>
    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet"
    />
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
        <a class="collapsible" href="admin">DashBoard</a>
    </div>
    <div>
        <a class="collapsible" href="AdminOrder">Order</a>
    </div>
    <div>
        <a href="AdminProduct" class="collapsible">Product</a>
    </div>
    <div>
        <a href="AdminService" class="collapsible active">Service</a>
    </div>
    <div>
        <a href="AdminUser" class="collapsible">User</a>
    </div>
    <div>
        <a href="AdminBlog" class="collapsible">Blog</a>
    </div>
</div>

<div class="content">
    <div class="container">
        <div class="row height d-flex align-items-center">
            <div class="col-md-12 searchBar">
                <form action="MainController" method="POST">
                    <div class="form">
                        <input
                                type="text"
                                class="form-control form-input"
                                placeholder="Search..."
                                name="SearchService"
                                value="<%=search%>"
                        />
                        <span class="left-pan btn">
                    <button name="action" value="searchservice">
                        <i class="fa-solid fa-magnifying-glass"></i>
                    </button>
                </span>
                    </div>
                </form>
                <div style="display: flex; align-items: center; padding-left: 10px">
                    <p
                            onclick="myFunction()"
                            class="dropbtn"
                            style="padding-left: 12px; padding-top: 12px">
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
                        <h2 class="d-inline-block">Service Management</h2>
                    </div>
                </div>
                <div class="tm table-h-auto tm-block-h-auto">
                    <div class="col-xl-6 col-lg-6 col-md-12"></div>
                    <div class="mt-3 mb-3">
                        <a
                                href="./adminAddService.jsp"
                                target="popup"
                                onclick="window.open('./adminAddService.jsp','_blank','fullscreen=yes','true')"
                        >
                            <button
                                    class="button"
                                    style="margin-left: 2px; background-color: #51969c"
                            >
                                Create Service
                            </button>
                        </a
                        >
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
                    <li class="table-row">

                        <div class="col col-1" data-label="Number"><%=index++%>
                        </div>
                        <div class="col col-2" data-label="Service"><%=service.getServiceName()%>
                        </div>
                        <div class="col col-3" data-label="Price"><%=service.getServicePrice()%>
                        </div>
                        <div class="col col-4" data-label="Description">
                            <%=service.getServiceDescription()%>
                        </div>
                        <div class="col col-5" data-label="Action">
                            <a
                                    href="./adminAddService.jsp"
                                    target="popup"
                                    onclick="window.open('AdminAddService?method=update&ID=<%= service.getServiceID()%>','_blank','fullscreen=yes','true')"
                            >
                                <i class="fa-solid fa-arrow-up"
                                   style="font-size: 24px;cursor: pointer;padding-right: 5px;color: black;"
                                ></i
                                ></a>
                            <form method="post" action="MainController">
                                <input type="hidden" name="serviceID" value="<%= service.getServiceID()%>">

                                <button type="submit" name="action" value="DeleteService"><i class='far fa-trash-alt'
                                                                                             style='font-size:24px ; cursor: pointer;'></i>
                                </button>
                            </form>
                        </div>
                    </li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</div>

<script src="./js/admin.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    $(function () {
        $("#expire_date").datepicker();
    });
</script>
</body>
</html>
