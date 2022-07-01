<%@ page import="com.DTO.UserDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" />
  <link rel="stylesheet" href="css/dropDownAdmin.css">
  <link rel="shortcut icon" type="img/png" href="../img/paw-solid.svg" />
  <link rel="stylesheet" href="css/addUser.css">

  <link
          href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
          rel="stylesheet">
  <title>Admin - User</title>

</head>

<body>

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
    <a href="#User" class="collapsible active">User</a>
  </div>
  <div>
    <a href="Blog.jsp" class="collapsible">Blog</a>
  </div>

  <a class="collapsible" href="admin/order.jsp">Order</a>
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
        <div style="display: flex; align-items:center;" class="Admin_414rs">
          <img class="adminIcon" src="" alt="">
          <!-- <div class="dropdown"> -->
          <p onclick="myFunction()" class="dropbtn" style="padding-left:12px; padding-top: 12px;">Admin</p>
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
  <div class="container tm-mt-big tm-mb-big">
    <div class="row">
      <div class="col-xl-9 col-lg-10 col-md-10 col-sm-10">
        <div class="tm table-h-autotm-block-h-auto">
          <div class="row">
            <div class="col-12">
              <h2 class="d-inline-block">User Management</h2>
            </div>
          </div>

          <form action="MainController" method="post">

            <div class="body">
              <div class="username row mb-4">
                <div class="col-md-2 d-flex align-items-center">
                  Name
                </div>
                <div class="col-md-10">
                  <div class="form-outline">
                    <input type="text" id="form3Example1cg" required=""
                           class="custom-box form-control form-control-lg pt-1" />
                  </div>
                </div>
              </div>

              <div class="username row mb-4">
                <div class="col-md-2 d-flex align-items-center">
                  Email
                </div>
                <div class="col-md-10">
                  <div class="form-outline">
                    <input type="email" id="form3Example3cg1" required=""
                           class="custom-box form-control form-control-lg pt-1" />
                  </div>
                </div>
              </div>

              <div class="username row mb-4">
                <div class="col-md-2 d-flex align-items-center">
                  Phone
                </div>
                <div class="col-md-10">
                  <div class="form-outline">
                    <input type="text" id="form3Example3cg" required=""
                           class="custom-box form-control form-control-lg pt-1" />
                  </div>
                </div>
              </div>

              <div class="username row mb-4">
                <div class="col-md-2 d-flex align-items-center">
                  Password
                </div>
                <div class="col-md-10">
                  <div class="form-outline">
                    <input type="password" id="form3Example3cg12" required=""
                           class="custom-box form-control form-control-lg pt-1" />
                  </div>
                </div>
              </div>
              <div class="row mb-4">
                <div class="col-md-2"></div>
                <div class="col-md-10">
                  <button class="button">Add User</button>
                </div>
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
          <div class="col col-2">User</div>
          <div class="col col-3">Email</div>
          <div class="col col-4">Phone</div>
          <div class="col col-5">RoleID</div>
          <div class="col col-6">Action</div>
        </li>
        <%
          List<UserDTO> list = (List<UserDTO>) request.getAttribute("LIST_USER");
          int index = 1;
          if (list == null) {
            response.sendRedirect("error.jsp");
            return;
          }
          for(UserDTO user: list){
        %>
        <li class="table-row">
          <div class="col col-1" data-label="Number"><%= index++%></div>
          <div class="col col-2" data-label="User"><%= user.getName()%> </div>
          <div class="col col-3" data-label="Email"><%= user.getEmail()%></div>
          <div class="col col-4" data-label="Phone"><%= user.getPhoneNumber()%></div>
          <div class="col col-5" data-label="RoleID"><%= user.getRoleID()%></div>
          <div class="col col-6" data-label="Action">
            <i class="fa-solid fa-arrow-up" style='font-size:24px; cursor: pointer; padding-right: 5px;'></i>
            <i class='far fa-trash-alt' style='font-size:24px ; cursor: pointer;'></i>
          </div>
        </li>
        <%
          }
        %>
      </ul>
    </div>
  </div>

</div>
</div>

<script src="js/admin.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
  $(function () {
    $("#expire_date").datepicker();
  });
</script>
</body>
</body>

</html>