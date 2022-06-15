package com.DTO;

public class OrderDetailDTO {

    private String orderDetailID;
    private float orderDetailPrice;
    private String orderID;
    private String itemID;
    private int quantity;

    public OrderDetailDTO(){
        this.orderDetailID = "";
        this.orderDetailPrice = 0;
        this.orderID = "";
        this.itemID = "";
        this.quantity = 0;
    }



    public OrderDetailDTO(String orderDetailID, float orderDetailPrice, String orderID, String itemID,int quantity){
        this.orderDetailID = orderDetailID;
        this.orderDetailPrice = orderDetailPrice;
        this.orderID = orderID;
        this.itemID = itemID;
        this.quantity = quantity;

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
}
