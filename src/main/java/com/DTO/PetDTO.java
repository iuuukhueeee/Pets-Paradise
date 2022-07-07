package com.DTO;

public class PetDTO {
    private String petID;
    private String username;
    private String animalID;
    private String animalName;
    private int animalAge;
    private String animalPicture;
    private String animalDescription;

    public PetDTO() {
    }

    public PetDTO(String petID, String username, String animalID, String animalName, int animalAge, String animalPicture, String animalDescription) {
        this.petID = petID;
        this.username = username;
        this.animalID = animalID;
        this.animalName = animalName;
        this.animalAge = animalAge;
        this.animalPicture = animalPicture;
        this.animalDescription = animalDescription;
    }

    public String getPetID() {
        return petID;
    }

    public void setPetID(String petID) {
        this.petID = petID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAnimalID() {
        return animalID;
    }

    public void setAnimalID(String animalID) {
        this.animalID = animalID;
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
}
