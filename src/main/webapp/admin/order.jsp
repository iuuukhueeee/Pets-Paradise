<%@ page import="java.util.List" %>
<%@ page import="com.DTO.OrderDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">




<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />
    <link rel="stylesheet" href="css/dropDownAdmin.css">
    <link rel="shortcut icon" type="img/png" href="../img/paw-solid.svg" />
    <link rel="stylesheet" href="css/Order.css">

    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet">
    <title>Admin - Order Details</title>

</head>

<body>



<div class="sidebar">
    <div class="LOGO">
        <img src="../img/paw-solid.svg" alt="" class="img-responsive">
        <div style="font-size: 20px;font-weight: 900;color: #68A7AD; justify-content: center; padding-left: 16px;">
            Pet's Paradise
        </div>
    </div>
    <div>
        <a href="admin/product.jsp" class="collapsible">Product</a>
    </div>
    <div>
        <a href="admin/service.jsp" class="collapsible">Service</a>
    </div>
    <div>
        <a href="admin/user.jsp" class="collapsible">User</a>
    </div>
    <div>
        <a href="Blog.jsp" class="collapsible">Blog</a>
    </div>
    <a class="collapsible active" href="#order">Order</a>
</div>

<div class="content">
    <div class="container">

        <div class="row height d-flex justify-content-center align-items-center">

            <div class="col-md-12 searchBar">

                <div class="form">
                    <input type="text" class="form-control form-input" placeholder="Search...">

                    <span class="left-pan btn "><i class="fa-solid fa-magnifying-glass"></i></span>
                </div>
                <div></div>
                <div style="display: flex; align-items:center;">
                    <img class="adminIcon" src="" alt="">
                    <!-- <div class="dropdown"> -->
                    <p onclick="myFunction()" class="dropbtn" style="padding-left:12px; padding-top: 12px;">Admin
                    </p>
                    <div id="myDropdown" class="dropdown-content">
                        <a href="../../../../user.html">Profile</a>
                        <a href="#">Logout</a>
                    </div>
                    <!-- </div> -->
                </div>
            </div>
        </div>
    </div>
    <br>
    <%
        List<OrderDTO> list = (List<OrderDTO>) request.getAttribute("LIST_ORDER");
        int index = 0;
        if (list == null) {
            response.sendRedirect("error.jsp");
            return;
        }
        for(OrderDTO order: list){
    %>
    <div class="t-container">
        <div class="container">
            <ul class="responsive-table">
                <li class="table-header">
                    <div class="col col-1">#</div>
                    <div class="col col-2">OrderID</div>
                    <div class="col col-3">OrderDate</div>
                    <div class="col col-4">Username</div>
                    <div class="col col-5">FeedbackOrder</div>
                </li>

                <li class="table-row">
                    <div class="col col-1" data-label="#"><%= index++ %></div>
                    <div class="col col-2" data-label="OrderID"><%= order.getOrderID() %>></div>
                    <div class="col col-3" data-label="OrderDate"><%= order.getOrderDate() %></div>
                    <div class="col col-4" data-label="Username"><%= order.getUsername() %></div>
                    <div class="col col-5" data-label="FeedbackOrder"><%= order.getFeedBackOrder() %></div>
                </li>

            </ul>
        </div>
    </div>
    <%
        }
    %>
</div>


<script src="js/admin.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
