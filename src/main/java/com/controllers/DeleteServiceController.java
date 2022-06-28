package com.controllers;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import com.DAO.ServiceDAO;

@WebServlet(name = "DeleteServiceController", value = "/DeleteServiceController")
public class DeleteServiceController extends HttpServlet {

    private static final String SUCCESS = "SearchServiceController";
    private static final String ERROR = "SearchServiceController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String serviceID = request.getParameter("serviceID");
            ServiceDAO dao = new ServiceDAO();
            boolean check = dao.delete(serviceID);
            if (check) {
                url = SUCCESS;
            }
        }catch(Exception e){
            log("Error at DeleteServiceController" +toString());
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
