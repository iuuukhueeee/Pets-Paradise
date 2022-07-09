package com.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.DAO.ServiceDAO;
import com.DTO.ServiceDTO;
import com.Error.ServiceError;

@WebServlet(name = "AddServiceController", value = {"/AddServiceController"})
public class AddServiceController extends HttpServlet {

    private static final String ERROR = "AdminService";
    private static final String SUCCESS = "AdminService";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        ServiceError serviceError = new ServiceError();
        try {
            String serviceName = request.getParameter("serviceName");
            Float servicePrice = Float.parseFloat(request.getParameter("servicePrice"));
            String serviceDescription = request.getParameter("serviceDescription");
            ServiceDAO dao = new ServiceDAO();
            String serviceID = dao.createID();
            boolean check = true;

            if (serviceName.length() < 3 || serviceName.length() > 50) {
                serviceError.setServiceNameError("Name must be in [3, 50]");
                check = false;
            }
            if (check) {
                ServiceDTO service = new ServiceDTO(serviceID, serviceName, servicePrice, serviceDescription);
                boolean checkCreate = dao.create(service);
                if (checkCreate) {
                    request.setAttribute("MESSAGE", "Inserted successfully.");
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("SERVICE_ERROR", serviceError);
            }

        } catch (Exception e) {
            log("Error at AddServiceController :" + e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request , response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
