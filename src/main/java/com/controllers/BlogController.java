package com.controllers;

import com.blog.BlogDAO;
import com.blog.BlogDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BlogController", value = "/BlogController")
public class BlogController extends HttpServlet {

    //KHOA: USE ONE TYPE OF CATCH

    private static final String ERROR = "blog.jsp";
    private static final String SUCCESS = "blog.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            BlogDAO blogDAO = new BlogDAO();
            List<BlogDTO> listBlog;
            listBlog = blogDAO.loadListBlog();

            if (listBlog.size() > 0) {
                request.setAttribute("LIST_BLOG", listBlog);
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
