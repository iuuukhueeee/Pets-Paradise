package com.controllers;

import com.DAO.ServiceDAO;
import com.DTO.ServiceDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.ws.Service;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexPageController", value = "/IndexPageController")
public class IndexPageController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "index.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        try {
            ServiceDAO serviceDAO = new ServiceDAO();
            List<ServiceDTO> list = serviceDAO.getIndex();
            if (list.size() > 0) {
                request.setAttribute("SERVICES", list);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at IndexPageController: " + e);
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
