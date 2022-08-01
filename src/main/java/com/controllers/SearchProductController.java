package com.controllers;

import java.io.IOException;
import com.DAO.ProductDAO;
import com.DTO.ProductDTO;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.DTO.UserDTO;

@WebServlet(name = "SearchProductController", value = "/SearchProductController")
public class SearchProductController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS_US = "shopping.jsp";
    private static final String SUCCESS_AD = "adminProduct.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        try {
            String searchproduct = request.getParameter("SearchProduct");
            if(searchproduct == null) searchproduct = "";
            ProductDAO dao = new ProductDAO();

            List<ProductDTO> list = dao.getListProduct(searchproduct);
            request.setAttribute("search", searchproduct);
            request.setAttribute("LIST_PRODUCT", list);

            HttpSession session = request.getSession(false);
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null) {
                url = SUCCESS_US;
            } else {
                if (loginUser.getRoleID().equals("US")) {
                    url = SUCCESS_US;
                } else if (loginUser.getRoleID().equals("AD")) {
                    url = SUCCESS_AD;
                }
            }


        } catch (Exception e) {
            log("Error at SearchProductController: " + e.toString());
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
