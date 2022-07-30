package com.DTO;

import java.util.Date;

public class OrderDTO {
    private String orderID;
    private Date orderDate;
    private String username;
    private String feedBackOrder;
    private float total;

    public OrderDTO(){
        this.orderID = "";
        this.orderDate = null;
        this.username = "";
        this.feedBackOrder = "";
        this.total = 0;
    }

    public OrderDTO(String OrderID, Date OrderDate, String username, String feedBackOrder, float total){
        this.orderID = OrderID;
        this.orderDate = OrderDate;
        this.username = username;
        this.feedBackOrder = feedBackOrder;
        this.total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFeedBackOrder() {
        return feedBackOrder;
    }

    public void setFeedBackOrder(String feedBackOrder) {
        this.feedBackOrder = feedBackOrder;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return orderID;
    }
}
