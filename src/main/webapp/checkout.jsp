<%@ page import="com.DTO.UserDTO" %>
<%@ page import="com.DAO.CartDAO" %>
<%@ page import="com.DAO.ProductDAO" %>
<%@ page import="com.DTO.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.checkout.ItemDetails" %>
<%@ page import="com.DTO.CartDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.DTO.ServiceDTO" %>
<%@ page import="com.DAO.ServiceDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://unpkg.com/flowbite@1.4.7/dist/flowbite.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com"/>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="./css/checkout.css">
    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet">
    <link rel="shortcut icon" type="img/png" href="/img/paw-solid.svg"/>
    <title>Checkout</title>
</head>

<body>


<%
    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    CartDAO cartDAO = new CartDAO();
    ProductDAO productDAO = new ProductDAO();
    ServiceDAO serviceDAO = new ServiceDAO();
    ProductDTO product;
    ServiceDTO service;
    List<ItemDetails> list = new ArrayList<>();
    List<CartDTO> cart = cartDAO.getByUsername(user.getUsername());
    if (cart.size() > 0 && cart != null) {
        for (int i = 0; i < cart.size(); i++) {
            String itemID = cart.get(i).getItemID();
            String itemTypeID = itemID.split("-")[0];
            if ("PRODUCT".equals(itemTypeID)) {
                product = productDAO.getByID(itemID);
                list.add(new ItemDetails(product.getImage(), product.getName(), cart.get(i).getQuantity(), product.getPrice()));
            } else if ("SERVICE".equals(itemTypeID)) {
                service = serviceDAO.getByID(itemID);
                list.add(new ItemDetails("", service.getServiceName(), 1, service.getServicePrice()));
            }
        }
    }
%>

<section class="breacrumb ">
    <div class="container">
        <nav class="flex" aria-label="Breadcrumb">
            <ol class="inline-flex items-center space-x-1 md:space-x-3">
                <li class="inline-flex items-center">
                    <a href="index.jsp"
                       class="inline-flex items-center text-sm font-medium text-gray-700 hover:text-gray-900 dark:text-gray-400 dark:hover:text-white">
                        <svg class="mr-2 w-4 h-4" fill="currentColor" viewBox="0 0 20 20"
                             xmlns="http://www.w3.org/2000/svg">
                            <path
                                    d="M10.707 2.293a1 1 0 00-1.414 0l-7 7a1 1 0 001.414 1.414L4 10.414V17a1 1 0 001 1h2a1 1 0 001-1v-2a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 001 1h2a1 1 0 001-1v-6.586l.293.293a1 1 0 001.414-1.414l-7-7z">
                            </path>
                        </svg>
                        Home
                    </a>
                </li>
                <li>
                    <div class="flex items-center">
                        <svg class="w-6 h-6 text-gray-400" fill="currentColor" viewBox="0 0 20 20"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                  d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                                  clip-rule="evenodd"></path>
                        </svg>
                        <a href="shopping"
                           class="ml-1 text-sm font-medium text-gray-700 hover:text-gray-900 md:ml-2 dark:text-gray-400 dark:hover:text-white">Shopping</a>
                    </div>
                </li>
                <li aria-current="page">
                    <div class="flex items-center">
                        <svg class="w-6 h-6 text-gray-400" fill="currentColor" viewBox="0 0 20 20"
                             xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                  d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                                  clip-rule="evenodd"></path>
                        </svg>
                        <span class="ml-1 text-sm font-medium text-gray-500 md:ml-2 dark:text-gray-400">Checkout
							</span>
                    </div>
                </li>
            </ol>
        </nav>
    </div>
