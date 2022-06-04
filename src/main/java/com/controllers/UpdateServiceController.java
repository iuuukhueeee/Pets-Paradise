package com.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import com.service.*;

@WebServlet(name = "UpdateServiceController", value = "/UpdateServiceController")
public class UpdateServiceController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "add_service.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String ServiceID = request.getParameter("ServiceID");
            String ServiceName = request.getParameter("ServiceName");
            float ServicePrice = Float.parseFloat(request.getParameter("ServicePrice"));
            String ServiceDescription = request.getParameter("ServiceDescription");
            ServiceDAO dao = new ServiceDAO();
            ServiceDTO service = new ServiceDTO(ServiceID, ServiceName, ServicePrice, ServiceDescription);
            boolean checkUpdate = dao.update(service);
            if (checkUpdate) {
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at UpdateController: " + e.toString());
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
