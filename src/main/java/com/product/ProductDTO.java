package com.product;

import java.sql.Date;

public class ProductDTO {
    private String productID;
    private String categoryID;
    private String name;
    private int quantity;
    private String image;
    private float price;
    private Date importDate;
    private Date expiredDate;

    public ProductDTO(String productID, String categoryID, String name, int quantity, String image, float price, Date importDate, Date expiredDate) {
        this.productID = productID;
        this.categoryID = categoryID;
        this.name = name;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
        this.importDate = importDate;
        this.expiredDate = expiredDate;
    }

    public ProductDTO() {
        this.productID = "";
        this.categoryID = "";
        this.name = "";
        this.quantity = 0;
        this.image = "";
        this.price = 0;
        this.importDate = null;
        this.expiredDate = null;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
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

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
}
