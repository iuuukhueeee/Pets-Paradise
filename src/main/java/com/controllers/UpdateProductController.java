package com.controllers;


import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.product.*;
import com.utils.ValidUtils;


@WebServlet(name = "UpdateProductController", value = "/UpdateProductController")
public class UpdateProductController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "product.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("ProductID");
            String categoryID = request.getParameter("ProductCategoryID");
            String productName = request.getParameter("Name");
            int quantity = Integer.parseInt(request.getParameter("Quantity"));
            String image = request.getParameter("Image");
            float price = Float.parseFloat(request.getParameter("Price"));
            Date importDate = ValidUtils.isValidDate(request.getParameter("ImportDate"));
            Date expiredDate = ValidUtils.isValidDate(request.getParameter("ExpiredDate"));
            ProductDAO dao = new ProductDAO();
            ProductDTO product = new ProductDTO(productID, categoryID, productName, quantity, image, price, importDate, expiredDate);
            boolean checkUpdate = dao.update(product);
            if (checkUpdate) {
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at UpdateController: " + e.toString());
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
