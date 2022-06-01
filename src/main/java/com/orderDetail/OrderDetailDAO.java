package com.orderDetail;


import com.utils.DButils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {

    private static final String CREATE= "INSERT INTO OrderDetail(OrderDetailID, OrderDetailPrice, OrderID, ServiceID, ProductID, BookingTime) VALUES(?,?,?,?,?,?)";
    private static final String VIEW_BOOKING_TIME= "SELECT BookingTime FROM OrderDetail";



    public List<OrderDetailDTO> viewBookingTime() throws SQLException{
        List<OrderDetailDTO> ProductList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(VIEW_BOOKING_TIME);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    Date BookingTime = rs.getDate("BookingTime");
                    ProductList.add(new OrderDetailDTO("",0,"","","",BookingTime));
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
        return ProductList;
    }



    public boolean CreateOrderDetail(OrderDetailDTO orderDetail) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try{
            conn = DButils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1,orderDetail.getOrderDetailID());
                ptm.setFloat(2,orderDetail.getOrderDetailPrice());
                ptm.setString(3,orderDetail.getOrderID());
                ptm.setString(4,orderDetail.getServiceID());
                ptm.setString(5,orderDetail.getProductID());
                ptm.setDate(6, (Date) orderDetail.getBookingTime());
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
