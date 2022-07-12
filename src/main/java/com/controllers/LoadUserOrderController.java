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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoadUserOrderController", value = "/LoadUserOrderController")
public class LoadUserOrderController extends HttpServlet {

    private static final String SUCCESS = "userOrder.jsp";
    private static final String LOGIN = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            Map<String, List<OrderDetailDTO>> map = new HashMap<>();
            Map<String, String> mapItemName = new HashMap<>();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null) {
                request.getRequestDispatcher(LOGIN).forward(request, response);
            }
            OrderDAO orderDAO = new OrderDAO();
            List<OrderDTO> listOrder = orderDAO.getOrderByUsername(user.getUsername());
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            List<OrderDetailDTO> listOrderDetail = null;
            ProductDTO product;
            ServiceDTO service;
            ProductDAO productDAO = new ProductDAO();
            ServiceDAO serviceDAO = new ServiceDAO();

            if (listOrder.size() > 0 && listOrder != null) {
                for (OrderDTO order : listOrder) {
                    listOrderDetail = orderDetailDAO.getListDetailByOrderID(order.getOrderID());
                    for (OrderDetailDTO item : listOrderDetail) {
                        String itemID = item.getItemID().split("-")[0];
                        if ("PRODUCT".equals(itemID)) {
                            if (!mapItemName.containsKey(item.getItemID())) {
                                product = productDAO.getByID(item.getItemID());
                                mapItemName.put(item.getItemID(), product.getName());
                            }
                        } else if ("SERVICE".equals(itemID)) {
                            if (!mapItemName.containsKey(item.getItemID())) {
                                service = serviceDAO.getByID(item.getItemID());
                                mapItemName.put(item.getItemID(), service.getServiceName());
                            }
                        }
                    }
                    map.put(order.getOrderID(), listOrderDetail);
                }
                request.setAttribute("USER_LIST_ORDER", listOrder);
                request.setAttribute("USER_MAP_ORDER_DETAIL", map);
                request.setAttribute("ITEM_NAME", mapItemName);
            }
        } catch (Exception e) {
            log("Error at LoadUserOrderController: " + e);
        } finally {
            request.getRequestDispatcher(SUCCESS).forward(request, response);
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
