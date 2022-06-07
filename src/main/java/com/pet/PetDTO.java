package com.pet;

import java.util.Date;

public class PetDTO {
    private String PetID;
    private String AnimalID;
    private String OrderDetailID;
    private String AnimalName;
    private int AnimalAge;
    private String AnimalPicture;
    private String AnimalDescription;
    private Date BookingTime;

    public PetDTO(){
        this.PetID = "";
        this.AnimalID = "";
        this.OrderDetailID = "";
        this.AnimalName = "";
        this.AnimalAge = 0;
        this.AnimalPicture = "";
        this.AnimalDescription = "";
        this.BookingTime = null;
    }

    public PetDTO(String petID, String animalID, String orderDetailID, String animalName, int animalAge, String animalPicture, String animalDescription, Date bookingTime){
        this.PetID = petID;
        this.AnimalID = animalID;
        this.OrderDetailID = orderDetailID;
        this.AnimalName = animalName;
        this.AnimalAge = animalAge;
        this.AnimalPicture = animalPicture;
        this.AnimalDescription = animalDescription;
        this.BookingTime = bookingTime;
    }

    public String getPetID() {
        return PetID;
    }

    public void setPetID(String petID) {
        PetID = petID;
    }

    public String getAnimalID() {
        return AnimalID;
    }

    public void setAnimalID(String animalID) {
        AnimalID = animalID;
    }

    public String getOrderDetailID() {
        return OrderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        OrderDetailID = orderDetailID;
    }

    public String getAnimalName() {
        return AnimalName;
    }

    public void setAnimalName(String animalName) {
        AnimalName = animalName;
    }

    public int getAnimalAge() {
        return AnimalAge;
    }

    public void setAnimalAge(int animalAge) {
        AnimalAge = animalAge;
    }

    public String getAnimalPicture() {
        return AnimalPicture;
    }

    public void setAnimalPicture(String animalPicture) {
        AnimalPicture = animalPicture;
    }

    public String getAnimalDescription() {
        return AnimalDescription;
    }

    public void setAnimalDescription(String animalDescription) {
        AnimalDescription = animalDescription;
    }

    public Date getBookingTime() {
        return BookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        BookingTime = bookingTime;
    }
}
