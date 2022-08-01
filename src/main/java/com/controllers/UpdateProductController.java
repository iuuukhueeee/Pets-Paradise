package com.controllers;


import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.DAO.ProductDAO;
import com.DTO.ProductDTO;
import com.utils.ValidUtils;


@WebServlet(name = "UpdateProductController", value = "/UpdateProductController")
public class UpdateProductController extends HttpServlet {

    private static final String ERROR = "AdminProduct";
    private static final String SUCCESS = "AdminProduct";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            String categoryID = request.getParameter("category");
            String productName = request.getParameter("productName");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String image = request.getParameter("productImage");
            float price = Float.parseFloat(request.getParameter("price"));
            Date importDate = ValidUtils.isValidDate(request.getParameter("importDate"));
            Date expiredDate = ValidUtils.isValidDate(request.getParameter("expiredDate"));
            ProductDAO dao = new ProductDAO();
            ProductDTO product = new ProductDTO(productID, categoryID, productName, quantity, image, price, importDate, expiredDate);
            boolean checkUpdate = dao.updateProduct(product);
            if (checkUpdate) {
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at UpdateProductController: " + e.toString());
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
