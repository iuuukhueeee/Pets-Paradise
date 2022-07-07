package com.DAO;


import com.DTO.PetDTO;
import com.utils.DButils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class PetDAO {
    private static final String CREATE= "INSERT INTO Pet(PetID, Username, AnimalID, AnimalName, AnimalAge, AnimalPicture, AnimalDescription, Saved, Status) VALUES(?,?,?,?,?,?,?,?,1)";


    public boolean addPetInfo(PetDTO pet, boolean saved) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try{
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1,pet.getPetID());
                ptm.setString(2,pet.getUsername());
                ptm.setString(3,pet.getAnimalID());
                ptm.setString(4,pet.getAnimalName());
                ptm.setInt(5,pet.getAnimalAge());
                ptm.setString(6,pet.getAnimalPicture());
                ptm.setString(7,pet.getAnimalDescription());
                ptm.setBoolean(8, saved);
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
        return check;
    }

}
