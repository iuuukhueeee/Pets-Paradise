package com.controllers.blog;

import com.DAO.BlogDAO;
import com.DTO.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UploadBlogController", value = "/UploadBlogController")
public class UploadBlogController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "AdminBlog";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        try {
            String title = request.getParameter("title");
            String content = request.getParameter("message");
            String description = request.getParameter("description");
            UserDTO userDTO = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
            if (content != null) {
                BlogDAO blogDAO = new BlogDAO();
                if (blogDAO.uploadBlog(userDTO, title, content, description)) url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at UploadBlogController: " + e.toString());
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
