package com.controllers;

import com.DAO.ServiceDAO;
import com.DTO.ServiceDTO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoadServiceController", value = "/LoadServiceController")
public class LoadServiceController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            List<ServiceDTO> listService = new ArrayList<>();
            ServiceDAO serviceDAO = new ServiceDAO();
            listService = serviceDAO.getAll();
            if (listService != null) {
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
