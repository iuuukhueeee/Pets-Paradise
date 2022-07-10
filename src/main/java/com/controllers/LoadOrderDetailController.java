package com.controllers;

import com.DAO.OrderDetailDAO;
import com.DAO.PetDAO;
import com.DTO.OrderDetailDTO;
import com.DTO.PetDTO;
import com.DTO.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadOrderDetailController", value = "/LoadOrderDetailController")
public class LoadOrderDetailController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "userOrder";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            String orderID = request.getParameter("orderID");
            List<OrderDetailDTO> orderDTList;
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            orderDTList = orderDetailDAO.getByOrderID(orderID);
            if(orderDTList != null) {
                request.setAttribute("ORDER_DETAIL_LIST", orderDTList);
                url = SUCCESS;
            }


        } catch (Exception e) {
            log("Error at LoadPetInfoController: " + e.toString());
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
