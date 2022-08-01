package com.controllers.blog;

import com.DAO.BlogDAO;
import com.DTO.UserDTO;
import com.Error.AdminBlogError;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateBlogController", value = "/CreateBlogController")
public class CreateBlogController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "adminAddBlog.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        try {
            boolean validation = true;
            AdminBlogError error = new AdminBlogError();
            String title = request.getParameter("title");
            String content = request.getParameter("message");
            String description = request.getParameter("description");
            UserDTO userDTO = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
            if ("".equals(title)) {
                error.setTitleError("Title can't be blank");
                validation = false;
            }
            if ("".equals(content)) {
                error.setContentError("Content can't be blank");
                validation = false;
            }
            if ("".equals(description)) {
                error.setDescriptionError("Description can't be blank");
                validation = false;
            }

            BlogDAO blogDAO = new BlogDAO();
            if (validation) {
                if (blogDAO.createBlog(userDTO, title, content, description)) url = SUCCESS;
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
