package com.DAO;


import com.DTO.CartDTO;
import com.DTO.OrderDetailDTO;
import com.utils.DButils;

import java.sql.*;
import java.util.UUID;

public class OrderDetailDAO {

    private static final String CREATE= "INSERT INTO OrderDetail(OrderDetailID, OrderDetailPrice, OrderID, itemID, Quantity, Status) VALUES(?,?,?,?,?,1)";


    public OrderDetailDTO createOrderDetail(String orderID, CartDTO cart , float price) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        String orderDetailID = "";
        OrderDetailDTO orderDT = null;
        String itemTypeID = "";
        String[] getItem = cart.getItemID().split("-");
        try{
            conn = DButils.getConnection();
            if (conn != null) {
                UUID uuid = UUID.randomUUID();
                orderDetailID = "ORDERDETAIL" + "-" + uuid;
                itemTypeID = getItem[0];
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, orderDetailID );
                ptm.setFloat(2, price);
                ptm.setString(3,orderID);
                ptm.setString(4,cart.getItemID());
                if(itemTypeID.equals("PRODUCT")) {
                    ptm.setInt(5, cart.getQuantity());
                }
                ptm.setInt(5, 1);
                check = ptm.executeUpdate() > 0;
                if(check){
                    orderDT = new OrderDetailDTO(orderDetailID,0,orderID, cart.getItemID(), 0);
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
        return orderDT;
    }

}
