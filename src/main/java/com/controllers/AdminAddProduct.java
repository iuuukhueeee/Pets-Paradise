package com.controllers;

import com.DAO.ProductDAO;
import com.DTO.ProductDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminAddProduct", value = "/AdminAddProduct")
public class AdminAddProduct extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "adminAddProduct.jsp";

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
                ProductDAO productDAO = new ProductDAO();
                ProductDTO product = productDAO.getByID(id);
                request.setAttribute("PRODUCT", product);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at AdminAddProduct: " + e);
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
