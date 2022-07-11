package com.controllers;

import com.DAO.PetDAO;
import com.DAO.ProductDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeletePetInfoController", value = "/DeletePetInfoController")
public class DeletePetInfoController extends HttpServlet {

    private static final String SUCCESS = "userPetInfo";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String animalName = request.getParameter("animalName");
            PetDAO dao = new PetDAO();
            boolean check = dao.deletePetInfo(animalName);
            if (check) {
                url = SUCCESS;
            }
        }catch(Exception e){
            log("Error at DeletePetInfoController" +toString());
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