</section>
<section class="body">
    <div class="bg-gray-100">
        <div class="container mx-auto mt-10">
            <div class="flex shadow-md my-10 cart">
                <div class="w-3/4 bg-white px-10 py-10 item">
                    <div class="flex justify-between border-b pb-8">
                        <h1 class="font-semibold text-2xl">Shopping Cart</h1>
                        <h2 class="font-semibold text-2xl"><%=list.size()%> Items</h2>
                    </div>
                    <div class="flex mt-10 mb-5">
                        <h3 class="font-semibold text-gray-600 text-xs uppercase w-2/5">Product Details</h3>
                        <h3 class="font-semibold text-center text-gray-600 text-xs uppercase w-1/5 text-center">
                            Quantity
                        </h3>
                        <h3 class="font-semibold text-center text-gray-600 text-xs uppercase w-1/5 text-center">
                            Price
                        </h3>
                        <h3 class="font-semibold text-center text-gray-600 text-xs uppercase w-1/5 text-center">
                            Total
                        </h3>
                    </div>

                    <%
                        float total = 0;
                        for (ItemDetails item : list) {
                            total += item.getPrice() * item.getQuantity();
                    %>

                    <div class="flex items-center hover:bg-gray-100 -mx-8 px-6 py-5">
                        <div class="flex w-2/5">
                            <div class="w-20">
                                <img class="h-24" src="./img/service1.jpeg" alt="">
                            </div>
                            <div class="flex flex-col justify-between ml-4 flex-grow">
                                <span class="font-bold text-sm"><%=item.getName()%></span>
                                <span class="text-red-500 text-xs">Apple</span>
                                <a href="#"
                                   class="font-semibold hover:text-red-500 text-gray-500 text-xs">Remove</a>
                            </div>
                        </div>
                        <div class="flex justify-center w-1/5">
                            <div class="qty">
                                <input type="number" class="count" name="qty" value="<%=item.getQuantity()%>" min="0"
                                       style="width: 80px;height: 60px;">
                            </div>
                        </div>
                        <span class="text-center w-1/5 font-semibold text-sm"><%=item.getPrice()%></span>
                        <span class="text-center w-1/5 font-semibold text-sm"><%=item.getPrice() * item.getQuantity()%></span>
                    </div>

                    <%
                        }
                    %>



                    <%--                    <div class="flex items-center hover:bg-gray-100 -mx-8 px-6 py-5">--%>
                    <%--                        <div class="flex w-2/5">--%>
                    <%--                            <div class="w-20">--%>
                    <%--                                <img class="h-24" src="./img/service1.jpeg" alt="">--%>
                    <%--                            </div>--%>
                    <%--                            <div class="flex flex-col justify-between ml-4 flex-grow">--%>
                    <%--                                <span class="font-bold text-sm">Xiaomi Mi 20000mAh</span>--%>
                    <%--                                <span class="text-red-500 text-xs">Xiaomi</span>--%>
                    <%--                                <a href="#"--%>
                    <%--                                   class="font-semibold hover:text-red-500 text-gray-500 text-xs">Remove</a>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                        <div class="flex justify-center w-1/5">--%>
                    <%--                            <div class="qty">--%>
                    <%--                                <input type="number" class="count" name="qty" value="1"--%>
                    <%--                                       style="width: 80px;height: 60px;">--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                        <span class="text-center w-1/5 font-semibold text-sm">$40.00</span>--%>
                    <%--                        <span class="text-center w-1/5 font-semibold text-sm">$40.00</span>--%>
                    <%--                    </div>--%>

                    <%--                    <div class="flex items-center hover:bg-gray-100 -mx-8 px-6 py-5">--%>
                    <%--                        <div class="flex w-2/5">--%>
                    <%--                            <div class="w-20">--%>
                    <%--                                <img class="h-24" src="./img/service1.jpeg" alt="">--%>
                    <%--                            </div>--%>
                    <%--                            <div class="flex flex-col justify-between ml-4 flex-grow">--%>
                    <%--                                <span class="font-bold text-sm">Airpods</span>--%>
                    <%--                                <span class="text-red-500 text-xs">Apple</span>--%>
                    <%--                                <a href="#"--%>
                    <%--                                   class="font-semibold hover:text-red-500 text-gray-500 text-xs">Remove</a>--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                        <div class="flex justify-center w-1/5">--%>
                    <%--                            <div class="qty ">--%>
                    <%--                                <input type="number" class="count" name="qty" value="1"--%>
                    <%--                                       style="width: 80px;height: 60px;">--%>
                    <%--                            </div>--%>
                    <%--                        </div>--%>
                    <%--                        <span class="text-center w-1/5 font-semibold text-sm">$150.00</span>--%>
                    <%--                        <span class="text-center w-1/5 font-semibold text-sm">$150.00</span>--%>
                    <%--                    </div>--%>

                    <a href="shopping" class="flex font-semibold text-indigo-600 text-sm mt-10">

                        <svg class="fill-current mr-2 text-indigo-600 w-4" viewBox="0 0 448 512">
                            <path
                                    d="M134.059 296H436c6.627 0 12-5.373 12-12v-56c0-6.627-5.373-12-12-12H134.059v-46.059c0-21.382-25.851-32.09-40.971-16.971L7.029 239.029c-9.373 9.373-9.373 24.569 0 33.941l86.059 86.059c15.119 15.119 40.971 4.411 40.971-16.971V296z"/>
                        </svg>
                        Continue Shopping
                    </a>
                </div>
                <div id="summary" class="w-1/4 px-8 py-10">
                    <h1 class="font-semibold text-2xl border-b pb-8">Order Summary</h1>
