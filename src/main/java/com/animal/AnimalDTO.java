package com.animal;

public class AnimalDTO {
    private String animalID;
    private String animalName;

    public AnimalDTO(){
        this.animalID = "";
        this.animalName = "";
    }

    public AnimalDTO(String animalID, String animalName){
        this.animalID = animalID;
        this.animalName = animalName;
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
}
