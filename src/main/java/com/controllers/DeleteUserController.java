package com.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import com.DAO.UserDAO;
import com.DTO.UserDTO;

@WebServlet(name = "DeleteUserController", value = "/DeleteUserController")
public class DeleteUserController extends HttpServlet {

    private static final String SUCCESS = "SearchUserController";
    private static final String ERROR = "SearchUserController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String username = request.getParameter("ID");
            UserDAO dao = new UserDAO();
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null || username.equals(user.getUsername())) {
                request.setAttribute("ERROR", "User is currently login");
            } else {
                boolean check = dao.deleteUser(username);
                if (check) {
                    url = SUCCESS;
                }
            }
        }catch(Exception e){
            log("Error at DeleteUserController" +toString());
        }finally{
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