<%--                    <div class="flex justify-between mt-10 mb-5">--%>
<%--                        <span class="font-semibold text-sm uppercase">Items 3</span>--%>
<%--                    </div>--%>
                    <div>
                        <label class="font-medium inline-block mb-3 text-sm uppercase">Payment</label>
                        <select class="block p-2 text-gray-600 w-full text-sm" id="list" onchange="getSelector()">
                            <option id="cash" value="cash">Cash</option>
                            <option id="paypal" value="paypal">PayPal</option>
                        </select>
                    </div>
                    <div class="row justify-center payment" id="payment_method" style="display: none;">
                        <div class="col">
                            <div class="card">
                                <div class="row">
                                    <div class="col radio-group">
                                        <div class="row flex px-3 radio gray mb-3">
                                            <img class="pay" src="./img/paypal.png" style="height: 40px;">
                                            <p class="my-auto">PayPal</p>
                                        </div>
                                    </div>
                                    <div class="col ">
                                        <div class="row px-2">
                                            <div class="form-group col-md-6">
                                                <label class="form-control-label">Name on Card</label>
                                                <input type="text" id="cname" name="cname" placeholder="Johnny Doe">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="form-control-label">Card Number</label>
                                                <input type="text" id="cnum" name="cnum"
                                                       placeholder="1111 2222 3333 4444">
                                            </div>
                                        </div>
                                        <div class="row px-2">
                                            <div class="form-group col-md-6">
                                                <label class="form-control-label">Expiration Date</label>
                                                <input type="text" id="exp" name="exp" placeholder="MM/YYYY">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label class="form-control-label">CVV</label>
                                                <input type="text" id="cvv" name="cvv" placeholder="***">
                                            </div>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="py-10">
                        <label for="promo" class="font-semibold inline-block mb-3 text-sm uppercase">Promo
                            Code</label>
                        <input type="text" id="promo" placeholder="Enter your code" class="p-2 text-sm w-full">
                    </div>
                    <button
                            class="bg-red-500 hover:bg-red-600 px-5 py-2 text-sm text-white uppercase">Apply
                    </button>
                    <div class="border-t mt-8">
                        <div class="flex font-semibold justify-between py-6 text-sm uppercase">
                            <span>Total cost</span>
                            <span><%=total%></span>
                        </div>
                        <form action="MainController" method="POST">
                            <button type="submit" name="action" value="Checkout"
                                    class="bg-indigo-500 font-semibold hover:bg-indigo-600 py-3 text-sm text-white uppercase w-full">
                                Finish
                            </button>
                        </form>

                    </div>

                </div>

            </div>
        </div>
    </div>
</section>
</div>
<script src="https://cdn.tailwindcss.com"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="./js/selector.js"></script>
<script src="./js/counter.js"></script>
<script src="https://unpkg.com/flowbite@1.4.7/dist/flowbite.js"></script>
</body>

</html>