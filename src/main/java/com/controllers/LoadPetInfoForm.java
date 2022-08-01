package com.controllers;

import com.DAO.PetDAO;
import com.DTO.PetDTO;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoadPetInfoForm", value = "/LoadPetInfoForm")
public class LoadPetInfoForm extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "updatePetForm.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = ERROR;

        try {
            String method = request.getParameter("method");

            if ("update".equals(method)) {
                String petID = request.getParameter("ID");
                PetDAO petDAO = new PetDAO();
                PetDTO pet = petDAO.getByID(petID);
                request.setAttribute("PET", pet);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at AdminAddProduct: " + e);
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
