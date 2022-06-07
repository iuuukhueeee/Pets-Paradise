package com.product;

import java.util.Date;

public class ProductDTO {
    private String productID;
    private String name;
    private int quantity;
    private String image;
    private float price;
    private Date importDate;
    private Date expireDate;

    public ProductDTO(){
        this.productID = "";
        this.name = "";
        this.quantity = 0;
        this.image = "";
        this.price = 0;
        this.importDate = null;
        this.expireDate = null;
    }

    public ProductDTO(String productID, String name, int quantity, String image, float price, Date importDate, Date expireDate){
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
        this.importDate = importDate;
        this.expireDate = expireDate;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

}
