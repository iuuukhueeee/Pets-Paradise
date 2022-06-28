package com.DAO;

import com.DTO.CartDTO;
import com.utils.DButils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    private static final String CREATE= "INSERT INTO Cart(Username, ItemID, OnCart, Quantity) VALUES(?,?,?,?)";
    private static final String DELETE_ITEM = "UPDATE Cart SET OnCart=0 WHERE Username = ? ";
    private static final String CHECK_DUPLICATE = "SELECT ItemID FROM Cart WHERE ItemID LIKE ? AND Username = ? AND OnCart = 1";
    private static final String GET_BY_USERNAME = "SELECT ItemID, Quantity, OnCart FROM Cart WHERE Username = ? AND OnCart = 1";
    private static final String GET_PRODUCT_PRICE = "SELECT Price FROM Product\n" +
            "INNER JOIN Cart\n" +
            "ON Product.ProductID = ?\n";
    private static final String GET_SERVICE_PRICE = "SELECT ServicePrice FROM Service\n" +
            "INNER JOIN Cart\n" +
            "ON Service.ServiceID = ?\n";


    public boolean checkDuplicate(String itemID , String username) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, itemID);
                ptm.setString(2, username);
                rs = ptm.executeQuery();
                if(rs.next()){
                    check = true;
                }
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

    public boolean updateCartStatus(String username) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_ITEM);
                ptm.setString(1, username);
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

    public String getPrice(String itemID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String toString = "";
        String getItemtype = itemID.split("-")[0];
        try{
            conn = DButils.getConnection();
            if (conn != null) {
                if(getItemtype.equals("PRODUCT")){
                    ptm = conn.prepareStatement(GET_PRODUCT_PRICE);
                }
                else if(getItemtype.equals("SERVICE")){
                    ptm = conn.prepareStatement(GET_SERVICE_PRICE);
                }
                ptm.setString(1,itemID);
                rs = ptm.executeQuery();
                if(rs.next()){
                    if(getItemtype.equals("PRODUCT")){
                        float price = rs.getFloat("Price");
                        toString = Float.toString(price);
                    }
                    else if(getItemtype.equals("SERVICE")){
                        float price = rs.getFloat("ServicePrice");
                        toString = Float.toString(price);
                    }
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
        return toString;
    }

    public List<CartDTO> getItemOnCartByUsername(String username) throws SQLException {
        List<CartDTO> list = new ArrayList<>();
        CartDTO cart = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BY_USERNAME);
                ptm.setString(1, username);
                rs = ptm.executeQuery();
                while(rs.next()) {
                    String itemID = rs.getString("ItemID");
                    int quantity = rs.getInt("Quantity");
                    boolean onCart = rs.getBoolean("OnCart");
                    cart = new CartDTO(username,itemID,onCart,quantity);
                    list.add(cart);
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
    public boolean addToCart(CartDTO cart) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try{
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1,cart.getUsername());
                ptm.setString(2,cart.getItemID());
                ptm.setBoolean(3,cart.isOnCart());
                ptm.setInt(4,cart.getQuantity());
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
