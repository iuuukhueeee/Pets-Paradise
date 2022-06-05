package com.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.user.*;

@WebServlet(name = "SignupController", value = "/SignupController")
public class SignupController extends HttpServlet {

    private static final String ERROR = "signup.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        UserError userError = new UserError();
        try {
            String username = request.getParameter("username");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            UserDAO dao = new UserDAO();

            Pattern pattern = Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b");
            Matcher matcher = pattern.matcher(email);
            boolean matchFound = matcher.find();

            boolean validation = true;
            boolean checkDuplicate = dao.checkDuplicate(username);
            if (checkDuplicate) {
                userError.setUserNameError("Duplicate User Name");
                validation = false;
            }

            if (username.length() < 4 || username.length() > 20) {
                userError.setUserNameError("User Name must be in [4, 20]");
                validation = false;
            }

            if (firstName.length() < 3 || firstName.length() > 20) {
                userError.setNameError("Name must be in [3, 20]");
                validation = false;
            }

            if (lastName.length() < 3 || lastName.length() > 20) {
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
                UserDTO user = new UserDTO(username, firstName, lastName, password, email, phoneNumber, "US");
                boolean checkCreate = dao.createUser(user);
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
