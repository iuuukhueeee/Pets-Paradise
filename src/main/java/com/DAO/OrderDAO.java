package com.DAO;

import com.DTO.OrderDTO;
import com.DTO.UserDTO;
import com.utils.DButils;
import com.utils.DateUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDAO {
    private static final String CREATE= "INSERT INTO Orders(OrderID, OrderDate, Username, Status) VALUES(?,?,?,1)";
    private static final String UPDATE= "UPDATE Orders SET OrderDate=?, Username=?, FeedbackOrder=? WHERE OrderID=?";
    private static final String DELETE= "UPDATE Orders SET Status=0 WHERE OrderID=?";
    private static final String GET_ORDERS = "SELECT OrderID, OrderDate, Username, FeedbackOrder FROM Orders WHERE status = 1 ";


    public boolean deleteOrder(String OrderID) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try{
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, OrderID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e){
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

    public boolean updateOrder(OrderDTO order) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try{
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1,order.getOrderID());
                ptm.setDate(2, (Date) order.getOrderDate());
                ptm.setString(3,order.getUsername());
                ptm.setString(4,order.getFeedBackOrder());
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


    public OrderDTO createOrder(String username) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        String orderID;
        OrderDTO order = null;
        try{
            Date orderDate = new Date(DateUtils.now());
            UUID uuid = UUID.randomUUID();
            conn = DButils.getConnection();
            if (conn != null) {
                orderID = "ORDER"+"-"+ uuid;
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1,orderID);
                ptm.setDate(2,orderDate);
                ptm.setString(3,username);
                boolean check = ptm.executeUpdate() > 0;
                order = new OrderDTO(orderID,orderDate,username,"");
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
        return order;
    }

    public List<OrderDTO> getAll() throws SQLException {
        List<OrderDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDERS);
                rs = ptm.executeQuery();

                while (rs.next()) {
                    String orderID = rs.getString("OrderID");
                    Date orderDate = rs.getDate("OrderDate");
                    String username = rs.getString("Username");
                    String feedbackOrder = rs.getString("FeedbackOrder");
                    OrderDTO order = new OrderDTO(orderID,orderDate,username,feedbackOrder);
                    list.add(order);
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

}
