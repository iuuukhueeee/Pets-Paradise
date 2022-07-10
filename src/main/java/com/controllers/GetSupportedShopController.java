package com.controllers;

import com.DAO.ServiceDAO;
import com.DAO.ShopDAO;
import com.DAO.ShopServiceDetailDAO;
import com.DTO.ServiceDTO;
import com.DTO.ShopDTO;
import com.DTO.ShopServiceDetailDTO;
import com.DTO.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "GetSupportedShopController", value = "/GetSupportedShopController")
public class GetSupportedShopController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "form.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        try {
            UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
            if (user == null) {
                request.setAttribute("ERROR", "You need to login");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

            ServiceDAO serviceDAO = new ServiceDAO();
            ShopDAO shopDAO = new ShopDAO();
            ShopServiceDetailDAO serviceDetailDAO = new ShopServiceDetailDAO();
            //get ID from shopping.jsp
            String id = request.getParameter("ID");
            //get the service's information based on id
            ServiceDTO service = serviceDAO.getByID(id);
            //check if any shop has that service
            List<ShopServiceDetailDTO> serviceDetail = serviceDetailDAO.getSupportedShop(service.getServiceID());
            //store that service on a map
            Map<String, ShopDTO> map = shopDAO.getShopInfo(serviceDetail);

            if (map != null) {
                request.setAttribute("SERVICE", service);
                request.setAttribute("MAP_SUPPORTED_SHOP", map);
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at InsertFormController: " + e.toString());
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
