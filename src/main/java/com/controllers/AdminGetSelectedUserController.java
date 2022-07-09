package com.controllers;

import com.DAO.UserDAO;
import com.DTO.UserDTO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminGetSelectedUserController", value = "/AdminGetSelectedUserController")
public class AdminGetSelectedUserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            UserDAO userDAO = new UserDAO();
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            String id = request.getParameter("ID");
            UserDTO user = userDAO.getByUsername(id);
            Gson gson = new Gson();
            JsonObject json = new JsonObject();
            JsonElement convert = gson.toJsonTree(user);
            json.add("USER", convert);
            out.println(json);
            out.close();
        } catch (Exception e) {
            log("Error at AdminGetSelectedUserController: " + e);
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
