package com.controllers;


import com.animal.AnimalDAO;
import com.animal.AnimalDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InfoInputController", value = "/InfoInputController")
public class InfoInputController extends HttpServlet {

    private static final String SUCCESS = "CartService.jsp";

    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String serviceID = request.getParameter("ID");
            String animalType = request.getParameter("animalType");
            String animalName = request.getParameter("animalName");
            String animalAge = request.getParameter("animalAge");
            String animalDescription = request.getParameter("animalDescription");
            String bookingTime = request.getParameter("bookingTime");
            List<String> petInfo = new ArrayList<>();
            AnimalDAO animalDAO = new AnimalDAO();
            AnimalDTO animal = animalDAO.getID(animalType);
            petInfo.add(animal.getAnimalID());
            petInfo.add(animalName);
            petInfo.add(animalAge);
            petInfo.add(animalDescription);
            petInfo.add(bookingTime);
            petInfo.add(serviceID);
            HttpSession session = request.getSession();
            session.setAttribute("PET_INFO",petInfo);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at InfoInputController: " + e.toString());
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
