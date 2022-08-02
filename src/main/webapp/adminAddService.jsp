<%@ page import="com.DTO.ServiceDTO" %>
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
    <link rel="stylesheet" href="./css/addService.css" />
    <link
      href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
      rel="stylesheet"
    />
    <title>Admin - Service</title>
  </head>

  <body>
  <%
    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
    ServiceDTO service = (ServiceDTO) request.getAttribute("SERVICE");
    String method;
    if (service == null) {
      method = "create";
      service = new ServiceDTO();
    } else {
      method = "update";
    }
  %>
    <body>
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
          <a href="#Service" class="collapsible active">Service</a>
        </div>
        <div>
          <a href="./User.html" class="collapsible">User</a>
        </div>
        <div>
          <a href="./Blog.html" class="collapsible">Blog</a>
        </div>

      </div>
      <div class="content">
        <div class="container tm-mt-big tm-mb-big Add_992rs">
          <div class="row">
            <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
              <div class="tm table-h-autotm-block-h-auto">
                <div class="row">
                  <div class="col-12">
                    <h2 class="d-inline-block">Service Management</h2>
                  </div>
                </div>

                <div class="col-xl-6 col-lg-6 col-md-12 col-sm-10">
                  <form action="MainController" method="POST">

                    <input type="hidden" name="SearchService" value=""/>
                    <input type="hidden" name="ID" value="<%= service.getServiceID()%>"/>
                    <label class="title-lable mt-3" for="name"
                      >Service Name
                    </label>
                    <input
                      id="name"
                      name="serviceName"
                      type="text"
                      value="<%= service.getServiceName()%>"
                      class="form-control validate"
                      required
                    />

                    <label class="title-lable mt-3" for="name">Price </label>
                    <input
                      id="name"
                      name="servicePrice"
                      type="text"
                      value="<%= service.getServicePrice()%>"
                      class="form-control validate"
                      required
                    />

                    <label class="title-lable mt-3" for="name"
                      >Adjust Price
                    </label>
                    <input
                        id="name"
                        name="name"
                        type="text"
                        value="<%= service.getServicePrice()%>"
                        class="form-control validate"
                        required
                    />

                    <label class="title-lable mt-3" for="name">Service </label>
                    <textarea
                        class="form-control validate"
                        rows="3"
                        required
                        name="serviceDescription"
                        value="<%= service.getServiceDescription()%>"
                    ></textarea>

                    <div class="col-xl-6 col-lg-6 col-md-12 col-sm-10 mb-4">
                      <label class="title-lable mt-3" for="name"
                        >Suported Store
                      </label>
                      <div class="col-md-12">
                        <select
                          class="form-select"
                          aria-label="Default select example"
                        >
                          <option selected>Choose a nearby store</option>
                          <option value="SHOP-001">Pets Paradise Quận 9</option>
                          <option value="SHOP-002">Pets Paradise Quận 1</option>
                          <option value="SHOP-003">Pets Paradise Quận 2</option>
                        </select>
                      </div>
                    </div>

                    <div class="col-xl-6 col-lg-6 col-md-12 col-sm-10 mb-4">
                      <div class="col-md-2">
                        <%
                          if ("create".equals(method)) {
                        %>
                        <button  class="button" name="action" value="AddService">Add Service</button>
                        <%
                        } else {
                        %>
                        <button  class="button" name="action" value="UpdateService">Update Service</button>
                        <%
                          }
                        %>
                      </div>
                      <div class="col-md-10"></div>
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
    </body>
  </body>
</html>
