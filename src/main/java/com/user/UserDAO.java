package com.user;

import com.utils.DButils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String CREATE = "INSERT INTO Users(Username, Name, Password, Email, PhoneNumber, RoleID, Status) VALUES(?,?,?,?,?,?,1)";
    private static final String UPDATE = "UPDATE Users SET  Username=?, Password=?, Email=?, PhoneNumber=? where Name=?";
    private static final String DELETE = "UPDATE Users SET Status=0 WHERE Name=?";


    public boolean DeleteUser(String Name) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try{
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, Name);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e){
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


    public boolean UpdateUser(UserDTO users) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try{
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1,users.getUsername());
                ptm.setString(2,users.getName());
                ptm.setString(3,users.getPassword());
                ptm.setString(4,users.getEmail());
                ptm.setString(5,users.getPhoneNumber());
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ptm != null){
                ptm.close();
            }
            if (conn != null){
                ptm.close();
            }
        }
        return  check;
    }

    public boolean CreateUser(UserDTO users) throws SQLException {
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


    public boolean login(String username, String password) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {

            conn = DButils.getConnection();
            System.out.println(conn != null);
        } catch (Exception e) {

        }
        return true;
    }
}
