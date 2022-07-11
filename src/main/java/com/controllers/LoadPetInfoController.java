package com.controllers;



import com.DAO.PetDAO;
import com.DTO.PetDTO;
import com.DTO.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadPetInfoController", value = "/LoadPetInfoController")
public class LoadPetInfoController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "userPetInfo.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
            List<PetDTO> petInfo;
            PetDAO petDAO = new PetDAO();
            petInfo = petDAO.getPetInfo(user.getUsername());
            if (petInfo != null) {
                request.setAttribute("PET_INFO", petInfo);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at LoadPetInfoController: " + e.toString());
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
