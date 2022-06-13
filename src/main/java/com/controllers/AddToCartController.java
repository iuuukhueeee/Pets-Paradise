package com.controllers;

import com.checkout.Cart;
import com.checkout.Item;
import com.DAO.ProductDAO;
import com.DAO.ServiceDAO;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@WebServlet(name = "AddToCartController", value = "/AddToCartController")
public class AddToCartController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "shopping.jsp";
    private static final String INSERT_FORM = "InfoInputController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            //HANDLE SAVE IMAGE
            Part filePart = request.getPart("picture");
            InputStream fileContent = filePart.getInputStream();
            String hashedFileName = Integer.toString(Paths.get(filePart.getSubmittedFileName()).hashCode());
            ServletContext context = request.getServletContext();
            String savePath = context.getRealPath("/") + "img\\upload\\";
            String relativePath = "./img/upload/";
            File targetFile = new File(savePath + hashedFileName + ".png");
            FileUtils.copyInputStreamToFile(fileContent, targetFile);
            request.setAttribute("IMG_URL", relativePath + hashedFileName + ".png");


            Item item = new Item();
            String ID = request.getParameter("ID");
            item.setItemID(ID);
            String[] itemTypeID = ID.split("-");
            ProductDAO product = new ProductDAO();
            ServiceDAO service = new ServiceDAO();
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if(cart == null){
                cart = new Cart();
            }
            if(itemTypeID[0].equals("PRODUCT")){
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                item.setProduct(product.getByID(item.getItemID()));
                item.getProduct().setQuantity(quantity);
                cart.addProduct(item);
            }
            else if(itemTypeID[0].equals("SERVICE")){
                url = INSERT_FORM;
                request.getRequestDispatcher(url).include(request, response);
                item.setService(service.getByID(item.getItemID()));
                cart.addService(item);
            }
            session.setAttribute("CART",cart);
            request.setAttribute("MESSAGE","Added Successfully");
            url = SUCCESS;

        } catch (Exception e) {
            log("Error at AddToCartController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
