package com.controllers.blog;

import com.DAO.BlogDAO;
import com.DTO.BlogDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminAddBlog", value = "/AdminAddBlog")
public class AdminAddBlog extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "adminAddBlog.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            String method = request.getParameter("method");
            if (method == null) method = "create";
            if ("create".equals(method)) {
                url = SUCCESS;
            }
            else if ("update".equals(method)) {
                String id = request.getParameter("ID");
                BlogDAO blogDAO = new BlogDAO();
                BlogDTO blog = blogDAO.loadByID(id);
                request.setAttribute("BLOG", blog);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at AdminAddBlog: " + e);
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
