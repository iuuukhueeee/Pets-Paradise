package com.controllers;

import com.DAO.AnimalDAO;
import com.DAO.PetDAO;
import com.DTO.AnimalDTO;
import com.DTO.PetDTO;
import com.DTO.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "InfoInputController", value = "/InfoInputController")
public class InfoInputController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String animalType = request.getParameter("animalType");
            String animalName = request.getParameter("animalName");
            int animalAge = Integer.parseInt(request.getParameter("animalAge"));
            String animalDescription = request.getParameter("animalDescription");
            String animalPicture = (String) request.getAttribute("IMG_URL");

            HttpSession session = request.getSession();
            AnimalDAO animalDAO = new AnimalDAO();
            AnimalDTO animal = animalDAO.getID(animalType);


            UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");

            String saveInfo = request.getParameter("saveInfo");
            String petID = "PET-" + UUID.randomUUID().toString().substring(0, 10);


            PetDTO info = new PetDTO(petID, user.getUsername(), animal.getAnimalID(), animalName, animalAge, animalPicture, animalDescription);
            String ID = request.getParameter("ID");
            PetDAO petDAO = new PetDAO();
            if (saveInfo != null) {
                if (petDAO.addPetInfo(info, true)) {
                    System.out.println("Save pet's info successfully");
                }
            } else {
                if (petDAO.addPetInfo(info, false)) {
                    System.out.println("Add to cart successfully");
                }
            }

            Map<String, PetDTO> petInfo  = new HashMap<>();
            petInfo.put(ID, info);
            session.setAttribute("PET_INFO", petInfo);
            session.setAttribute("SHOP", request.getAttribute("shop"));

        } catch (Exception e) {
            log("Error at InfoInputController: " + e.toString());
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
