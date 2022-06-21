package com.DAO;


import com.checkout.Item;
import com.DTO.OrderDetailDTO;
import com.utils.DButils;

import java.sql.*;
import java.util.UUID;

public class OrderDetailDAO {

    private static final String CREATE= "INSERT INTO OrderDetail(OrderDetailID, OrderDetailPrice, OrderID, itemID, Quantity, Status) VALUES(?,?,?,?,?,1)";


    public OrderDetailDTO createOrderDetail(String orderID, Item item) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        String orderDetailID = "";
        OrderDetailDTO orderDT = null;
        String itemTypeID = "";
        String[] getItem = item.getItemID().split("-");
        try{
            conn = DButils.getConnection();
            if (conn != null) {
                UUID uuid = UUID.randomUUID();
                orderDetailID = "ORDERDETAIL" + "-" + uuid.toString();
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, orderDetailID );
                itemTypeID = getItem[0];
                if(itemTypeID.equals("PRODUCT")){
                    ptm.setFloat(2,item.getProduct().getPrice());
                } else if (itemTypeID.equals("SERVICE")) {
                    ptm.setFloat(2,item.getService().getServicePrice());
                }
                ptm.setString(3,orderID);
                ptm.setString(4,item.getItemID());
                if(itemTypeID.equals("PRODUCT")) {
                    ptm.setInt(5, item.getProduct().getQuantity());
                }
                ptm.setInt(5, 1);
                check = ptm.executeUpdate() > 0;
                orderDT = new OrderDetailDTO(orderDetailID,0,orderID,itemTypeID,0);
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
