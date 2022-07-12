package com.controllers;

import com.DAO.AnimalDAO;
import com.DAO.PetDAO;
import com.DTO.AnimalDTO;
import com.DTO.PetDTO;
import com.DTO.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LoadUserPetInfoController", value = "/LoadUserPetInfoController")
public class LoadUserPetInfoController extends HttpServlet {

    private static final String LOGIN = "login.jsp";
    private static final String SUCCESS = "userPetInfo.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            if (user == null) {
                request.getRequestDispatcher(LOGIN).forward(request, response);
            }
            PetDAO petDAO = new PetDAO();
            List<PetDTO> list = petDAO.getUserPetList(user.getUsername());
            AnimalDAO animalDAO = new AnimalDAO();
            Map<String, AnimalDTO> petType = animalDAO.getAllType();

            if (list.size() > 0 && list != null) {
                request.setAttribute("LIST_PET", list);
                request.setAttribute("ANIMAL_TYPE", petType);
            }

        } catch (Exception e) {
            log("Error at LoadUserPetInfoController: " +e);
        } finally {
            request.getRequestDispatcher(SUCCESS).forward(request, response);
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
