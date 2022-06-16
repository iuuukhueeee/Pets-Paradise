<%@ page import="com.checkout.Cart" %>
<%@ page import="com.checkout.Item" %>
<%@ page import="com.DTO.PetDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: ADMINS
  Date: 6/13/2022
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" width="303">


    <%
        Cart cart   = (Cart) session.getAttribute("CART");
        for (Item item : cart.getCart().values()) {
            String itemID = item.getItemID().split("-")[0];
            if(itemID.equals("PRODUCT")){

    %>
    <tr>
        <td width="168"><b>Name</b></td>
        <td width="168"><b>Quantity</b></td>
        <td width="168"><b>Price</b></td>
        <td width="168"><b>ImportDate</b></td>
        <td width="168"><b>ExpiredDate</b></td>
    </tr>
    <tr>
        <td width="168"><%= item.getProduct().getName() %></td>
        <td width="119"><%= item.getProduct().getQuantity() %></td>
        <td width="168"><%= item.getProduct().getPrice() %></td>
        <td width="168"><%= item.getProduct().getImportDate() %></td>
        <td width="168"><%= item.getProduct().getExpiredDate() %></td>
    </tr>

    <%
    } else if (itemID.equals("SERVICE")) {
        Map<String, PetDTO> petInfo = (Map<String, PetDTO>) session.getAttribute("PET_INFO");
        for(PetDTO getInfo : petInfo.values()){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    %>
    <tr>
        <td width="168"><b>PetName</b></td>
        <td width="168"><b>Age</b></td>
        <td width="168"><b>BookingTime</b></td>
        <td width="168"><b>AnimalPicture</b></td>
    </tr>
    <tr>
        <td width="119"><%= getInfo.getAnimalName()%></td>
        <td width="168"><%= getInfo.getAnimalAge() %></td>
        <td width="119"><%= formatter.format(getInfo.getBookingTime())%></td>
        <td width="119"><img src="<%= getInfo.getAnimalPicture()%> "/> </td>
    </tr>

    <tr>
        <td width="168"><b>ServiceName</b></td>
        <td width="168"><b>ServicePrice</b></td>
    </tr>
    <tr>
        <td width="168"><%= item.getService().getServiceName() %></td>
        <td width="119"><%= item.getService().getServicePrice() %></td>
    </tr>

    <%
                }
            }
        }
    %>
    <form action="MainController" method="post">
        <button type="submit" name="action" value="checkout">DONE?</button>
    </form>
</table>
</body>
</html>
