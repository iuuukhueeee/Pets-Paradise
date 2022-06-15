package com.DTO;

public class ShopDTO {
    private String shopID;
    private String shopName;
    private String shopLocation;

    public ShopDTO() {
        this.shopID = "";
        this.shopName = "";
        this.shopLocation = "";
    }

    public ShopDTO(String shopID, String shopName, String shopLocation) {
        this.shopID = shopID;
        this.shopName = shopName;
        this.shopLocation = shopLocation;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }
}
