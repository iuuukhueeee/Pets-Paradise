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
    private static final String SUCCESS = "services";
    private static final String OUT_OF_STOCK = "checkout";
    private static final String CHECK_LOGIN = "login.jsp";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            String id[] = request.getParameterValues("ID");
            String quantity[] = request.getParameterValues("qty");

            HttpSession session = request.getSession(false);
            ProductDAO productDAO = new ProductDAO();
            ServiceDAO serviceDAO = new ServiceDAO();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null) {
                url = CHECK_LOGIN;
            } else {
                OrderDAO orderDAO = new OrderDAO();
                OrderDTO order = orderDAO.getCartByUsername(user.getUsername());
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                for (int i = 0; i < id.length; i++) {
                    orderDetailDAO.overrideQuantity(id[i], order.getOrderID(), Integer.parseInt(quantity[i]));
                }
                List<OrderDetailDTO> list = orderDetailDAO.getByOrderID(order.getOrderID());
                List<OrderDetailDTO> outOfStock = new ArrayList<>();

                float total = 0;

                String orderTable = "";

                for (OrderDetailDTO orderDetail : list) {
                    String itemID = orderDetail.getItemID();
                    if ("PRODUCT".equals(itemID.split("-")[0])) {
                        ProductDTO product = productDAO.getByID(itemID);
                        if (product.getQuantity() < orderDetail.getQuantity()) {
                            outOfStock.add(orderDetail);
                        }
                    }
                }

                if (outOfStock.isEmpty()) {

                    for (OrderDetailDTO orderDetail : list) {
                        String itemID = orderDetail.getItemID();
                        if ("PRODUCT".equals(itemID.split("-")[0])) {
                            ProductDTO product = productDAO.getByID(itemID);
                            total += product.getPrice() * orderDetail.getQuantity();
                            orderTable += "<tr><td>" + product.getName() + "</td><td>"
                                    + orderDetail.getOrderDetailPrice()
                                    + "</td><td>" + orderDetail.getQuantity()
                                    + "</td><td>" + orderDetail.getQuantity() * orderDetail.getOrderDetailPrice() + "</td></tr>";
                            productDAO.updateQuantity(itemID, orderDetail.getQuantity());
                        } else if ("SERVICE".equals(itemID.split("-")[0])) {
                            ServiceDTO service = serviceDAO.getByID(itemID);
                            total += service.getServicePrice();
                        }
                    }

                    orderDAO.updateTotal(order.getOrderID(), total);
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
                } else {
                    request.setAttribute("LIST_OUT_OF_STOCK", outOfStock);
                    request.setAttribute("ERROR", "Some items are out of stock!");
                    url = OUT_OF_STOCK;
                }
            }

        } catch (Exception e) {
            log("Error at CheckoutController: " + e);
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
