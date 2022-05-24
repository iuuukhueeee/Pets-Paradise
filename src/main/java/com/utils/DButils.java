package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButils {
    public static Connection getConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://ls-da48bd3255ad419d48ef40d6994820e37166b000.c10oakswwmpf.ap-southeast-1.rds.amazonaws.com:3306/PetParadise";
        conn = DriverManager.getConnection(url, "dbmasteruser", "#JB|aJd01VyB1P`DXWn#t7Wf*4]|,Q6O");
        return conn;
    }
}
