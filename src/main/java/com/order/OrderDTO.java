package com.order;

import java.util.Date;

public class OrderDTO {
    private String orderID;
    private Date orderDate;
    private String username;
    private String feedBackOrder;

    public OrderDTO(){
        this.orderID = "";
        this.orderDate = null;
        this.username = "";
        this.feedBackOrder = "";
    }

    public OrderDTO(String OrderID, Date OrderDate, String username, String feedBackOrder){
        this.orderID = OrderID;
        this.orderDate = OrderDate;
        this.username = username;
        this.feedBackOrder = feedBackOrder;
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
}
