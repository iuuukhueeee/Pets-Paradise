package com.controllers;

import com.checkout.Cart;
import com.checkout.Item;
import com.DAO.OrderDAO;
import com.DTO.OrderDTO;
import com.DAO.OrderDetailDAO;
import com.DTO.OrderDetailDTO;
import com.DAO.PetDAO;
import com.DTO.PetDTO;
import com.DAO.ProductDAO;
import com.DTO.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CheckOutController", value = "/CheckOutController")
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "index.jsp";
    private static final String SUCCESS = "CheckOut.jsp";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if(cart == null) {
                cart = new Cart();
                request.setAttribute("ERROR" , "Your Shopping cart is Empty!");
            }
            else{
                OrderDAO orderDAO = new OrderDAO();
                OrderDetailDAO orderDetail = new OrderDetailDAO();
                OrderDetailDTO orderDT = null;
                ProductDAO product = new ProductDAO();
                if(cart.getCart().size() == 0){
                    request.setAttribute("ERROR", "Your cart is empty!");
                }
                else {
                    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                    String username = user.getUsername();
                    OrderDTO order = orderDAO.createOrder(username);
                    for(Item item : cart.getCart().values()){
                        orderDT = orderDetail.createOrderDetail(order.getOrderID(),item);
                        product.updateQuantity(item);
                    }
                    String itemType = orderDT.getItemID().split("-")[0];

                    if(itemType.equals("SERVICE")){
                        List<String> petInfo = (ArrayList) session.getAttribute("PET_INFO");
                        String orderDetailID = orderDT.getOrderDetailID();
                        String animalID = petInfo.get(0);
                        String animalName = petInfo.get(1);
                        int animalAge = Integer.parseInt(petInfo.get(2));
                        String animalDescription = petInfo.get(3);
                        String getDate = petInfo.get(4);
                        Date bookingTime = formatter.parse(getDate);
                        PetDTO pet = new PetDTO("",animalID,orderDetailID,animalName,animalAge,"",animalDescription,bookingTime);
                        PetDAO petDAO = new PetDAO();
                        petDAO.addPetInfo(pet);
                        request.setAttribute("ORDER_ID",order.getOrderID());
                        request.setAttribute("CART",cart);
                        session.removeAttribute("CART");
                    }
                    request.setAttribute("ORDER_ID",order.getOrderID());
                    request.setAttribute("CART",cart);
                    url = SUCCESS;
                    session.removeAttribute("CART");
                }

            }
        } catch (Exception e) {
            log("Error at CheckOutController: " + e.toString());

        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
