package com.controllers;

import com.DAO.UserDAO;
import com.DTO.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadUserController", value = "/LoadUserController")
public class AdminLoadUserController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "adminUser.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            List<UserDTO> listUser;
            UserDAO userDAO = new UserDAO();
            listUser = userDAO.getAll();
            if (listUser != null) {
                request.setAttribute("USER_LIST", listUser);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at LoadUserController: " + e.toString());
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
