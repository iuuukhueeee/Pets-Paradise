package com.controllers;

import com.DAO.*;
import com.DTO.*;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Map;

@WebServlet(name = "AddToCartController", value = "/AddToCartController")
public class AddToCartController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS_SERVICES = "services";
    private static final String SUCCESS_SHOPPING = "shopping";
    private static final String INSERT_PET_INFO = "InfoInputController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            OrderDAO orderDAO = new OrderDAO();
            OrderDTO order = null;

            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

            ProductDAO productDAO = new ProductDAO();

            ServiceDAO serviceDAO = new ServiceDAO();
            ServiceDTO service;
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null) {
                request.setAttribute("ERROR", "Please Login to use this function!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

            String ID = request.getParameter("ID");
            String[] itemTypeID = ID.split("-");

            order = orderDAO.getCartByUsername(user.getUsername());
            String shop = "";
            if (order == null) {
                shop = request.getParameter("shopLocation");
                if (shop == null) shop = "";
                orderDAO.createOrder(user.getUsername(), shop);
                order = orderDAO.getCartByUsername(user.getUsername());
            }

            if ("".equals(order.getShop())) {
                orderDAO.updateShop(shop, order.getOrderID());
            }


            if (itemTypeID[0].equals("PRODUCT")) {
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                if (orderDetailDAO.checkItemDuplicate(ID, order.getOrderID())) {
                    orderDetailDAO.addMoreQuantity(ID, order.getOrderID(), quantity);
                } else {
                    ProductDTO product = productDAO.getByID(ID);
                    if (orderDetailDAO.createOrderDetail(order, product, quantity)) {
                        request.setAttribute("MESSAGE", "Added Successfully");
                        url = SUCCESS_SHOPPING;
                    }
                }
            } else if (itemTypeID[0].equals("SERVICE")) {
                service = serviceDAO.getByID(ID);

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

                //HANDLE USER'S INPUT

                String bookingTime = request.getParameter("bookingTime");
                url = INSERT_PET_INFO;
                request.getRequestDispatcher(url).include(request, response);

                Map<String, PetDTO> petInfo = (Map<String, PetDTO>) session.getAttribute("PET_INFO");

                if (orderDetailDAO.createOrderDetail(order, service, 1, bookingTime, petInfo.get(ID).getPetID())) {
                    url = SUCCESS_SERVICES;
                    request.setAttribute("MESSAGE", "Added Successfully");
                }
            }
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
