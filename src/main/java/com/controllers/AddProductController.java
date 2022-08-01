package com.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import com.DAO.ProductDAO;
import com.DTO.ProductDTO;
import com.Error.ProductError;
import java.sql.Date;
import com.utils.*;

@WebServlet(name = "AddProductController", value = "/AddProductController")
public class AddProductController extends HttpServlet {

    private static final String ERROR = "AdminProduct";
    private static final String SUCCESS = "AdminProduct";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductError productError = new ProductError();
        try {

//            KHOA: hotfix -- productID will be auto regenerated

            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            String productImage = request.getParameter("productImage");
            Float price = Float.parseFloat(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String category = request.getParameter("productCategoryID");
            Date importDate = ValidUtils.isValidDate(request.getParameter("importDate"));
            Date expiredDate = ValidUtils.isValidDate(request.getParameter("expiredDate"));
            ProductDAO dao = new ProductDAO();
            boolean check = true;
            boolean check_dup = dao.checkDuplicate(productID);
            if(check_dup){
                productError.setProductIDError("Duplicate Product ID");
            }
            if (productID.length() < 2 || productID.length() > 10) {
                productError.setProductIDError("userID must be in [5, 10]");
                check = false;
            }

            if (productName.length() < 3 || productName.length() > 50) {
                productError.setNameError("Name must be in [3, 50]");
                check = false;
            }
            if (check) {
                ProductDTO product = new ProductDTO(productID, category, productName, quantity, productImage, price, importDate,
                        expiredDate);
                boolean checkCreate = dao.create(product);
                if (checkCreate) {
                    request.setAttribute("MESSAGE", "Inserted successfully.");
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("PRODUCT_ERROR", productError);
            }

        } catch (Exception e) {
            log("Error at AddProductController :" + e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request , response);
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
