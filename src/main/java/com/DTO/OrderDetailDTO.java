package com.DTO;

import java.sql.Date;

public class OrderDetailDTO {

    private String orderDetailID;
    private float orderDetailPrice;
    private String orderID;
    private String itemID;
    private int quantity;
    private String petID;
    private Date bookingTime;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String orderDetailID, float orderDetailPrice, String orderID, String itemID, int quantity, String petID, Date bookingTime) {
        this.orderDetailID = orderDetailID;
        this.orderDetailPrice = orderDetailPrice;
        this.orderID = orderID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.petID = petID;
        this.bookingTime = bookingTime;
    }

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public float getOrderDetailPrice() {
        return orderDetailPrice;
    }

    public void setOrderDetailPrice(float orderDetailPrice) {
        this.orderDetailPrice = orderDetailPrice;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }
}
