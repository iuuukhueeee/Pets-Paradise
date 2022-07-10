package com.controllers;

import com.DAO.ServiceDAO;
import com.DAO.ShopDAO;
import com.DTO.ServiceDTO;
import com.DTO.ShopDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadServiceController", value = "/LoadServiceController")
public class AdminLoadServiceController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "adminService.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            List<ServiceDTO> listService;
            ServiceDAO serviceDAO = new ServiceDAO();
            listService = serviceDAO.getAll();
            ShopDAO shopDAO = new ShopDAO();
            List<ShopDTO> shop = shopDAO.getAll();
            if (listService != null) {
                boolean onSearch = Boolean.TRUE == request.getSession().getAttribute("ON_SEARCH");
                if (!onSearch) {
                    request.setAttribute("SERVICE_LIST", listService);
                }
                request.setAttribute("SHOP_LIST", shop);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at LoadServiceController: " + e.toString());
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
