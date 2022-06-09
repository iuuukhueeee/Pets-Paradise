package com.controllers;

import com.DAO.UserDAO;
import com.DTO.GoogleDTO;
import com.utils.GoogleUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GoogleAuthController", value = "/GoogleAuthController")
public class GoogleAuthController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "index.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse respnse) throws ServletException, IOException {
         String url = ERROR;

         try {
            UserDAO userDAO = new UserDAO();
            String urlParameter = "code="
                + "&client_id=782043511219-gshc0resddhuqreh8ne9ppjbdinqndnj.apps.googleusercontent.com"
                + "&client_secret=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"
                + "&redirect_uri=http://localhost:8080/Pets_Paradise_war_exploded/GoogleAuthController"
                + "&grant_type=authorization_code";

            GoogleDTO user = GoogleUtils.getInfo(urlParameter);

         } catch (Exception e) {
            log("Error at GoogleAuthController: " + e.toString());
         } finally {
             request.getRequestDispatcher(url).forward(request, respnse);
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
