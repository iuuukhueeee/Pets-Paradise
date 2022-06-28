package com.controllers.blog;

import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

@MultipartConfig
@WebServlet(name = "UploadBlogImageController", value = "/UploadBlogImageController")
public class UploadBlogImageController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        InputStream fileContent = filePart.getInputStream();
        String hashedFileName = Integer.toString(Paths.get(filePart.getSubmittedFileName()).hashCode());
        ServletContext context = request.getServletContext();
        String savePath = context.getRealPath("/") + "img\\upload\\";
        String relativePath = "./img/upload/";
        File targetFile = new File(savePath + hashedFileName + ".png");
        FileUtils.copyInputStreamToFile(fileContent, targetFile);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("location", relativePath + hashedFileName + ".png");
        out.print(json);
        out.flush();
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
