package com.controllers.blog;

import com.DAO.BlogDAO;
import com.DTO.BlogDTO;
import com.DTO.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BlogController", value = "/BlogController")
public class BlogController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS_US = "blog.jsp";
    private static final String SUCCESS_AD = "adminBlog.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
            BlogDAO blogDAO = new BlogDAO();
            List<BlogDTO> listBlog;
            listBlog = blogDAO.loadAll();

            if (listBlog.size() > 0) {
                request.setAttribute("LIST_BLOG", listBlog);
                if (user != null) {
                    if ("AD".equals(user.getRoleID()))
                        url = SUCCESS_AD;
                    else url = SUCCESS_US;
                }
                else if (user == null)
                    url = SUCCESS_US;
            }
        } catch (Exception e) {
            log("Error at BlogController:" + e.toString());
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
