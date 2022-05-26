package com.order;

import java.util.Date;

public class OrderDTO {
    private String OrderID;
    private Date OrderDate;
    private String Username;
    private String FeedbackOrder;

    public OrderDTO(){
        this.OrderID = "";
        this.OrderDate = null;
        this.Username = "";
        this.FeedbackOrder = "";
    }

    public OrderDTO(String OrderID, Date OrderDate, String Username, String FeedbackOrder){
        this.OrderID = OrderID;
        this.OrderDate = OrderDate;
        this.Username = Username;
        this.FeedbackOrder = FeedbackOrder;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFeedbackOrder() {
        return FeedbackOrder;
    }

    public void setFeedbackOrder(String feedbackOrder) {
        FeedbackOrder = feedbackOrder;
    }
}
