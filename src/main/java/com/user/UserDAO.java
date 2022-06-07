package com.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utils.DButils;

public class UserDAO {
    private static final String LOGIN = "SELECT Username, Name, Password, Email, PhoneNumber, RoleID FROM Users WHERE Username=? AND Password=? AND Status=1";
    private static final String CHECK_DUPLICATE = "SELECT Name FROM Users WHERE Username=?";
    private static final String SEARCH = "SELECT Username, Name, Password, Email, PhoneNumber, RoleID FROM Users WHERE Name LIKE ? AND Status=1";
    private static final String CREATE = "INSERT INTO Users(Username, Name, Password, Email, PhoneNumber, RoleID, Status) VALUES(?, ?, ?, ?, ?, 'US', 1)";
    private static final String DELETE = "UPDATE Users SET Status=0 WHERE Username=?";
    private static final String UPDATE = "UPDATE Users SET Name = ? , Password = ? , Email = ? , PhoneNumber = ? WHERE Username = ?";


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


    public List<UserDTO> getListUser(String search) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("Username");
                    String name = rs.getString("Name");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String email = rs.getString("Email");
                    String roleID = rs.getString("RoleID");
                    list.add(new UserDTO(username, name, "", email, phoneNumber, roleID));
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
        return list;
    }

    public boolean createUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, user.getUsername());
                ptm.setString(2, user.getName());
                ptm.setString(3, user.getPassword());
                ptm.setString(4, user.getEmail());
                ptm.setString(5, user.getPhoneNumber());
                check = ptm.executeUpdate() > 0;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;

    }

    public static boolean deleteUser(String userName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, userName);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getName());
                ptm.setString(2, user.getPassword());
                ptm.setString(3, user.getEmail());
                ptm.setString(4, user.getPhoneNumber());
                ptm.setString(5, user.getUsername());
                check = ptm.executeUpdate() > 0;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;

    }

}
