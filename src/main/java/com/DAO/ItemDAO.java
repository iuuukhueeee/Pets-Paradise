package com.DAO;

import com.checkout.Item;
import com.utils.DButils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDAO {

    private static final String CREATE= "INSERT INTO ItemType(ItemTypeID) VALUES(?,?)";


    public boolean createItem(Item item) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try{
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1,item.getItemID());
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

}
