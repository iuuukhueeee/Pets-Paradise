package com.orderDetail;

import java.util.Date;

public class OrderDetailDTO {

    private String OrderDetailID;
    private float OrderDetailPrice;
    private String OrderID;
    private String itemID;
    private int quantity;

    public OrderDetailDTO(){
        this.OrderDetailID = "";
        this.OrderDetailPrice = 0;
        this.OrderID = "";
        this.itemID = "";
        this.quantity = 0;
    }



    public OrderDetailDTO(String orderDetailID, float orderDetailPrice, String orderID, String itemID,int quantity){
        this.OrderDetailID = orderDetailID;
        this.OrderDetailPrice = orderDetailPrice;
        this.OrderID = orderID;
        this.itemID = itemID;
        this.quantity = quantity;

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
}
