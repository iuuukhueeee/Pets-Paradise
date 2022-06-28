package com.controllers;

import com.DAO.UserDAO;
import com.utils.EmailUtils;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "ResetPasswordController", value = "/ResetPasswordController")
public class ResetPasswordController extends HttpServlet {

    private static final String SUCCESS = "login.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        try {

            String username = request.getParameter("username");
            UserDAO userDAO = new UserDAO();
            if (!userDAO.checkDuplicate(username)) {
                request.setAttribute("ERROR", "Username does not exist!");
                response.sendRedirect("forgotPassword.jsp");
            }
            String email = userDAO.getEmail(username);
            UUID uuid = UUID.randomUUID();
            String newPassword = org.apache.commons.codec.digest.DigestUtils.sha256Hex(uuid.toString()).substring(0, 15);
            if(EmailUtils.sendResetPassword(newPassword, email)) {
                userDAO.updatePassword(newPassword, username);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at ResetPasswordController: " + e.toString());
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
