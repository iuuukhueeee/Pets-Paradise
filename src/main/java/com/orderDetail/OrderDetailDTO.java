package com.orderDetail;

import java.util.Date;

public class OrderDetailDTO {

    private String OrderDetailID;
    private float OrderDetailPrice;
    private String OrderID;
    private String ServiceID;

    private String ProductID;
    private Date BookingTime;

    public OrderDetailDTO(){
        this.OrderDetailID = "";
        this.OrderDetailPrice = 0;
        this.OrderID = "";
        this.ServiceID = "";
        this.ProductID= "";
        this.BookingTime = null;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public OrderDetailDTO(String orderDetailID, float orderDetailPrice, String orderID, String serviceID, String productID, Date bookingTime){
        this.OrderDetailID = orderDetailID;
        this.OrderDetailPrice = orderDetailPrice;
        this.OrderID = orderID;
        this.ServiceID = serviceID;
        this.ProductID = productID;
        this.BookingTime = bookingTime;
    }

    public String getOrderDetailID() {
        return OrderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        OrderDetailID = orderDetailID;
    }

    public float getOrderDetailPrice() {
        return OrderDetailPrice;
    }

    public void setOrderDetailPrice(float orderDetailPrice) {
        OrderDetailPrice = orderDetailPrice;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getServiceID() {
        return ServiceID;
    }

    public void setServiceID(String serviceID) {
        ServiceID = serviceID;
    }

    public Date getBookingTime() {
        return BookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        BookingTime = bookingTime;
    }
}
