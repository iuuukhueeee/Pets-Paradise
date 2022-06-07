package com.controllers;

import com.checkout.Cart;
import com.checkout.Item;
import com.product.ProductDAO;
import com.service.ServiceDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddToCartController", value = "/AddToCartController")
public class AddToCartController extends HttpServlet {


    private static final String ERROR = "index.jsp";
    private static final String SUCCESS = "ViewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            Item item = new Item();
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String ID = request.getParameter("ID");
            item.setItemID(ID);
            String[] itemTypeID = ID.split("-");
            ProductDAO product = new ProductDAO();
            ServiceDAO service = new ServiceDAO();
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if(cart == null){
                cart = new Cart();
            }
            if(itemTypeID[0].equals("PRODUCT")){
                item.setProduct(product.getByID(item.getItemID()));
                item.getProduct().setQuantity(quantity);
                cart.addProduct(item);
            }
            else if(itemTypeID[0].equals("SERVICE")){
                item.setService(service.getByID(item.getItemID()));
                cart.addService(item);
            }
            session.setAttribute("CART",cart);
            request.setAttribute("MESSAGE","Added Successfully");
            url = SUCCESS;



        } catch (Exception e) {

        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
