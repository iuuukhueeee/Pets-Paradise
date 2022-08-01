package com.controllers;

import com.DAO.ProductDAO;
import com.DAO.UserDAO;
import com.DTO.ProductDTO;
import com.DTO.UserDTO;
import com.Error.ProductError;
import com.utils.ValidUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "AddUserController", value = "/AddUserController")
public class AddUserController extends HttpServlet {
    private static final String ERROR = "AdminUser";
    private static final String SUCCESS = "adminAddUser.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductError productError = new ProductError();
        try {
            String userName = request.getParameter("username");
            String name = request.getParameter("userName");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            UserDAO dao = new UserDAO();
            boolean check = true;
            boolean check_dup = dao.checkDuplicate(userName);
            if(check_dup){
                productError.setProductIDError("User Already Exists!");
            }

            if (check) {
                UserDTO user = new UserDTO(userName,name,password,email,phoneNumber,"AD");
                boolean checkCreate = dao.createAdmin(user);
                if (checkCreate) {
                    request.setAttribute("MESSAGE", "successfully Create user.");
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at AddUserController :" + e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request , response);
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
