package com.controllers;

import com.DAO.OrderDAO;
import com.DAO.ProductDAO;
import com.DTO.OrderDTO;
import com.DTO.ProductDTO;
import com.DTO.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchOrderController", value = "/SearchOrderController")
public class SearchOrderController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS_US = "index.jsp";
    private static final String SUCCESS_AD = "adminOrder.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        try {
            String searchOrder = request.getParameter("SearchOrder");
            if(searchOrder == null) searchOrder = "";
            OrderDAO orderDAO = new OrderDAO();

            List<OrderDTO> list = orderDAO.getListOrder(searchOrder);

            request.setAttribute("LIST_ORDER", list);

            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser.getRoleID().equals("US")) {
                url = SUCCESS_US;
            } else if (loginUser.getRoleID().equals("AD")) {
                url = SUCCESS_AD;
            }
        } catch (Exception e) {
            log("Error at SearchProductController: " + e.toString());
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
