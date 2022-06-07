package com.checkout;

import com.product.ProductDTO;
import com.service.ServiceDTO;

import javax.xml.ws.Service;

public class Item {
    ProductDTO product;
    ServiceDTO service;

    private String itemID;

    public Item(){
        this.product = new ProductDTO();
        this.service = new ServiceDTO();
        this.itemID = "";
    }




    public Item(ProductDTO product, ServiceDTO service, String itemID){
        this.product = product;
        this.service = service;
        this.itemID = itemID;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public ServiceDTO getService() {
        return service;
    }

    public void setService(ServiceDTO service) {
        this.service = service;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

}


