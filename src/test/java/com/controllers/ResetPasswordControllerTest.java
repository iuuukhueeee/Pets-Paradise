package com.controllers;

import com.DAO.UserDAO;
import com.DTO.UserDTO;
import com.utils.EmailUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ResetPasswordControllerTest extends Mockito {
    private ResetPasswordController resetPasswordController = new ResetPasswordController();
    private UserDAO userDAO;


    @Test
    void shouldBeDuplicate() throws SQLException {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        RequestDispatcher rd = mock(RequestDispatcher.class);
//        when(userDAO.checkDuplicate(Mockito.any(String.class))).thenReturn(true);
//        when(request.getParameter("username")).thenReturn("user");
//        when(request.getRequestDispatcher("login.jsp")).thenReturn(rd);
//        resetPasswordController.processRequest(request, response);
        userDAO = mock(UserDAO.class);
        when(userDAO.checkDuplicate("user")).thenReturn(true);
        assertEquals(true, userDAO.checkDuplicate("user"));
        verify(userDAO, times(1)).checkDuplicate("user");
    }

    @Test
    void shouldntBeDuplicate() throws SQLException {
        userDAO = mock(UserDAO.class);
        when(userDAO.checkDuplicate("userr")).thenReturn(false);
        assertEquals(false, userDAO.checkDuplicate("user"));
        verify(userDAO, times(1)).checkDuplicate("user");
    }

    @Test
    void sendKnownEmail() throws SQLException {
        userDAO = mock(UserDAO.class);
        UserDTO user = userDAO.getByUsername("user");
        MockedStatic mocked = mockStatic(EmailUtils.class);
        mocked.when(() -> EmailUtils.sendResetPassword("123", user.getEmail())).thenReturn(true);
        assertEquals(true, EmailUtils.sendResetPassword("123", user.getEmail()));
        mocked.verify(() -> EmailUtils.sendResetPassword("123", user.getEmail()));
    }


}