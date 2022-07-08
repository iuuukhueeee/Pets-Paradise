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
public class AdminLoadProductController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "adminProduct.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            List<ProductDTO> listProduct;
            ProductDAO productDAO = new ProductDAO();
            listProduct = productDAO.getAll();
            if (listProduct != null) {
                request.setAttribute("PRODUCT_LIST", listProduct);
                url = SUCCESS;
            }
//            if (request.getRequestURI().equals("/shopping")) {
//                List<BlogDTO> listBlog;
//                BlogDAO blogDAO = new BlogDAO();
//                listBlog = blogDAO.loadListBlogTemplate();
//                request.setAttribute("LIST_BLOG", listBlog);
//                url = SUCCESS;
//            }
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
