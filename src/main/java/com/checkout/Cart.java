package com.checkout;

import com.product.ProductDTO;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<String,Item> cart;

    public Cart(){
        Map<String,Item> cart = new HashMap<>();
    }

    public Cart(Map<String, Item> cart){
        this.cart = cart;
    }



    public void addService(Item item){
        if(this.cart == null) this.cart = new HashMap<>();
        cart.put(new Item().service.getServiceID(),item);
    }

    public boolean editService(String serviceID, Item item){
        boolean check = false;
        if(this.cart  != null){
            if(this.cart.containsKey(serviceID)){
                this.cart.replace(serviceID,item);
                check = true;
            }
        }
        return check;
    }

    public boolean removeService(String serviceID){
        boolean check = false;
        if (this.cart != null){
            if(this.cart.containsKey(serviceID)){
                this.cart.remove(serviceID);
                check = true;
            }
        }
        return check;
    }

    public void addProduct(Item item){
        if(this.cart == null) this.cart = new HashMap<>();
        cart.put(new Item().product.getProductID(),item);
    }

    public boolean editProduct(String productID, Item item){
        boolean check = false;
        if(this.cart != null){
            if(this.cart.containsKey(productID)){
                this.cart.replace(productID,item);
                check = true;
            }
        }
        return check;
    }

    public boolean RemoveProduct(String productID){
        boolean check = false;
        if (this.cart != null){
            if(this.cart.containsKey(productID)){
                this.cart.remove(productID);
                check = true;
            }
        }
        return check;
    }

    public Map<String, Item> getCart() {
        return cart;
    }

    public void setCart(Map<String, Item> cart) {
        this.cart = cart;
    }
}
