package com.controllers;

import com.DAO.ServiceDAO;
import com.DTO.ServiceDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadServiceController", value = "/LoadServiceController")
public class LoadServiceController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "services.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }

            List<ServiceDTO> listService;
            ServiceDAO serviceDAO = new ServiceDAO();
            listService = serviceDAO.getServicePerPage(page);
            int size = serviceDAO.getSize();
            if (listService != null) {
                request.setAttribute("SIZE", size % 3);
                request.setAttribute("LIST_SERVICE", listService);
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
