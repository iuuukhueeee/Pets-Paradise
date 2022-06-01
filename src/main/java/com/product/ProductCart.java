package com.product;

import java.util.HashMap;
import java.util.Map;

public class ProductCart {
    private Map<String, ProductDTO> productCart;

    public ProductCart(){
        this.productCart = null;
    }

    public ProductCart(Map<String, ProductDTO> productCart){
        this.productCart = productCart;
    }

    public Map<String, ProductDTO> getProductCart(){
        return productCart;
    }

    public void setProductCart(Map<String, ProductDTO> productCart) {
        this.productCart = productCart;
    }

    public void addPro(ProductDTO product){
        if (this.productCart == null) this.productCart = new HashMap<>();
        if (this.productCart.containsKey(product.getProductID())){
            int currentQuantity = this.productCart.get(product.getProductID()).getQuantity();
            product.setQuantity(currentQuantity + product.getQuantity());
        }
        productCart.put(product.getProductID(),product);
    }

    public boolean editPro(String productID, ProductDTO product){
        boolean check = false;
        if(this.productCart != null){
            if(this.productCart.containsKey(productID)){
                this.productCart.replace(productID,product);
                check = true;
            }
        }
        return check;
    }

}
