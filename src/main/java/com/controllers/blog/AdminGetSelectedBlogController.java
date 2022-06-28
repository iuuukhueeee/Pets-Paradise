package com.controllers.blog;

import com.DAO.BlogDAO;
import com.DTO.BlogDTO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminGetSelectedBlogController", value = "/AdminGetSelectedBlogController")
public class AdminGetSelectedBlogController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            BlogDAO blogDAO = new BlogDAO();
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            String id = request.getParameter("ID");
            BlogDTO blog = blogDAO.loadByID(id);
            Gson gson = new Gson();
            JsonObject json = new JsonObject();
            JsonElement convert = gson.toJsonTree(blog);
            json.add("BLOG", convert);
            out.println(json);
            out.close();
        } catch (Exception e) {
            log("Error at AdminGetSelectedBlogController: " + e);
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
