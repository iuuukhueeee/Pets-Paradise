package com.controllers;

import com.DAO.AnimalDAO;
import com.DAO.PetDAO;
import com.DTO.AnimalDTO;
import com.DTO.PetDTO;
import org.apache.commons.io.FileUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@WebServlet(name = "UpdatePetInfoController", value = "/UpdatePetInfoController")
public class UpdatePetInfoController extends HttpServlet {

    private static final String SUCCESS = "UserPetInfo";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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

            //HANDLE INPUT
            String animalPicture = relativePath + hashedFileName + ".png";
            String animalType = request.getParameter("animalType");
            String animalName = request.getParameter("animalName");
            int animalAge = Integer.parseInt(request.getParameter("animalAge"));
            String animalDescription = request.getParameter("animalDescription");
            String petID = request.getParameter("petID");
            String username = request.getParameter("username");
            AnimalDAO animalDAO = new AnimalDAO();
            AnimalDTO animal = animalDAO.getID(animalType);

            PetDAO petDAO =  new PetDAO();
            PetDTO petInfo = new PetDTO(petID,username,animal.getAnimalID(),animalName,animalAge,animalPicture,animalDescription);
            boolean updatedPet = petDAO.updatePetInfo(petInfo);
            if(updatedPet) url = SUCCESS;

        }catch(Exception e){
            log("Error at DeletePetController" +toString());
        }finally{
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
