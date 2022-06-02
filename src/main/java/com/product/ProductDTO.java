package com.product;

import java.util.Date;

public class ProductDTO {
    private String ProductID;
    private String Name;
    private int Quantity;
    private String Image;
    private float Price;
    private Date ImportDate;
    private Date ExpireDate;

    public ProductDTO(){
        this.ProductID = "";
        this.Name = "";
        this.Quantity = 0;
        this.Image = "";
        this.Price = 0;
        this.ImportDate = null;
        this.ExpireDate = null;
    }

    public ProductDTO(String productID, String name, int quantity, String image, float price, Date importDate, Date expireDate){
        this.ProductID = productID;
        this.Name = name;
        this.Quantity = quantity;
        this.Image = image;
        this.Price = price;
        this.ImportDate = importDate;
        this.ExpireDate = expireDate;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public Date getImportDate() {
        return ImportDate;
    }

    public void setImportDate(Date importDate) {
        ImportDate = importDate;
    }

    public Date getExpireDate() {
        return ExpireDate;
    }

    public void setExpireDate(Date expireDate) {
        ExpireDate = expireDate;
    }

}
