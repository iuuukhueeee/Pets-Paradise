package com.DTO;

public class ShopServiceDetailDTO {
    private String shopServiceDetailID;
    private String shopID;
    private String serviceID;
    private float adjustPrice;

    public ShopServiceDetailDTO() {
        this.shopServiceDetailID = "";
        this.shopID = "";
        this.serviceID = "";
        this.adjustPrice = 0;
    }

    public ShopServiceDetailDTO(String shopServiceDetailID, String shopID, String serviceID, float adjustPrice) {
        this.shopServiceDetailID = shopServiceDetailID;
        this.shopID = shopID;
        this.serviceID = serviceID;
        this.adjustPrice = adjustPrice;
    }

    public String getShopServiceDetailID() {
        return shopServiceDetailID;
    }

    public void setShopServiceDetailID(String shopServiceDetailID) {
        this.shopServiceDetailID = shopServiceDetailID;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public float getAdjustPrice() {
        return adjustPrice;
    }

    public void setAdjustPrice(float adjustPrice) {
        this.adjustPrice = adjustPrice;
    }
}
