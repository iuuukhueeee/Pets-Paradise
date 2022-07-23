<%@ page import="com.DTO.UserDTO" %>
<%@ page import="com.DTO.OrderDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.DTO.OrderDetailDTO" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
    <link rel="shortcut icon" type="img/png" href="../img/paw-solid.svg"/>
    <link rel="stylesheet" href="./css/order.css">

    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet">
    <title>User - Order</title>

</head>

<body>

<div class="sidebar">
    <div class="LOGO">
        <img src="./img/paw-solid.svg" alt="" class="img-responsive">
        <div style="font-size: 20px;font-weight: 900;color: #68A7AD; justify-content: center; padding-left: 16px;">
            Pet's Paradise
        </div>
    </div>
    <div>
        <a href="./user.jsp" class="collapsible">Personal Information</a>
    </div>
    <div>
        <a href="UserOrder" class="collapsible active">Order</a>
    </div>
    <div>
        <a href="UserPetInfo" class="collapsible">Pet's Info</a>
    </div>
</div>
<div class="content">
    <div class="t-container mt-5">
        <div class="container">
            <ul class="responsive-table">
                <li class="table-header">
                    <div class="col col-1">#</div>
                    <div class="col col-2">Order</div>
                    <div class="col col-3">Number of items</div>
                    <div class="col col-4">Action</div>
                </li>


                <%
                    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                    if (user == null) {
                        response.sendRedirect("login.jsp");
                        return;
                    }
                    List<OrderDTO> listOrder = (List<OrderDTO>) request.getAttribute("USER_LIST_ORDER");
                    Map<String, List<OrderDetailDTO>> mapDetail = (Map<String, List<OrderDetailDTO>>) request.getAttribute("USER_MAP_ORDER_DETAIL");
                    Map<String, String> mapItemName = (Map<String, String>) request.getAttribute("ITEM_NAME");

                    if (listOrder.size() > 0 && listOrder != null) {
                        int index = 0;
                        for (OrderDTO order : listOrder) {
                %>

                <li class="table-row">
                    <div class="col col-1" data-label="#"><%=++index%>
                    </div>
                    <div class="col col-2" data-label="Order"><%=order.getOrderID()%>
                    </div>
                    <div class="col col-3" data-label="NOI"><%=mapDetail.get(order.getOrderID()).size()%>
                    </div>
                    <div class="col col-4" data-label="Action">
                        <a href="./feedback.html" target="popup" onclick="window.open('../feedback.html','_blank','fullscreen=yes','true')">
                            <i class="fa fa-envelope-open" style='font-size:16px; cursor: pointer; padding-right: 5px; color: black'></i></a>
                        <i class="fa fa-arrow-down" onclick="showHideRow('hidden_row3');" style='font-size:16px; cursor: pointer; padding-right: 5px; color: black' aria-hidden="true"></i>
                    </div>
                </li>
                <div class="container" id="<%=order.getOrderID()%>" class="hidden_row" style="display: none">
                    <ul class="responsive-table p-2 mb-3">
                        <li class="table-header">
                            <div class="col col-1">Name</div>
                            <div class="col col-2">Price</div>
                            <div class="col col-3">Quantity</div>
                            <div class="col col-4">Total</div>
                        </li>
                        <%
                            List<OrderDetailDTO> list = mapDetail.get(order.getOrderID());
                            for (OrderDetailDTO orderDetail : list) {

                        %>
                        <li class="table-row">
                            <div class="col col-1" data-label="Name"><%=mapItemName.get(orderDetail.getItemID())%>
                            </div>
                            <div class="col col-2" data-label="Price"><%=orderDetail.getOrderDetailPrice()%>
                            </div>
                            <div class="col col-3" data-label="Quantity"><%=orderDetail.getQuantity()%>
                            </div>
                            <div class="col col-4"
                                 data-label="Total"><%=orderDetail.getOrderDetailPrice() * orderDetail.getQuantity()%>
                            </div>
                        </li>
                        <%
                            }
                        %>

                    </ul>
                </div>
                <%
                        }
                    }
                %>
            </ul>
        </div>
    </div>
</div>

<script src="./js/order.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js"></script>
</body>

</html>
