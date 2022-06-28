package com.DAO;


import com.DTO.AnimalDTO;
import com.utils.DButils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalDAO {
    private static final String GET_ID = "SELECT AnimalID FROM Animal WHERE AnimalType = ?";

    public AnimalDTO getID(String animalType) throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ptm = null;
        AnimalDTO animal = null;
        try{
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ID);
                ptm.setString(1,animalType);
                rs = ptm.executeQuery();
                if(rs.next()) {
                    String animalID = rs.getString("AnimalID");
                    animal = new AnimalDTO(animalID,animalType);
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
