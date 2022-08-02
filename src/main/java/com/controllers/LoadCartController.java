package com.controllers;

import com.DAO.OrderDAO;
import com.DAO.OrderDetailDAO;
import com.DAO.ProductDAO;
import com.DAO.ServiceDAO;
import com.DTO.*;
import com.checkout.ItemDetails;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoadCartController", value = "/LoadCartController")
public class LoadCartController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String SUCCESS = "checkout.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            HttpSession session = request.getSession(false);
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null) {
                url = LOGIN;
                request.setAttribute("ERROR", "You need to login!");
            }
            else {
                ProductDAO productDAO = new ProductDAO();
                ServiceDAO serviceDAO = new ServiceDAO();
                ProductDTO product;
                ServiceDTO service;
                List<ItemDetails> list = new ArrayList<>();
                int countProduct = 0, countService = 0;
                Map<String, String> key = new HashMap<>();

                OrderDAO orderDAO = new OrderDAO();
                OrderDTO order = orderDAO.getCartByUsername(user.getUsername());
                if (order != null) {
                    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                    List<OrderDetailDTO> cart = orderDetailDAO.getByOrderID(order.getOrderID());
                    if (cart.size() > 0 && cart != null) {
                        for (int i = 0; i < cart.size(); i++) {
                            String itemID = cart.get(i).getItemID();
                            String itemTypeID = itemID.split("-")[0];
                            if ("PRODUCT".equals(itemTypeID)) {
                                product = productDAO.getByID(itemID);
                                list.add(new ItemDetails(product.getImage(), product.getName(), cart.get(i).getQuantity(), product.getPrice()));
                                if (!key.containsKey(product.getName())) {
                                    key.put(product.getName(), product.getProductID());
                                }
                                countProduct++;
                            } else if ("SERVICE".equals(itemTypeID)) {
                                service = serviceDAO.getByID(itemID);
                                list.add(new ItemDetails("", service.getServiceName(), 1, service.getServicePrice()));
                                if (!key.containsKey(service.getServiceName())) {
                                    key.put(service.getServiceName(), service.getServiceID());
                                }
                                countService++;
                            }
                        }
                    }
                }
                session.setAttribute("CART", list);
                request.setAttribute("COUNT_PRODUCT", countProduct);
                request.setAttribute("COUNT_SERVICE", countService);
                request.setAttribute("KEY", key);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at LoadCartController: " + e);
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
