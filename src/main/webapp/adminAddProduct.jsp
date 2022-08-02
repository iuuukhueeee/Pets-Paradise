<%@ page import="com.DTO.UserDTO" %>
<%@ page import="com.DTO.ProductDTO" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
    <link rel="stylesheet" href="./css/dropDownAdmin.css">

    <link rel="shortcut icon" type="img/png" href="./img/paw-solid.svg"/>
    <link rel="stylesheet" href="./css/addProduct.css">


    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet">
    <title>Admin - Product</title>

</head>

<body>
<%
    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
    ProductDTO product = (ProductDTO) request.getAttribute("PRODUCT");
    String method;
    if (product == null) {
        method = "create";
        product = new ProductDTO();
    } else {
        method = "update";
    }
%>
<div class="sidebar">
    <div class="LOGO">
        <img src="./img/paw-solid.svg" alt="" class="img-responsive">
        <div style="font-size: 20px;font-weight: 900;color: #68A7AD; justify-content: center; padding-left: 16px;">
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
        <a href="AdminProduct" class="collapsible active">Product</a>
    </div>
    <div>
        <a href="AdminService" class="collapsible">Service</a>
    </div>
    <div>
        <a href="AdminUser" class="collapsible">User</a>
    </div>
    <div>
        <a href="AdminBlog" class="collapsible">Blog</a>
    </div>
</div>
<div class="content">
    <form action="MainController" method="POST" class="tm-edit-product-form">
        <div class="container tm-mt-big tm-mb-big ">
            <div class="row">
                <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12">
                    <div class="row">
                        <div class="col-12">
                            <h2 class="d-inline-block">Product Management</h2>
                        </div>
                    </div>

                    <div class="tm table-h-auto tm-block-h-auto">
                        <div class="row tm-edit-product-row">
                            <div class="col-xl-6 col-lg-6 col-md-12">
                                <input type="hidden" name="ID" value="<%= product.getProductID()%>"/>
                                <div class="form-group mb-3">
                                    <label class="title-lable mt-3" for="name">Product Name
                                    </label>
                                    <input id="name" name="name" type="text" value="<%= product.getName() %>"
                                           class="form-control validate" required/>
                                    <div class="row mt-3">
                                        <div class="form-group col-xs-12 col-sm-6">
                                            <label for="price" class="title-lable">Price
                                            </label>
                                            <input id="price" name="price" type="number"
                                                   value="<%= product.getPrice()%>" class="form-control validate"
                                                   data-large-mode="true"/>
                                        </div>
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label for="quantity" class="title-lable">Quantity
                                            </label>
                                            <input id="quantity" name="quantity" type="number"
                                                   value="<%= product.getQuantity()%>" class="form-control validate"
                                                   required/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group mb-3">
                                    <label for="category" class="title-lable">Category</label>
                                    <select class="custom-select tm-select-accounts" id="category"
                                            onchange="getSelector()">
                                        <option selected>Select category</option>
                                        <option value="CATEGORY-005">Toy</option>
                                        <option value="CATEGORY-001">Food</option>
                                        <option value="CATEGORY-002">Gel</option>
                                        <option value="CATEGORY-003">Accessories</option>
                                        <option value="CATEGORY-004">Stuffs</option>
                                    </select>
                                </div>
                                <div class="row" id="date_form">
                                    <div class="form-group mb-3 col-xs-12 col-sm-6" id="import_date"
                                         style="display: none;">
                                        <label for="import_date" class="title-lable">Import Date
                                        </label>
                                        <input id="import_date" name="import_date" type="date"
                                               value="<%= product.getImportDate()%>" class="form-control validate "
                                               data-large-mode="true"/>
                                    </div>
                                    <div class="form-group mb-3 col-xs-12 col-sm-6" id="expire_date"
                                         style="display: none;">
                                        <label for="expire_date" class="title-lable">Expired Date
                                        </label>
                                        <input id="expire_date" name="expire_date" type="date"
                                               value="<%= product.getExpiredDate()%>" class="form-control validate"
                                               required/>
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4 input_file">
                                    <div class="mb-3">
                                        <input class="form-control" type="file" id="formFile">
                                    </div>

                                    <div class="custom-file mt-3 mb-3" style="display: flex;">
                                        <input id="fileInput" type="file" style="display:none;"/>
                                        <%
                                            if ("create".equals(method)) {
                                        %>
                                        <button class="button" name="action" value="AddProduct">Add Product</button>
                                        <%
                                        } else {
                                        %>
                                        <button class="button" name="action" value="UpdateProduct">Update Product
                                        </button>
                                        <%
                                            }
                                        %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <%--<script src="./js/selector.js"></script>--%>
    <%--<script src="./js/admin.js"></script>--%>
    <script src="./js/admin_selector.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>