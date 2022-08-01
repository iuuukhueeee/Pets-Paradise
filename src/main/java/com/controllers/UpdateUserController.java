package com.controllers;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UserDAO;
import com.DTO.UserDTO;

@WebServlet(name = "UpdateUserController", value = "/UpdateUserController")
public class UpdateUserController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS_AD = "AdminUser";
    private static final String SUCCESS_US = "user.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userName = request.getParameter("username");
            String name = request.getParameter("userName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String roleID = request.getParameter("roleID");

            UserDAO dao = new UserDAO();
            UserDTO user = new UserDTO(userName, name, password, email, phoneNumber, Objects.equals(roleID, "AD") ? "AD" : "US");
            boolean checkUpdate = dao.updateUser(user);
            if (checkUpdate) {
                HttpSession session = request.getSession();
                UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");

                if (loginUser.getRoleID().equals("US")) {
                    session.setAttribute("LOGIN_USER", user);
                    url = SUCCESS_US;
                } else if (loginUser.getRoleID().equals("AD")) {
                    url = SUCCESS_AD;
                }
            }

        } catch (Exception e) {
            log("Error at UpdateUserController: " + e.toString());
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
