package com.pet;

import java.util.Date;

public class PetDTO {
    private String petID;
    private String animalID;
    private String orderDetailID;
    private String animalName;
    private int animalAge;
    private String animalPicture;
    private String animalDescription;
    private Date bookingTime;

    public PetDTO(){
        this.petID = "";
        this.animalID = "";
        this.orderDetailID = "";
        this.animalName = "";
        this.animalAge = 0;
        this.animalPicture = "";
        this.animalDescription = "";
        this.bookingTime = null;
    }

    public PetDTO(String petID, String animalID, String orderDetailID, String animalName, int animalAge, String animalPicture, String animalDescription, Date bookingTime){
        this.petID = petID;
        this.animalID = animalID;
        this.orderDetailID = orderDetailID;
        this.animalName = animalName;
        this.animalAge = animalAge;
        this.animalPicture = animalPicture;
        this.animalDescription = animalDescription;
        this.bookingTime = bookingTime;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getAnimalID() {
        return animalID;
    }

    public void setAnimalID(String animalID) {
        this.animalID = animalID;
    }

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public int getAnimalAge() {
        return animalAge;
    }

    public void setAnimalAge(int animalAge) {
        this.animalAge = animalAge;
    }

    public String getAnimalPicture() {
        return animalPicture;
    }

    public void setAnimalPicture(String animalPicture) {
        this.animalPicture = animalPicture;
    }

    public String getAnimalDescription() {
        return animalDescription;
    }

    public void setAnimalDescription(String animalDescription) {
        this.animalDescription = animalDescription;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }
}
