package com.DAO;


import com.DTO.AnimalDTO;
import com.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalDAO {
    private static final String GET_ID = "SELECT AnimalID FROM Animal WHERE AnimalName = ?";

    public AnimalDTO getID(String animalName) throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ptm = null;
        AnimalDTO animal = null;
        try{
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ID);
                ptm.setString(1,animalName);
                rs = ptm.executeQuery();
                if(rs.next()) {
                    String animalID = rs.getString("AnimalID");
                    animal = new AnimalDTO(animalID,animalName);
                }
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
        return  animal;
    }
}
