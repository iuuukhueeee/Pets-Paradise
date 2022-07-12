package com.controllers;

import com.DAO.OrderDAO;
import com.DAO.OrderDetailDAO;
import com.DTO.OrderDTO;
import com.DTO.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveFromCartController", value = "/RemoveFromCartController")
public class RemoveFromCartController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "checkout";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            HttpSession session = request.getSession(false);
            if (session == null) {
                request.setAttribute("ERROR", "You need to login!");
                request.getRequestDispatcher(url).forward(request,response);
            }
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            OrderDAO orderDAO = new OrderDAO();
            OrderDTO order = orderDAO.getCartByUsername(user.getUsername());
            String ID = request.getParameter("ID");
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            if (orderDetailDAO.removeItem(ID, order.getOrderID())) {
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at RemoveFromCartController: " + e);
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
