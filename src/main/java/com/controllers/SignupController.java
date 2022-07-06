package com.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.DAO.UserDAO;
import com.DTO.UserDTO;
import com.Error.UserError;

@WebServlet(name = "SignupController", value = "/SignupController")
public class SignupController extends HttpServlet {

    private static final String ERROR = "register.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        UserError userError = new UserError();
        try {
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String confirmed = request.getParameter("confirmed");
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

            if (name.length() < 2 || name.length() > 20) {
                userError.setNameError("Name must be in [2, 20]");
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

            if (!confirmed.equals(password)) {
                userError.setPasswordError("Password does not match!");
                validation = false;
            }


            if (validation) {
                UserDTO user = new UserDTO(username, name, password, email, phoneNumber, "US");
                boolean checkCreate = dao.createUser(user);
                if (checkCreate) url = SUCCESS;
            } else {
                request.setAttribute("USER_ERROR", userError);
            }

        } catch (Exception e) {
            log("Error at SignupController: " + e.toString());
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
