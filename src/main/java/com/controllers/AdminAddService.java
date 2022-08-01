package com.controllers;

import com.DAO.ServiceDAO;
import com.DTO.ServiceDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminAddService", value = "/AdminAddService")
public class AdminAddService extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "adminAddService.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            String method = request.getParameter("method");
            if (method == null) method = "create";
            if ("create".equals(method)) {
                url = SUCCESS;
            }
            else if ("update".equals(method)) {
                String id = request.getParameter("ID");
                ServiceDAO serviceDAO = new ServiceDAO();
                ServiceDTO service = serviceDAO.getByID(id);
                request.setAttribute("SERVICE", service);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at AdminAddService: " + e);
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
