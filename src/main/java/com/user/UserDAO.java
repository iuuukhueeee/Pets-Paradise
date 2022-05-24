package com.user;

import com.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

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
