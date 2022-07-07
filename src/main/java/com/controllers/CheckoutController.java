package com.controllers;

import com.DAO.*;

import com.DTO.*;
import com.utils.EmailUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CheckoutController", value = "/CheckoutController")
public class CheckoutController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "index.jsp";
    private static final String CHECK_LOGIN = "login.jsp";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            HttpSession session = request.getSession(false);
            ProductDAO productDAO = new ProductDAO();
            ServiceDAO serviceDAO = new ServiceDAO();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            OrderDAO orderDAO = new OrderDAO();
            OrderDTO order = orderDAO.getByUsername(user.getUsername());
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            List<OrderDetailDTO> list = orderDetailDAO.getByOrderID(order.getOrderID());
            List<OrderDetailDTO> outOfStock = new ArrayList<>();

            float total = 0;

            String orderTable = "";

            for (OrderDetailDTO orderDetail: list) {
                String itemID = orderDetail.getItemID();
                if ("PRODUCT".equals(itemID.split("-")[0])) {
                    ProductDTO product = productDAO.getByID(itemID);
                    if (product.getQuantity() < orderDetail.getQuantity()) {
                        outOfStock.add(orderDetail);
                    } else {
                        total += orderDetail.getOrderDetailPrice() * orderDetail.getQuantity();
                        orderTable += "<tr><td>" + product.getName() + "</td><td>"
                                + orderDetail.getOrderDetailPrice()
                                + "</td><td>" + orderDetail.getQuantity()
                                + "</td><td>" + orderDetail.getQuantity() * orderDetail.getOrderDetailPrice() + "</td></tr>";
                        productDAO.updateQuantity(itemID, orderDetail.getQuantity());
                    }
                } else if ("SERVICE".equals(itemID.split("-")[0])) {
                    total += orderDetail.getOrderDetailPrice();
                }
            }

            if (orderDAO.updateTotal(order.getOrderID(), total)) {
                if (orderDAO.checkout(order.getOrderID())) {

                    String emailContent = "<html>" +
                            "<h1>Thank you for buying</h1>" +
                            "<table><tr><th>Name</th><th>Price</th><th>Quantity</th><th>Total</th></tr>" + orderTable +
                            "</table>" +
                            "</html>";

                    EmailUtils.sendConfirmOrder(order.getOrderID(), user.getEmail(), emailContent);

                    orderDetailDAO.checkout(order.getOrderID());
                    url = SUCCESS;
                }

            }

        } catch (Exception e) {
            log("Error at CheckoutController: " + e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

//        try {
//            HttpSession session = request.getSession();
//            OrderDAO orderDAO = new OrderDAO();
//            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
//            CartDAO cart = new CartDAO();
//            ProductDAO productDAO = new ProductDAO();
//            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
//            if(user == null){
//                url = CHECK_LOGIN;
//                request.setAttribute("ERROR","Please Login to use this function!");
//                request.getRequestDispatcher(url).forward(request, response);
//            }
//            String username = user.getUsername();
//            OrderDetailDTO orderDetail = null;
//
//            for(int i = 0; i < cart.getByUsername(username).size(); i++){
//
//                //Get the Product/Service Price
//                String getPrice = cart.getPrice(cart.getByUsername(username).get(i).getItemID());
//                float price = Float.parseFloat(getPrice);
//
//                //Create OrderDetail
//
//                //Get the ItemType.
//                String[] orderDTID = orderDetail.getItemID().split("-");
//                String itemType = orderDTID[0];
//
//                //Update Quantity if it's a Product
//                if(itemType.equals("PRODUCT")){
//                    if(productDAO.updateQuantity(orderDetail.getItemID(),cart.getByUsername(username).get(i))){
//                        System.out.println("Update successfully");
//                    }
//                }
//
//                //If it a Service input petInfo into database
//                else if(itemType.equals("SERVICE")){
//                    Map<String, PetDTO> petInfo = (Map<String, PetDTO>) session.getAttribute("PET_INFO");
//                    String orderDetailID = orderDetail.getOrderDetailID();
//                    PetDTO pet = null;
//                    for(PetDTO getInfo : petInfo.values()){
//                        String animalID = getInfo.getAnimalID();
//                        String animalName = getInfo.getAnimalName();
//                        int animalAge = getInfo.getAnimalAge();
//                        String animalPicture = getInfo.getAnimalPicture();
//                        String animalDescription = getInfo.getAnimalDescription();
//                        Date bookingTime = getInfo.getBookingTime();
//                        pet = new PetDTO("",animalID,orderDetailID,animalName,animalAge,animalPicture,animalDescription,bookingTime);
//                        PetDAO petDAO = new PetDAO();
//                        petDAO.addPetInfo(pet);
//                    }
//                }
//            }
//            if(cart.updateCartStatus(username)){
//                request.setAttribute("MESSAGE", "CHECKOUT SUCCESSFULLY");
//                url = SUCCESS;
//            }
//        } catch (Exception e) {
//            log("Error at CheckoutController: " + e.toString());
//
//        } finally {
//            request.getRequestDispatcher(url).forward(request, response);
//        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
