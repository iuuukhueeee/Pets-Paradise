package com.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import com.DAO.UserDAO;
import com.DTO.UserDTO;


@WebServlet(name = "SearchUserController", value = "/SearchUserController")
public class SearchUserController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "admin_user.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String search = request.getParameter("SearchUser");
            if (search == null) search = "";
            UserDAO dao = new UserDAO();
            List<UserDTO> listUser = dao.getListUser(search);
            if (listUser.size() > 0) {
                request.setAttribute("LIST_USER", listUser);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at SearchUserController: " + e.toString());
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
