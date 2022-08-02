<%@ page import="com.DTO.UserDTO" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    />
    <link rel="stylesheet" href="./css/dropDownAdmin.css" />
    <link rel="shortcut icon" type="img/png" href="./img/paw-solid.svg" />
    <link rel="stylesheet" href="./css/addUser.css" />

    <link
      href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
      rel="stylesheet"
    />
    <title>Admin - User</title>
  </head>

  <body>
  <%
    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
    UserDTO userDTO = (UserDTO) request.getAttribute("USER");
    String method;
    if (userDTO == null) {
      method = "create";
      userDTO = new UserDTO();
    } else {
      method = "update";
    }
  %>
    <div class="sidebar">
      <div class="LOGO">
        <img src="./img/paw-solid.svg" alt="" class="img-responsive" />
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
        <a href="./admin.html" class="collapsible ">DashBoard</a>
    </div>
      <div>
        <a class="collapsible" href="./Order.html">Order</a>
      </div>
      <div>
        <a href="./Product.html" class="collapsible">Product</a>
      </div>
      <div>
        <a href="./Service.html" class="collapsible">Service</a>
      </div>
      <div>
        <a href="#User" class="collapsible active">User</a>
      </div>
      <div>
        <a href="./Blog.html" class="collapsible">Blog</a>
      </div>
    </div>

    <div class="content">
      <div class="container tm-mt-big tm-mb-big">
        <div class="row">
          <div class="col-xl-9 col-lg-10 col-md-10 col-sm-10">
            <div class="tm table-h-autotm-block-h-auto">
              <form method="post" action="MainController">
                <input type="hidden" name="roleID" value="<%=userDTO.getRoleID()%>"/>
                <div class="row">
                  <div class="col-12">
                    <h2 class="d-inline-block">User Management</h2>
                  </div>
                </div>

                <div class="body">
                  <div class="username row mb-4">
                    <div class="col-md-2 d-flex align-items-center">Username</div>
                    <div class="col-md-10">
                      <div class="form-outline">
                        <input
                          type="text"
                          id="form3Example1cg"
                          required=""
                          name="username"
                          value="<%=userDTO.getUsername()%>"
                          class="custom-box form-control form-control-lg pt-1"
                        />
                      </div>
                    </div>
                  </div>

                  <div class="username row mb-4">
                    <div class="col-md-2 d-flex align-items-center">Name</div>
                    <div class="col-md-10">
                      <div class="form-outline">
                        <input
                                type="text"
                                id="form3Example3cg"
                                required=""
                                name="userName"
                                value="<%= userDTO.getName()%>"
                                class="custom-box form-control form-control-lg pt-1"
                        />
                      </div>
                    </div>
                  </div>

                  <div class="username row mb-4">
                    <div class="col-md-2 d-flex align-items-center">Email</div>
                    <div class="col-md-10">
                      <div class="form-outline">
                        <input
                          type="email"
                          id="form3Example3cg"
                          required=""
                          name="email"
                          value="<%= userDTO.getEmail()%>"
                          class="custom-box form-control form-control-lg pt-1"
                        />
                      </div>
                    </div>
                  </div>

                  <div class="username row mb-4">
                    <div class="col-md-2 d-flex align-items-center">Phone</div>
                    <div class="col-md-10">
                      <div class="form-outline">
                        <input
                          type="text"
                          id="form3Example3cg"
                          required=""
                          name="phoneNumber"
                          value="<%= userDTO.getPhoneNumber()%>"
                          class="custom-box form-control form-control-lg pt-1"
                        />
                      </div>
                    </div>
                  </div>

                  <div class="username row mb-4">
                    <div class="col-md-2 d-flex align-items-center">
                      Password
                    </div>
                    <div class="col-md-10">
                      <div class="form-outline">
                        <input
                          type="password"
                          id="form3Example3cg"
                          required=""
                          name="password"
                          value="<%= userDTO.getPassword()%>"
                          class="custom-box form-control form-control-lg pt-1"
                        />
                      </div>
                    </div>
                  </div>
                  <div class="row mb-4">
                    <div class="col-md-2"></div>
                    <div class="col-md-10">
                      <%
                        if ("create".equals(method)) {
                      %>
                      <button  class="button" name="action" value="AddUser">Add User</button>
                      <%
                      } else {
                      %>
                      <button  class="button" name="action" value="UpdateUser">Update User</button>
                      <%
                        }
                      %>
                    </div>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script src="./js/admin.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
