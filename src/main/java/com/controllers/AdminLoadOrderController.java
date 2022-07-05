package com.controllers;

import com.DAO.OrderDAO;
import com.DTO.OrderDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadOrderController", value = "/LoadOrderController")
public class AdminLoadOrderController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "adminOrder.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            List<OrderDTO> listOrder;
            OrderDAO orderDAO = new OrderDAO();
            listOrder = orderDAO.getAll();
            if (listOrder != null) {
                request.setAttribute("LIST_ORDER", listOrder);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at LoadOrderController: " + e.toString());
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
