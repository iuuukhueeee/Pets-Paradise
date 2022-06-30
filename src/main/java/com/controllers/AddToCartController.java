package com.controllers;

import com.DAO.CartDAO;
import com.DTO.CartDTO;
import com.DTO.UserDTO;
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
    private static final String SUCCESS = "shopping";

    private static final String CHECK_LOGIN = "login.jsp";
    private static final String INSERT_PET_INFO = "InfoInputController";

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

            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if(user == null){
                url = CHECK_LOGIN;
                request.setAttribute("ERROR","Plea se Login to use this function!");
                request.getRequestDispatcher(url).forward(request, response);
            }
            CartDTO cart;
            CartDAO addCart = new CartDAO();

            String ID = request.getParameter("ID");
            String[] itemTypeID = ID.split("-");

            if(itemTypeID[0].equals("PRODUCT")){
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                cart = new CartDTO(user.getUsername(), ID, true ,quantity);
                if(addCart.addToCart(cart)){
                    request.setAttribute("MESSAGE","Added Successfully");
                }
            }
            else if(itemTypeID[0].equals("SERVICE")){
                    url = INSERT_PET_INFO;
                    request.getRequestDispatcher(url).include(request, response);
                    cart = new CartDTO(user.getUsername(), ID, true, 1);
                    if (addCart.addToCart(cart)) {
                        request.setAttribute("MESSAGE", "Added Successfully");
                }
            }
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at AddToCartController: " + e.toString());
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
