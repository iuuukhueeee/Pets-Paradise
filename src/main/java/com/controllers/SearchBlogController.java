package com.controllers;

import com.DAO.BlogDAO;
import com.DTO.BlogDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchBlogController", value = "/SearchBlogController")
public class SearchBlogController extends HttpServlet {

    private static final String ERROR = "adminBlog.jsp";
    private static final String SUCCESS = "adminBlog.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            String search = request.getParameter("search");
            BlogDAO blogDAO = new BlogDAO();
            List<BlogDTO> list = blogDAO.search(search);
            request.setAttribute("LIST_BLOG", list);
            url = SUCCESS;

        } catch (Exception e) {
            log("Error at SearchBlogController: " + e);
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
