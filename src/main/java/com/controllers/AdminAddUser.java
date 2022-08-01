package com.controllers;

import com.DAO.ServiceDAO;
import com.DAO.UserDAO;
import com.DTO.ServiceDTO;
import com.DTO.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminAddUser", value = "/AdminAddUser")
public class AdminAddUser extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "adminAddUser.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            String method = request.getParameter("method");
            if (method == null) method = "create";
            if ("create".equals(method)) {
                url = SUCCESS;
            }
            else if ("update".equals(method)) {
                String username = request.getParameter("userName");
                UserDAO userDAO = new UserDAO();
                UserDTO user = userDAO.getByUsername(username);
                request.setAttribute("USER", user);
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
