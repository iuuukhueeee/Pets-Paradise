<%@ page import="com.DTO.OrderDTO" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.DTO.PetDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.checkout.ItemDetails" %>
<%@ page import="com.DTO.OrderDetailDTO" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.DTO.ServiceDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/animate.css">
    <link rel="stylesheet" href="https://unpkg.com/flowbite@1.4.7/dist/flowbite.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com"/>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="./css/orderAdmin.css"/>
    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet">
    <link rel="shortcut icon" type="img/png" href="/img/paw-solid.svg"/>
    <title>Order - Admin</title>
</head>

<body>

<%
    OrderDTO order = (OrderDTO) request.getAttribute("ORDER");
    List<OrderDetailDTO> details = (List<OrderDetailDTO>) request.getAttribute("ORDER_DETAILS");
    Map<String, PetDTO> petInfo = (Map<String, PetDTO>) request.getAttribute("PET_DETAILS");
    List<ItemDetails> list = (List<ItemDetails>) request.getAttribute("ITEM_DETAILS");
    Map<String, ServiceDTO> serviceMap = (Map<String, ServiceDTO>) request.getAttribute("SERVICE_MAP");
    Map<String, ShopDTO> shopMap = (Map<String, ShopDTO>) request.getAttribute("SHOP_DETAILS");
    int countProduct = (int) request.getAttribute("TOTAL_PRODUCT");
    int countService = (int) request.getAttribute("TOTAL_SERVICE");
%>

<nav class="flex" aria-label="Breadcrumb">
    <div class="container">
        <ol class="inline-flex items-center space-x-1 md:space-x-3">
            <li class="inline-flex items-center">
                <a href="index"
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
                    <a href="./admin/admin.html"
                       class="ml-1 text-sm font-medium text-gray-700 hover:text-gray-900 md:ml-2 dark:text-gray-400 dark:hover:text-white">Admin
                        Page</a>
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
                    <span class="ml-1 text-sm font-medium text-gray-500 md:ml-2 dark:text-gray-400">Order -
							Admin</span>
                </div>
            </li>
        </ol>
    </div>
</nav>

<h1 class="animate__animated animate__bounceInDown">Order Mangement</h1>
<section class="order-summary container">
    <ul>
        <li>Order Information</li>
    </ul>
    <div class="overflow-x-auto relative ">
        <table class="text-sm text-left text-gray-500 dark:text-gray-400">
            <thead class="text-xs text-gray-700 uppercase dark:text-gray-400">
            <tr>

                <th scope="col" class="py-3 px-6">
                    Order Id
                </th>
                <th scope="col" class="py-3 px-6">
                    Name
                </th>
                <th scope="col" class="py-3 px-6 bg-gray-50 dark:bg-gray-800">
                    Order Placing-Date
                </th>
                <th scope="col" class="py-3 px-6 bg-gray-50 dark:bg-gray-800">
                    Total Products
                </th>
                <th scope="col" class="py-3 px-6 bg-gray-50 dark:bg-gray-800">
                    Total Services
                </th>
                <th scope="col" class="py-3 px-6 bg-gray-50 dark:bg-gray-800">
                    Total Cost
                </th>
            </tr>
            </thead>
            <tbody>
            <tr class="border-b border-gray-200 dark:border-gray-700">

                <td class="py-4 px-6">
                    <%=order.getOrderID()%>
                </td>
                <td class="py-4 px-6">
                    <%=order.getUsername()%>
                </td>
                <td class="py-4 px-6 bg-gray-50 dark:bg-gray-800">
                    <%=order.getOrderDate()%>
                </td>
                <td class="py-4 px-6 bg-gray-50 dark:bg-gray-800">
                    <%=countProduct%>
                </td>
                <td class="py-4 px-6 bg-gray-50 dark:bg-gray-800">
                    <%=countService%>
                </td>
                <td class="py-4 px-6 bg-gray-50 dark:bg-gray-800">
                    <%=order.getTotal()%>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</section>

<section class="container order-details" style="margin-bottom: 4rem;">
    <ul>
        <li>Order Details</li>
    </ul>
    <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
                <th scope="col" class="px-6 py-3">
                    Pet's name
                </th>
                <th scope="col" class="px-6 py-3">
                    Age
                </th>
                <th scope="col" class="px-6 py-3">
                    Description
                </th>
                <th scope="col" class="px-6 py-3">
                    Booking Time
                </th>
                <th scope="col" class="px-6 py-3">
                    Breed
                </th>
                <th scope="col" class="px-6 py-3">
                    Store
                </th>
                <th scope="col" class="px-6 py-3">
                    Service Name
                </th>
                <th scope="col" class="px-6 py-3">
                    Image
                </th>
            </tr>
            </thead>
            <tbody>
            <%
                if (!petInfo.isEmpty() && petInfo != null) {
                    for (String key : petInfo.keySet()) {
                        PetDTO pet = petInfo.get(key);
                        Date bookingTime = null;
                        String service = "";
                        for (OrderDetailDTO orderDetailDTO : details) {
                            if (orderDetailDTO.getItemID().equals(key)) {
                                bookingTime = orderDetailDTO.getBookingTime();
                                service = serviceMap.get(orderDetailDTO.getItemID()).getServiceName();
                            }
                        }
            %>
            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                <th scope="row" class="px-6 py-4 font-medium text-gray-900 dark:text-white whitespace-nowrap">
                    <%=pet.getAnimalName()%>
                </th>
                <td class="px-6 py-4">
                    <%=pet.getAnimalAge()%>
                </td>
                <td class="px-6 py-4">
                    <%=pet.getAnimalDescription()%>
                </td>
                <td class="px-6 py-4">
                    <%=bookingTime%>
                </td>
                <td class="px-6 py-4">
                    <%=pet.getAnimalName()%>
                </td>
                <td class="px-6 py-4">
                    <%=shopMap.get(order.getShop()).getShopName()%>
                </td>
                <td class="px-6 py-4">
                    <%=service%>
                </td>
                <td class="px-6 py-4">
                    <img src="<%=pet.getAnimalPicture()%>" style="height: 40px;width:50px"/>
                </td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>
</section>

<section class="container">
    <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
            <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
                <th scope="col" class="px-6 py-3">
                    Product name/ Services Name
                </th>
                <th scope="col" class="px-6 py-3">
                    Price
                </th>
                <th scope="col" class="px-6 py-3">
                    Quantity
                </th>
                <th scope="col" class="px-6 py-3">
                    Total
                </th>
            </tr>
            </thead>
            <tbody>
            <%
                for (ItemDetails item : list) {
            %>
            <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700">
                <th scope="row" class="px-6 py-4 font-medium text-gray-900 dark:text-white whitespace-nowrap">
                    <%=item.getName()%>
                </th>
                <td class="px-6 py-4">
                    <%=item.getPrice()%>
                </td>
                <td class="px-6 py-4">
                    <%=item.getQuantity()%>
                </td>
                <td class="px-6 py-4">
                    <%=item.getQuantity() * item.getPrice()%>
                </td>
            </tr>
            <%
                }
            %>

            </tbody>
        </table>
    </div>
</section>
<script src="https://unpkg.com/flowbite@1.4.7/dist/flowbite.js"></script>
<script src="js/wow.min.js"></script>
<script>
    new WOW().init();
</script>

</body>

</html>