package com.controllers;

import com.DAO.OrderDAO;
import com.DAO.OrderDetailDAO;
import com.DAO.PetDAO;
import com.DTO.OrderDTO;
import com.DTO.OrderDetailDTO;
import com.DTO.PetDTO;
import com.DTO.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserLoadOrderController", value = "/UserLoadOrderController")
public class UserLoadOrderController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "userOrder.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
//            UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
//            List<OrderDetailDTO> orderDTList = new ArrayList<>();
//            OrderDetailDTO orderDT = new OrderDetailDTO("",0,"","",0,"",null);
//            orderDTList.add(orderDT);
//            List<OrderDTO> orderList;
//            OrderDAO orderDAO = new OrderDAO();
//            orderList = orderDAO.getListByUsername(user.getUsername());
//            if (orderList != null) {
//                request.setAttribute("ORDER_LIST", orderList);
//                request.setAttribute("ORDER_DETAIL_LIST",orderDTList);
//                    url = SUCCESS;
//            }
            UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
            List<OrderDTO> orderList;
            OrderDAO orderDAO = new OrderDAO();
            orderList = orderDAO.getListByUsername(user.getUsername());
            request.setAttribute("ORDER_LIST", orderList);
            url = SUCCESS;


        } catch (Exception e) {
            log("Error at UserLoadOrderController: " + e.toString());
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
