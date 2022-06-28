package com.DTO;

public class CartDTO {
    private String username;
    private String itemID;
    private boolean onCart;
    private int quantity;

    public CartDTO(){
        this.username = "";
        this.itemID = "";
        this.onCart = false;
        this.quantity = 0;
    }



    public CartDTO(String username, String itemID, boolean onCart, int quantity) {
        this.username = username;
        this.itemID = itemID;
        this.onCart = onCart;
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public boolean isOnCart() {
        return onCart;
    }

    public void setOnCart(boolean onCart) {
        this.onCart = onCart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
