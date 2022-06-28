package com.controllers.blog;

import com.DAO.BlogDAO;
import com.DTO.BlogDTO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

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
            List<BlogDTO> listBlog = blogDAO.loadListBlogTemplate();

            if (blog != null) {
                request.setAttribute("BLOG_CONTENT", blog);
                request.setAttribute("BLOG_TEMPLATE", listBlog);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at BlogContentController: " + e.toString());
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
