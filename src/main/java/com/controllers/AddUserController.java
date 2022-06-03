package com.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.user.*;

@WebServlet(name = "AddUserController", value = "/AddUserController")
public class AddUserController extends HttpServlet {


    private static final String ERROR = "signup.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        UserError userError = new UserError();
        try {
            String userName = request.getParameter("userName");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            UserDAO dao = new UserDAO();

            Pattern pattern = Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b");
            Matcher matcher = pattern.matcher(email);
            boolean matchFound = matcher.find();

            boolean validation = true;
            boolean checkDuplicate = dao.checkDuplicate(userName);
            if (checkDuplicate) {
                userError.setUserNameError("Duplicate User Name");
                validation = false;
            }

            if (userName.length() < 5 || userName.length() > 10) {
                userError.setUserNameError("userID must be in [5, 10]");
                validation = false;
            }

            if (name.length() < 3 || name.length() > 20) {
                userError.setNameError("Name must be in [3, 20]");
                validation = false;
            }

            if (matchFound) {
                userError.setEmailError("Invalid Email");
                validation = false;
            }

            if (phoneNumber.length() < 9 || phoneNumber.length() > 13) {
                userError.setPhoneNumberError("Phone number must be in [9, 13]");
                validation = false;
            }


            if (validation) {
                UserDTO user = new UserDTO(userName, name, password, email, phoneNumber, "US");
                boolean checkCreate = dao.create(user);
                if (checkCreate) url = SUCCESS;
            } else {
                request.setAttribute("USER_ERROR", userError);
            }

        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());
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
