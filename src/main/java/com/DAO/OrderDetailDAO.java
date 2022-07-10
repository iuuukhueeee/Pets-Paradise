package com.DAO;


import com.DTO.OrderDTO;
import com.DTO.OrderDetailDTO;
import com.DTO.ProductDTO;
import com.DTO.ServiceDTO;
import com.utils.DButils;
import com.utils.ValidUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDetailDAO {

    private static final String CREATE_PRODUCT = "INSERT INTO OrderDetail(OrderDetailID, OrderDetailPrice, OrderID, ItemID, Quantity, Status) VALUES(?,?,?,?,?,2)";
    private static final String CREATE_SERVICE = "INSERT INTO OrderDetail(OrderDetailID, OrderDetailPrice, OrderID, ItemID, Quantity, PetID, BookingTime, Status) VALUES(?,?,?,?,?,?,?,2)";
    private static final String GET_BY_ORDERID = "SELECT OrderDetailID, OrderDetailPrice, ItemID, Quantity, PetID, BookingTime FROM OrderDetail WHERE OrderID=? AND Status=1";
    private static final String CHECK_DUPLICATE = "SELECT COUNT(*) AS NUM FROM OrderDetail WHERE OrderID=? AND ItemID=? AND Status=2";
    private static final String UPDATE_QUANTITY = "UPDATE OrderDetail SET Quantity=Quantity + ? WHERE ItemID=? AND OrderID=? AND Status=2";
    private static final String CHECKOUT = "UPDATE OrderDetail SET Status=1 WHERE OrderID=?";
    private static final String REMOVE_ITEM = "UPDATE OrderDetail SET Status=0 WHERE ItemID=? AND OrderID=?";

    public boolean createOrderDetail(OrderDTO order, ProductDTO product, int quantity) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        String orderDetailID;
        try{
            conn = DButils.getConnection();
            if (conn != null) {
                UUID uuid = UUID.randomUUID();
                orderDetailID = "ORDERDETAIL-" + uuid;
                ptm = conn.prepareStatement(CREATE_PRODUCT);
                ptm.setString(1, orderDetailID);
                ptm.setFloat(2, product.getPrice());
                ptm.setString(3,order.getOrderID());
                ptm.setString(4,product.getProductID());
                ptm.setInt(5, quantity);
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


    public boolean createOrderDetail(OrderDTO order, ServiceDTO service, int quantity, String bookingTime, String petID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        String orderDetailID;
        try{
            conn = DButils.getConnection();
            if (conn != null) {
                UUID uuid = UUID.randomUUID();
                orderDetailID = "ORDERDETAIL-" + uuid;
                ptm = conn.prepareStatement(CREATE_SERVICE);
                ptm.setString(1, orderDetailID);
                ptm.setFloat(2, service.getServicePrice());
                ptm.setString(3,order.getOrderID());
                ptm.setString(4,service.getServiceID());
                ptm.setInt(5, quantity);
                ptm.setString(6, petID);
                ptm.setDate(7, ValidUtils.isValidDate(bookingTime));
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

    public List<OrderDetailDTO> getByOrderID(String orderID) throws SQLException {
        List<OrderDetailDTO> orderDetail = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BY_ORDERID);
                ptm.setString(1, orderID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderDetailID = rs.getString("OrderDetailID");
                    float orderDetailPrice = rs.getFloat("OrderDetailPrice");
                    String itemID = rs.getString("ItemID");
                    int quantity = rs.getInt("Quantity");
                    if ("SERVICE".equals(itemID.split("-")[0])) {
                        String petID = rs.getString("PetID");
                        Date bookingTime = rs.getDate("BookingTime");
                        orderDetail.add(new OrderDetailDTO(orderDetailID, orderDetailPrice, orderID, itemID, quantity, petID, bookingTime));
                    } else {
                        orderDetail.add(new OrderDetailDTO(orderDetailID, orderDetailPrice, orderID, itemID, quantity, "", null));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }

        return orderDetail;
    }

    public boolean checkItemDuplicate(String itemID, String orderID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, orderID);
                ptm.setString(2, itemID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int num = Integer.parseInt(rs.getString("NUM"));
                    if (num > 0) check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public boolean addMoreQuantity(String itemID, String orderID, int quantity) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_QUANTITY);
                ptm.setInt(1, quantity);
                ptm.setString(2, itemID);
                ptm.setString(3, orderID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }

    public void checkout(String orderID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECKOUT);
                ptm.setString(1, orderID);
                ptm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
    }

    public boolean removeItem(String ID, String orderID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(REMOVE_ITEM);
                ptm.setString(1, ID);
                ptm.setString(2, orderID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) ptm.close();
            if (conn != null) conn.close();
        }
        return check;
    }
}
