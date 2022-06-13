package com.controllers;

import com.utils.EmailUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ResetPasswordController", value = "/ResetPasswordController")
public class ResetPasswordController extends HttpServlet {

    private static final String SUCCESS = "login.jsp";
    private static final String ERROR = "error.jsp";
    private String host;
    private String port;
    private String email;
    private String name;
    private String pass;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        try {
            String recipient = "phuongmtse161187@fpt.edu.vn";
            String subject = "RESET";
            String content = "sup bro";
            ServletContext context = getServletContext();
            host = context.getInitParameter("host");
            port = context.getInitParameter("port");
            email = context.getInitParameter("email");
            name = context.getInitParameter("name");
            pass = context.getInitParameter("pass");

//            EmailUtils.sendMail(host, port, email, name, pass, recipient, subject, content);
            EmailUtils.sendMail();
            url = SUCCESS;
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
