package com.DAO;


import com.DTO.PetDTO;
import com.utils.DButils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class PetDAO {
    private static final String CREATE= "INSERT INTO Pet(PetID, AnimalID, OrderDetailID, AnimalName, AnimalAge, AnimalPicture, AnimalDescription, BookingTime) VALUES(?,?,?,?,?,?,?,?)";


    public boolean addPetInfo(PetDTO pet) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        String petID = "";
        java.util.Date getDate = pet.getBookingTime();
        Date bookingTime = null;
        try{
            UUID uuid = UUID.randomUUID();
            conn = DButils.getConnection();
            if (conn != null) {
                petID = "PET"+"-"+uuid.toString();
                bookingTime = new Date(getDate.getTime());
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1,petID);
                ptm.setString(2,pet.getAnimalID());
                ptm.setString(3,pet.getOrderDetailID());
                ptm.setString(4,pet.getAnimalName());
                ptm.setInt(5,pet.getAnimalAge());
                ptm.setString(6,pet.getAnimalPicture());
                ptm.setString(7,pet.getAnimalDescription());
                ptm.setDate(8,bookingTime);
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
