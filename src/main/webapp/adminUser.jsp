<%@ page import="com.DTO.UserDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%
        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
        if (loginUser == null || !loginUser.getRoleID().equals("AD")) {
            response.sendRedirect("login.jsp");
            return;
        }
        String search = request.getParameter("SearchUser");
        if (search == null) {
            search = "";
        }
    %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
    <link rel="stylesheet" href="./css/dropDownAdmin.css"/>
    <link rel="shortcut icon" type="img/png" href="./img/paw-solid.svg"/>
    <link rel="stylesheet" href="./css/addUser.css"/>

    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet"/>
    <title>Admin - User</title>
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
        <a href="#AdminUser" class="collapsible active">User</a>
    </div>
    <div>
        <a href="AdminBlog" class="collapsible">Blog</a>
    </div>
</div>
<div class="content">
    <div class="container">
        <div class="row height d-flex justify-content-center align-items-center">
            <div class="col-md-12 searchBar">
                <form action="MainController" method="POST">
                    <div class="form">
                        <input type="text" class="form-control form-input" placeholder="Search..." value="<%=search%>"
                               name="SearchUser"/>
                        <span class="left-pan btn"><i class="fa-solid fa-magnifying-glass"></i></span>
                    </div>
                </form>
                <div></div>
                <div style="display: flex; align-items: center" class="Admin_414rs">
                    <p onclick="myFunction()" class="dropbtn" style="padding-left: 12px; padding-top: 12px">
                        Admin
                    </p>
                    <div id="myDropdown" class="dropdown-content">
                        <a href="./user.jsp">Profile</a>
                        <a href="#">Logout</a>
                    </div>
                    <!-- </div> -->
                </div>
            </div>
        </div>
    </div>
    <br/>
    <div class="container tm-mt-big tm-mb-big">
        <div class="row">
            <div class="col-xl-9 col-lg-10 col-md-10 col-sm-10">
                <div class="tm table-h-autotm-block-h-auto">
                    <div class="row">
                        <div class="col-12">
                            <h2 class="d-inline-block">User Management</h2>
                        </div>
                    </div>
                    <div class="body">
                        <div class="row mb-4">
                            <div class="col-md-2">
                                <a href="./adminAddUser.jsp" target="popup"
                                   onclick="window.open('adminAddUser.jsp','_blank','fullscreen=yes','true')">
                                    <button class="button">Create User</button>
                                </a>
                                <div class="col-md-10"></div>
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
                        <div class="col col-2">User</div>
                        <div class="col col-3">Email</div>
                        <div class="col col-4">Phone</div>
                        <div class="col col-5">RoleID</div>
                        <div class="col col-6">Action</div>
                    </li>
                    <%
                        List<UserDTO> list = (List<UserDTO>) request.getAttribute("USER_LIST");
                        int index = 1;
                        if (list == null) {
                            response.sendRedirect("error.jsp");
                            return;
                        }
                        for (UserDTO user : list) {
                    %>

                        <li class="table-row">
                            <div class="col col-1" data-label="Number"><%=index++%>
                            </div>
                            <div class="col col-2" data-label="User"><%=user.getName()%>
                            </div>
                            <div class="col col-3" data-label="Email">
                                <%=user.getEmail()%>
                            </div>
                            <div class="col col-4" data-label="Phone"><%=user.getPhoneNumber()%>
                            </div>
                            <div class="col col-5" data-label="RoleID"><%=user.getRoleID()%>
                            </div>
                            <div class="col col-6" data-label="Action">
                                <a href="./adminAddService.jsp" target="popup"
                                   onclick="window.open('AdminAddUser?method=update&userName=<%= user.getUsername()%>','_blank','fullscreen=yes','true')">
                                    <i class="fa-solid fa-arrow-up"
                                       style='font-size:24px; cursor: pointer; padding-right: 5px; color: black'></i></a>

                                <form method="POST" action="MainController">
                                    <input type="hidden" name="userName" value="<%= user.getUsername()%>">
                                <button type="submit" name="action" value="DeleteUser"><i class='far fa-trash-alt'
                                                                                          style='font-size:24px ; cursor: pointer;'></i>
                                </button>
                            </div>
                    </form>
                    <%
                        }
                    %>
                </ul>
            </div>
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
