package com.controllers;

import com.DAO.OrderDAO;
import com.DTO.OrderDTO;
import com.DTO.UserDTO;
import com.utils.PaymentServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AuthorizePaymentController", value = "/AuthorizePaymentController")
public class AuthorizePaymentController extends HttpServlet {

    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                OrderDAO orderDAO = new OrderDAO();
                UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
                OrderDTO order = orderDAO.getCartByUsername(user.getUsername());

                PaymentServices paymentServices = new PaymentServices();
                String approvalLink = paymentServices.authorizePayment(order);
                url = approvalLink;
            }
        } catch (Exception e) {
            log("Error at AuthorizePaymentController: " + e);
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
