<%@ page import="com.checkout.Cart" %>
<%@ page import="com.checkout.Item" %>
<%@ page import="com.DTO.PetDTO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.DTO.UserDTO" %>
<%@ page import="com.DAO.CartDAO" %>
<%@ page import="com.DAO.ProductDAO" %>
<%@ page import="com.DTO.ProductDTO" %>
<%@ page import="com.DAO.ServiceDAO" %>
<%@ page import="com.DTO.ServiceDTO" %><%--
  Created by IntelliJ IDEA.
  User: ADMINS
  Date: 6/13/2022
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" width="303">


    <%
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        CartDAO getCart = new CartDAO();
        ProductDAO getProduct = new ProductDAO();
        ProductDTO product = null;
        for (int i = 0; i < getCart.getItemOnCartByUsername(user.getUsername()).size(); i++) {
            String itemID = getCart.getItemOnCartByUsername(user.getUsername()).get(i).getItemID();
            String itemTypeID = itemID.split("-")[0];
            product = getProduct.getByID(itemID);
            if(itemTypeID.equals("PRODUCT")){

    %>
    <tr>
        <td width="168"><b>Name</b></td>
        <td width="168"><b>Quantity</b></td>
        <td width="168"><b>Price</b></td>
        <td width="168"><b>ImportDate</b></td>
        <td width="168"><b>ExpiredDate</b></td>
    </tr>
    <tr>
        <td width="168"><%= product.getName()  %></td>
        <td width="119"><%= getCart.getItemOnCartByUsername(user.getUsername()).get(i).getQuantity() %></td>
        <td width="168"><%= product.getPrice() %></td>
        <td width="168"><%= product.getImportDate() %></td>
        <td width="168"><%= product.getExpiredDate() %></td>
    </tr>

    <%
    } else if (itemTypeID.equals("SERVICE")) {
        Map<String, PetDTO> petInfo = (Map<String, PetDTO>) session.getAttribute("PET_INFO");
        ServiceDAO getService = new ServiceDAO();
        ServiceDTO service = getService.getByID(itemID);
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
        <td width="168"><%= service.getServiceName() %></td>
        <td width="119"><%= service.getServicePrice() %></td>
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
