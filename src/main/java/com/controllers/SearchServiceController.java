package com.controllers;

import com.DAO.ServiceDAO;
import com.DTO.ServiceDTO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import com.DTO.UserDTO;

@WebServlet(name = "SearchServiceController", value = "/SearchServiceController")
public class SearchServiceController extends HttpServlet {

    private static final String ERROR = "AdminService";
    private static final String SUCCESS_US = "index.jsp";
    private static final String SUCCESS_AD = "AdminService";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        try {
            String searchService = request.getParameter("SearchService");
            if(searchService == null) searchService = "";
            ServiceDAO dao = new ServiceDAO();

            List<ServiceDTO> list = dao.searchService(searchService);

            request.setAttribute("SERVICE_LIST", list);
            request.getSession().setAttribute("ON_SEARCH", true);
            request.setAttribute("SEARCH_SERVICE", searchService);

            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");

            if (loginUser.getRoleID().equals("US")) {
                url = SUCCESS_US;
            } else if (loginUser.getRoleID().equals("AD")) {
                url = SUCCESS_AD;
            }
        } catch (Exception e) {
            log("Error at SearchServiceController: " + e.toString());
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
