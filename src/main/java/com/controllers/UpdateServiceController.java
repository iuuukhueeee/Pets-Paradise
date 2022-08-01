package com.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import com.DAO.ServiceDAO;
import com.DTO.ServiceDTO;

@WebServlet(name = "UpdateServiceController", value = "/UpdateServiceController")
public class UpdateServiceController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "AdminService";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String search = request.getParameter("SearchService");
            String ServiceID = request.getParameter("ID");
            String ServiceName = request.getParameter("serviceName");
            float ServicePrice = Float.parseFloat(request.getParameter("servicePrice"));
            String ServiceDescription = request.getParameter("serviceDescription");
            ServiceDAO dao = new ServiceDAO();
            ServiceDTO service = new ServiceDTO(ServiceID, ServiceName, ServicePrice, ServiceDescription);
            boolean checkUpdate = dao.update(service);
            if (checkUpdate) {
                request.setAttribute("SEARCH_SERVICE", search);
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at UpdateServiceController: " + e.toString());
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
