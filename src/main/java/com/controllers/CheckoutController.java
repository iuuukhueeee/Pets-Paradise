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
import java.util.Date;
import java.util.Map;

@WebServlet(name = "CheckoutController", value = "/CheckoutController")
public class CheckoutController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "index.jsp";
    private static final String CHECK_LOGIN = "login.jsp";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            OrderDAO orderDAO = new OrderDAO();
            OrderDetailDAO orderDetail = new OrderDetailDAO();
            CartDAO getCart = new CartDAO();
            ProductDAO product = new ProductDAO();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if(user == null){
                url = CHECK_LOGIN;
                request.setAttribute("ERROR","Please Login to use this function!");
                request.getRequestDispatcher(url).forward(request, response);
            }
            String username = user.getUsername();
            OrderDetailDTO orderDT = null;
            OrderDTO order = orderDAO.createOrder(username);


            for(int i = 0; i < getCart.getByUsername(username).size(); i++){

                //Get the Product/Service Price
                String getPrice = getCart.getPrice(getCart.getByUsername(username).get(i).getItemID());
                float price = Float.parseFloat(getPrice);

                //Create OrderDetail
                orderDT = orderDetail.createOrderDetail(order.getOrderID(), getCart.getByUsername(username).get(i), price);

                //Get the ItemType.
                String[] orderDTID = orderDT.getItemID().split("-");
                String itemType = orderDTID[0];

                //Update Quantity if it's a Product
                if(itemType.equals("PRODUCT")){
                    if(product.updateQuantity(orderDT.getItemID(),getCart.getByUsername(username).get(i))){
                        System.out.println("Update successfully");
                    }
                }

                //If it a Service input petInfo into database
                else if(itemType.equals("SERVICE")){
                    Map<String, PetDTO> petInfo = (Map<String, PetDTO>) session.getAttribute("PET_INFO");
                    String orderDetailID = orderDT.getOrderDetailID();
                    PetDTO pet = null;
                    for(PetDTO getInfo : petInfo.values()){
                        String animalID = getInfo.getAnimalID();
                        String animalName = getInfo.getAnimalName();
                        int animalAge = getInfo.getAnimalAge();
                        String animalPicture = getInfo.getAnimalPicture();
                        String animalDescription = getInfo.getAnimalDescription();
                        Date bookingTime = getInfo.getBookingTime();
                        pet = new PetDTO("",animalID,orderDetailID,animalName,animalAge,animalPicture,animalDescription,bookingTime);
                        PetDAO petDAO = new PetDAO();
                        petDAO.addPetInfo(pet);
                    }
                }
            }
            if(getCart.updateCartStatus(username)){
                request.setAttribute("MESSAGE", "CHECKOUT SUCCESSFULLY");
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at CheckoutController: " + e.toString());

        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
