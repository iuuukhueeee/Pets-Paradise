package com.DAO;


import com.DTO.PetDTO;
import com.DTO.ProductDTO;
import com.utils.DButils;
import com.utils.ValidUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    private static final String CREATE= "INSERT INTO Pet(PetID, Username, AnimalID, AnimalName, AnimalAge, AnimalPicture, AnimalDescription, Saved, Status) VALUES(?,?,?,?,?,?,?,?,1)";
    private static final String GET_PET_INFO = "SELECT PetID, AnimalID, AnimalName, AnimalAge, AnimalPicture, AnimalDescription FROM Pet WHERE Username=? AND Status=1 AND Saved=1";
    private static final String DELETE_PET_INFO = "UPDATE Pet SET Status = 0 , Saved = 0 WHERE PetID = ?";
    private static final String UPDATE_PET_INFO = "UPDATE Pet SET AnimalID = ? , AnimalName = ?, AnimalAge= ?, AnimalPicture = ?, AnimalDescription = ?  WHERE PetID = ?";
    private static final String GET_BY_ID = "SELECT PetID, AnimalID, AnimalName, AnimalAge, AnimalPicture, AnimalDescription FROM Pet WHERE PetID = ? AND Status=1 AND Saved=1";


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

    public List<PetDTO> getUserPetList(String username) throws SQLException {
        List<PetDTO> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PET_INFO);
                ptm.setString(1, username);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String petID = rs.getString("PetID");
                    String animalID = rs.getString("AnimalID");
                    String animalName = rs.getString("AnimalName");
                    int animalAge = rs.getInt("AnimalAge");
                    String animalPicture = rs.getString("AnimalPicture");
                    String animalDescription = rs.getString("AnimalDescription");
                    PetDTO pet = new PetDTO(petID,"",animalID,animalName,animalAge,animalPicture,animalDescription);
                    list.add(pet);
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

    public boolean deletePetInfo(String ID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_PET_INFO);
                ptm.setString(1, ID);
                check = ptm.executeUpdate() > 0;
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
        return check;
    }

    public boolean updatePetInfo(PetDTO pet) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PET_INFO);
                ptm.setString(1, pet.getAnimalID());
                ptm.setString(2, pet.getAnimalName());
                ptm.setInt(3, pet.getAnimalAge());
                ptm.setString(4, pet.getAnimalPicture());
                ptm.setString(5, pet.getAnimalDescription());
                ptm.setString(6, pet.getPetID());
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

    public PetDTO getByID(String ID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        PetDTO pet = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BY_ID);
                ptm.setString(1, ID);
                rs = ptm.executeQuery();

                if (rs.next()) {
                    String petID = rs.getString("PetID");
                    String animalID = rs.getString("AnimalID");
                    String animalName = rs.getString("AnimalName");
                    int animalAge = rs.getInt("AnimalAge");
                    String animalPicture = rs.getString("AnimalPicture");
                    String animalDescription = rs.getString("AnimalDescription");

                    pet = new PetDTO(petID,"",animalID,animalName,animalAge,animalPicture,animalDescription);
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
        return pet;
    }
}
