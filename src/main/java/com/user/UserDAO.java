package com.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.utils.DButils;

public class UserDAO {
    private static final String CREATE = "INSERT INTO Users(Username, Name, Password, Email, PhoneNumber, RoleID, Status) VALUES(?,?,?,?,?,?,1)";
    private static final String LOGIN = "SELECT Username, Name, Password, Email, PhoneNumber, RoleID FROM Users WHERE Username=? AND Password=? AND Status=1";
    private static final String CHECK_DUPLICATE = "SELECT Name FROM Users WHERE Username=?";

    public boolean createUser(UserDTO users) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, users.getUsername());
                ptm.setString(2, users.getName());
                ptm.setString(3, users.getPassword());
                ptm.setString(4, users.getEmail());
                ptm.setString(5, users.getPhoneNumber());
                ptm.setString(6, users.getRoleID());
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                ptm.close();
            }
        }
        return check;
    }

    public UserDTO checkLogin(String Username, String Password) throws SQLException {
        UserDTO user = null;

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, Username);
                ptm.setString(2, Password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("Username");
                    String name = rs.getString("Name");
                    String email = rs.getString("Email");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String roleID = rs.getString("RoleID");
                    user = new UserDTO(username, name, "", email, phoneNumber, roleID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (ptm != null) {
                ptm.close();
            }
        }
        return check;
    }


}
