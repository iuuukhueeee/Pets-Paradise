package com.controllers;

import com.DAO.*;
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

@WebServlet(name = "LoadOrderDetail", value = "/LoadOrderDetail")
public class LoadOrderDetail extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "adminOrderDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        try {
            String ID = request.getParameter("ID");
            OrderDAO orderDAO = new OrderDAO();
            OrderDTO order = orderDAO.getOrderByID(ID);
            ShopDAO shopDAO = new ShopDAO();
            Map<String, ShopDTO> shop = shopDAO.getByID(order.getShop());
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            ProductDAO productDAO = new ProductDAO();
            ProductDTO product;
            ServiceDAO serviceDAO = new ServiceDAO();
            ServiceDTO service;
            List<OrderDetailDTO> details = orderDetailDAO.getListDetailByOrderID(ID);
            PetDAO petDAO = new PetDAO();
            Map<String, PetDTO> petInfo = new HashMap<>();
            List<ItemDetails> list = new ArrayList<>();
            int countProduct = 0;
            int countService = 0;
            Map<String, ServiceDTO> serviceMap = new HashMap<>();

            if (details.size() > 0) {
                for (int i = 0; i < details.size(); i++) {
                    String itemID = details.get(i).getItemID();
                    String itemTypeID = itemID.split("-")[0];
                    if ("PRODUCT".equals(itemTypeID)) {
                        product = productDAO.getByID(itemID);
                        list.add(new ItemDetails(product.getImage(), product.getName(), details.get(i).getQuantity(), product.getPrice()));
                        countProduct++;
                    } else if ("SERVICE".equals(itemTypeID)) {
                        service = serviceDAO.getByID(itemID);
                        serviceMap.put(itemID, service);
                        String petID = details.get(i).getPetID();
                        PetDTO pet = petDAO.getByID(petID);
                        petInfo.put(itemID, pet);
                        list.add(new ItemDetails("", service.getServiceName(), 1, service.getServicePrice()));
                        countService++;
                    }
                }
            }
            request.setAttribute("ORDER", order);
            request.setAttribute("ORDER_DETAILS", details);
            request.setAttribute("PET_DETAILS", petInfo);
            request.setAttribute("ITEM_DETAILS", list);
            request.setAttribute("SHOP_DETAILS", shop);
            request.setAttribute("TOTAL_PRODUCT", countProduct);
            request.setAttribute("TOTAL_SERVICE", countService);
            request.setAttribute("SERVICE_MAP", serviceMap);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at LoadOrderDetail: " + e);
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
