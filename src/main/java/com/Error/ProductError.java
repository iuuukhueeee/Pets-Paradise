
package com.Error;

public class ProductError {
    private String productIDError;
    private String productCategoryIDError;
    private String NameError;
    private String quantityError;
    private String imageError;
    private String priceError;
    private String importDateError;
    private String expiredDateError;

    public ProductError(String productIDError, String productCategoryIDError, String nameError, String quantityError, String imageError, String priceError, String importDateError, String expiredDateError) {
        this.productIDError = productIDError;
        this.productCategoryIDError = productCategoryIDError;
        this.NameError = nameError;
        this.quantityError = quantityError;
        this.imageError = imageError;
        this.priceError = priceError;
        this.importDateError = importDateError;
        this.expiredDateError = expiredDateError;
    }

    public ProductError() {
        this.productIDError = "";
        this.productCategoryIDError = "";
        this.NameError = "";
        this.quantityError = "";
        this.imageError = "";
        this.priceError = "";
        this.importDateError = "";
        this.expiredDateError = "";
    }

    public String getProductIDError() {
        return productIDError;
    }

    public void setProductIDError(String productIDError) {
        this.productIDError = productIDError;
    }

    public String getProductCategoryIDError() {
        return productCategoryIDError;
    }

    public void setProductCategoryIDError(String productCategoryIDError) {
        this.productCategoryIDError = productCategoryIDError;
    }

    public String getNameError() {
        return NameError;
    }

    public void setNameError(String nameError) {
        NameError = nameError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getImportDateError() {
        return importDateError;
    }

    public void setImportDateError(String importDateError) {
        this.importDateError = importDateError;
    }

    public String getExpiredDateError() {
        return expiredDateError;
    }

    public void setExpiredDateError(String expiredDateError) {
        this.expiredDateError = expiredDateError;
    }
}
