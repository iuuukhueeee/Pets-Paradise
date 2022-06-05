package com.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "MainController", value = "/MainController")
public class MainController extends HttpServlet {


    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String SIGNUP = "Signup";
    private static final String SIGNUP_CONTROLLER = "SignupController";


    //            ============= SERVICE ==============
    private static final String ADD_SERVICE = "AddService";
    private static final String ADD_SERVICE_CONTROLLER = "AddServiceController";
    private static final String SEARCH_SERVICE = "SearchService";
    private static final String SEARCH_SERVICE_CONTROLLER = "SearchServiceController";
    private static final String DELETE_SERVICE = "DeleteService";
    private static final String DELETE_SERVICE_CONTROLLER = "DeleteServiceController";
    private static final String UPDATE_SERVICE = "UpdateService";
    private static final String UPDATE_SERVICE_CONTROLLER = "UpdateServiceController";


    //           ============= USER ==============
    private static final String SEARCH_USER = "SearchUser";
    private static final String SEARCH_USER_CONTROLLER = "SearchUserController";
    private static final String DELETE_USER = "DeleteUser";
    private static final String DELETE_USER_CONTROLLER = "DeleteUserController";
    private static final String UPDATE_USER = "UpdateUser";
    private static final String UPDATE_USER_CONTROLLER = "UpdateUserController";

    //         ============= PRODUCT ==============
    private static final String ADD_PRODUCT = "AddProduct";
    private static final String ADD_PRODUCT_CONTROLLER = "AddProductController";
    private static final String SEARCH_PRODUCT = "SearchProduct";
    private static final String SEARCH_PRODUCT_CONTROLLER = "SearchProductController";
    private static final String DELETE_PRODUCT = "DeleteProduct";
    private static final String DELETE_PRODUCT_CONTROLLER = "DeleteProductController";
    private static final String UPDATE_PRODUCT = "UpdateProduct";
    private static final String UPDATE_PRODUCT_CONTROLLER = "UpdateProductController";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");

            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;

            } else if (SEARCH_SERVICE.equals(action)) {
                url = SEARCH_SERVICE_CONTROLLER;

            } else if (SEARCH_USER.equals(action)) {
                url = SEARCH_USER_CONTROLLER;

            } else if (SEARCH_PRODUCT.equals(action)) {
                url = SEARCH_PRODUCT_CONTROLLER;

            } else if (DELETE_USER.equals(action)) {
                url = DELETE_USER_CONTROLLER;

            } else if (DELETE_SERVICE.equals(action)) {
                url = DELETE_SERVICE_CONTROLLER;

            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;

            }else if (ADD_SERVICE.equals(action)) {
                url = ADD_SERVICE_CONTROLLER;

            }else if (SIGNUP.equals(action)) {
                url = SIGNUP_CONTROLLER;

            }else if (ADD_PRODUCT.equals(action)) {
                url = ADD_PRODUCT_CONTROLLER;

            }else if (UPDATE_PRODUCT.equals(action)) {
                url = UPDATE_PRODUCT_CONTROLLER;

            }else if (UPDATE_USER.equals(action)) {
                url = UPDATE_USER_CONTROLLER;

            }else if (UPDATE_SERVICE.equals(action)) {
                url = UPDATE_SERVICE_CONTROLLER;

            }else if (DELETE_PRODUCT.equals(action)) {
                url = DELETE_PRODUCT_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
