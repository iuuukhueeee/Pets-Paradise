package com.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import com.DAO.ProductDAO;

@WebServlet(name = "DeleteProductController", value = "/DeleteProductController")
public class DeleteProductController extends HttpServlet {

    private static final String SUCCESS = "SearchProductController";
    private static final String ERROR = "SearchProductController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            ProductDAO dao = new ProductDAO();
            boolean check = dao.delete(productID);
            if (check) {
                url = SUCCESS;
            }
        }catch(Exception e){
            log("Error at DeleteProductController" +toString());
        }finally{
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
