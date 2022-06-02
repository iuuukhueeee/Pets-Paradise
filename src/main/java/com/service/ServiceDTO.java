package com.service;

public class ServiceDTO {

    private String serviceID;
    private String serviceName;
    private float servicePrice;
    private String serviceDescription;


    public ServiceDTO(String serviceID, String serviceName, float servicePrice, String serviceDescription, boolean status) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.serviceDescription = serviceDescription;
    }

    public ServiceDTO() {
        this.serviceID = "";
        this.serviceName = "";
        this.servicePrice = 0;
        this.serviceDescription = "";
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public float getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(float servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

}
