package com.controllers;


import com.DAO.AnimalDAO;
import com.DTO.AnimalDTO;
import com.DTO.PetDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "InfoInputController", value = "/InfoInputController")
public class InfoInputController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String serviceID = request.getParameter("ID");
            String animalType = request.getParameter("animalType");
            String animalName = request.getParameter("animalName");
            String animalAge = request.getParameter("animalAge");
            String animalDescription = request.getParameter("animalDescription");
            String getTime = request.getParameter("bookingTime");
            String animalPicture = (String) request.getAttribute("IMG_URL");
            Date bookingTime = formatter.parse(getTime);
            Map<String,PetDTO> petInfo = new HashMap<>();
            AnimalDAO animalDAO = new AnimalDAO();
            AnimalDTO animal = null;
            animal = animalDAO.getID(animalType);
            PetDTO pet = new PetDTO("", animal.getAnimalID(),"",animalName,Integer.parseInt(animalAge),animalPicture,animalDescription,bookingTime);
            petInfo.put(serviceID,pet);
            HttpSession session = request.getSession();
            session.setAttribute("PET_INFO",petInfo);
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
