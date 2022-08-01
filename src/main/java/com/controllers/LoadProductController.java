package com.controllers;

import com.DAO.BlogDAO;
import com.DAO.ProductDAO;
import com.DTO.BlogDTO;
import com.DTO.ProductDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadProductController", value = "/LoadProductController")
public class LoadProductController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            String page = request.getParameter("page");
            if (page == null) {
                page = "1";
            }

            List<ProductDTO> listProduct;
            ProductDAO productDAO = new ProductDAO();
            listProduct = productDAO.getProductPerPage(page);
            int size = productDAO.getSize();
            if (listProduct != null) {
                request.setAttribute("SIZE", size / 4);
                request.setAttribute("LIST_PRODUCT", listProduct);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at LoadProductController: " + e.toString());
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
