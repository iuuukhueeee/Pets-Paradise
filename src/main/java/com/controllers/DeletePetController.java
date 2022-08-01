package com.controllers;

import com.DAO.PetDAO;
import com.DAO.ProductDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeletePetController", value = "/DeletePetController")
public class DeletePetController extends HttpServlet {
    private static final String SUCCESS = "user.jsp";
    private static final String ERROR = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String petID = request.getParameter("petID");
            PetDAO dao = new PetDAO();
            boolean check = dao.deletePetInfo(petID);
            if (check) {
                url = SUCCESS;
            }
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
