package com.Error;

public class ServiceError {
    private String serviceIDError;
    private String serviceNameError;
    private String servicePriceError;
    private String serviceDescriptionError;

    public ServiceError() {
        this.serviceIDError = "";
        this.serviceNameError = "";
        this.servicePriceError = "";
        this.serviceDescriptionError = "";
    }

    public ServiceError(String serviceIDError, String serviceNameError, String servicePriceError, String serviceDescriptionError) {
        this.serviceIDError = serviceIDError;
        this.serviceNameError = serviceNameError;
        this.servicePriceError = servicePriceError;
        this.serviceDescriptionError = serviceDescriptionError;
    }

    public String getServiceIDError() {
        return serviceIDError;
    }

    public void setServiceIDError(String serviceIDError) {
        this.serviceIDError = serviceIDError;
    }

    public String getServiceNameError() {
        return serviceNameError;
    }

    public void setServiceNameError(String serviceNameError) {
        this.serviceNameError = serviceNameError;
    }

    public String getServicePriceError() {
        return servicePriceError;
    }

    public void setServicePriceError(String servicePriceError) {
        this.servicePriceError = servicePriceError;
    }

    public String getServiceDescriptionError() {
        return serviceDescriptionError;
    }

    public void setServiceDescriptionError(String serviceDescriptionError) {
        this.serviceDescriptionError = serviceDescriptionError;
    }
}