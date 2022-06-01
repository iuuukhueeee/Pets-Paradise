package com.controllers;

import com.blog.BlogDAO;
import com.blog.BlogDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BlogContentController", value = "/BlogContentController")
public class BlogContentController extends HttpServlet {

    private static final String ERROR = "blogID.jsp";
    private static final String SUCCESS = "blogID.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;
        BlogDTO blog = null;

        try {
            String blogID = request.getParameter("blogID");
            BlogDAO blogDAO = new BlogDAO();
            blog = blogDAO.loadByID(blogID);

            if (blog != null) {
                request.setAttribute("BLOG_CONTENT", blog);
                url = SUCCESS;
            }


        } catch (Exception e) {
            e.printStackTrace();
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
