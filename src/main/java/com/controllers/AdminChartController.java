package com.controllers;

import com.DAO.OrderDAO;
import com.DTO.IncomeDTO;
import com.DTO.OrderDTO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdminChartController", value = "/AdminChartController")
public class AdminChartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        try {
//            OrderDAO orderDAO = new OrderDAO();
//            PrintWriter out = response.getWriter();
//            response.setContentType("application/json");
//            List<IncomeDTO> list = orderDAO.getTotalIncomeAMonth();
//            Gson gson = new Gson();
//            JsonObject json = new JsonObject();
//            JsonElement convert = gson.toJsonTree(list);
//            json.add("total_data", convert);
//            out.println(json);
//            System.out.println(json);
//            out.close();
//        } catch (Exception e) {
//            log("Error at AdminGetSelectedBlogController: " + e);
//        }

        try {
            OrderDAO orderDAO = new OrderDAO();
            Map<String, IncomeDTO> map = orderDAO.getTotalIncomeAMonth();
            request.setAttribute("CHART", map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher("admin.jsp").forward(request, response);
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
