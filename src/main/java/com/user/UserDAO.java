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
    private static final String SEARCH = "SELECT Username, Name, Password, Email, PhoneNumber, RoleID FROM Users WHERE Name LIKE ? AND Status=1";
    private static final String CREATE = "INSERT INTO Users(Username, Name, Password, Email, PhoneNumber, RoleID, Status) VALUES(?, ?, ?, ?, ?, 'US', 1)";
    private static final String DELETE = "UPDATE Users SET Status=0 WHERE Username=?";
    private static final String UPDATE = "UPDATE Users SET Name = ?, Password = ? , Email = ? , PhoneNumber = ? WHERE Username = ?";


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
                    String Name = rs.getString("Name");
                    String Email = rs.getString("Email");
                    String PhoneNumber = rs.getString("PhoneNumber");
                    String RoleID = rs.getString("RoleID");
                    user = new UserDTO(username, Name, "", Email, PhoneNumber, RoleID);
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
                    String Username = rs.getString("Username");
                    String Name = rs.getString("Name");
                    String PhoneNumber = rs.getString("PhoneNumber");
                    String Email = rs.getString("Email");
                    String RoleID = rs.getString("RoleID");
                    String Password = "***";
                    list.add(new UserDTO(Username, Name, Password, Email, PhoneNumber, RoleID));
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

    public boolean create(UserDTO user) throws SQLException {
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
                check = ptm.executeUpdate() > 0 ? true : false;

            }
        } catch (Exception e) {
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

    public static boolean delete(String userName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, userName);
                check = ptm.executeUpdate() > 0 ? true : false;
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

    public boolean update(UserDTO user) throws SQLException {
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
