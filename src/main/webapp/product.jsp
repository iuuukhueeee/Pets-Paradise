<%@ page import="com.product.ProductDTO" %>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Management</title>
</head>

<body>

    <div class="wrapper">
        <form action="MainController" method="post">
            <div class="h5 font-weight-bold text-center mb-3">Adding Product</div>
            </br>
            <div class="">
                <input autocomplete="off" type="text" class="form-control" placeholder="Product ID" name="productID">

            </div>
            </br>
            <div class="">
                <input autocomplete="off" type="text" class="form-control" placeholder="Category" name="productCategoryID">
            </div>
            </br>
            <div class="">
                <input autocomplete="off" type="text" class="form-control" placeholder="Name" name="productName">
            </div>
            </br>
            <div class="">
                <input autocomplete="off" type="number" class="form-control" placeholder="Quantity" name="quantity">
            </div>

            </br>
            <div class="">

                <input autocomplete="off" type="text" class="form-control" placeholder="Image(url)" name="productImage">
            </div>

            </br>
            <div class="">
                <input autocomplete="off" type="number" class="form-control" placeholder="Price" name="price">
            </div>



            </br>
            <div class="">
                Import date:
                <input autocomplete="off" type="date" class="form-control" placeholder="Import Date" name="importDate">
            </div>
            </br>
            <div class="">
                Using date:
                <input autocomplete="off" type="date" class="form-control" placeholder="EXP" name="expiredDate">
            </div>
            </br>
            <div>${requestScope.MESSAGE}</div>
            <div class="">
                <input type="submit" class="btn btn-info"  name="action" value="AddProduct">
            </div>
            </br>

        </form>
    </div>

    </form>
</section>

    <form action="MainController" method="POST">
        <input name="SearchProduct" placeholder="Product title" type="text"/>
        <input type="submit" name="action" value="SearchProduct" aria-label="submit form"/>

    </form>
    </br>

    <section class="grid">
        <%
            List<ProductDTO> productList = (List<ProductDTO>) request.getAttribute("PRODUCT_LIST");

            if (productList != null) {
                if (productList.size() > 0) {

        %>

        <table border="2" class="table table-striped table-bordered" style="box-sizing: border-box">
            <thead>
            <tr>
                <th>No</th>

                <th>ProductID</th>
                <th>CategoryID</th>
                <th>Quantity</th>
                <th>Image</th>
                <th>Name</th>
                <th>Price</th>
                <th>Import Date</th>
                <th>EXP</th>


            </tr>
            </thead>
            <tbody>
            <%
                int count = 1;
                for (ProductDTO product : productList) {
            %>
            <form action="MainController" method="POST">
                <tr>
                    <td scope="row"><%= count++%></td>


                    <input type="hidden" name="productID" value="<%= product.getProductID()%>" required="" />
                    <td>
                        <input type="text" name="category" value="<%= product.getCategoryID()%>" required="" size="8" />
                    </td>
                    <td>
                        <input type="text" name="productName" value="<%= product.getName()%>" required="" size="8" />
                    </td>
                    <td>
                        <input type="text" name="quantity" value="<%= product.getQuantity()%>" required="" size="8" />
                    </td>
                    <td>
                        <input type="text" name="productImage" value="<%= product.getImage()%>" required="" size="10"/>
                    </td>
                    <td>
                        <input type="text" name="price" value="<%= product.getPrice()%>" required="" size="5"/>
                    </td>
                    <td>
                        <input type="text" name="importDate" value="<%= product.getImportDate()%>" required="" size="5"/>
                    </td>
                    <td>
                        <input type="text" name="expiredDate" value="<%= product.getExpiredDate()%>" required="" size="5"/>
                    </td>


                    <td>
                        <a href="MainController?action=DeleteProduct&productID=<%= product.getProductID()%>&SearchProduct=<%= request.getParameter("SearchProduct")%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" name="action" value="UpdateProduct" />
                    </td>
                </tr>
            </form>
            <%
                }
            %>
            </tbody>
        </table>

        <%
            }
        %>
        <%
            }
        %>
    </section>



</body>

</html>