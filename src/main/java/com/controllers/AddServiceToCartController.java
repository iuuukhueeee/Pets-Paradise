package com.controllers;

import org.apache.commons.io.FileUtils;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@MultipartConfig
@WebServlet(name = "AddServiceToCartController", value = "/AddServiceToCartController")
public class AddServiceToCartController extends HttpServlet {

    private static final String SUCCESS = "formConfirm.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            Part filePart = request.getPart("picture");
            InputStream fileContent = filePart.getInputStream();
            String hashedFileName = Integer.toString(Paths.get(filePart.getSubmittedFileName()).hashCode());
            ServletContext context = request.getServletContext();
            String savePath = context.getRealPath("/") + "img\\upload\\";
            String relativePath = "./img/upload/";
            File targetFile = new File(savePath + hashedFileName + ".png");
            FileUtils.copyInputStreamToFile(fileContent, targetFile);

            request.setAttribute("IMG_URL", relativePath + hashedFileName + ".png");
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at AddServiceToCartController: " + e.toString());
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
