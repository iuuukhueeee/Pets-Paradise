package com.controllers.blog;

import com.DAO.BlogDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteBlogController", value = "/DeleteBlogController")
public class DeleteBlogController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "AdminBlog";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String url = ERROR;

        try {
            BlogDAO blogDAO = new BlogDAO();
            String ID = request.getParameter("ID");
            if (blogDAO.deleteBlog(ID)) {
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at DeleteBlogController: " + e);
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
