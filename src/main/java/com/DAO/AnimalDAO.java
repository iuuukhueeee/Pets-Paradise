package com.DAO;

import com.DTO.AnimalDTO;
import com.utils.DButils;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AnimalDAO {
    private static final String GET_ID = "SELECT AnimalID FROM Animal WHERE AnimalType = ?";
    private static final String GET_ALL = "SELECT AnimalID, AnimalType FROM Animal WHERE Status=1";

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

    public Map<String, AnimalDTO> getAllType() throws SQLException {
        Map<String, AnimalDTO> map = new HashMap<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("AnimalID");
                    String type = rs.getString("AnimalType");
                    if (!map.containsKey(id)) {
                        map.put(id, new AnimalDTO(id, type));
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return map;
    }
}
