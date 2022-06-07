package com.animal;

public class AnimalDTO {
    private String AnimalID;
    private String AnimalName;

    public AnimalDTO(){
        this.AnimalID = "";
        this.AnimalName = "";
    }

    public AnimalDTO(String animalID, String animalName){
        this.AnimalID = animalID;
        this.AnimalName = animalName;
    }

    public String getAnimalID() {
        return AnimalID;
    }

    public void setAnimalID(String animalID) {
        AnimalID = animalID;
    }

    public String getAnimalName() {
        return AnimalName;
    }

    public void setAnimalName(String animalName) {
        AnimalName = animalName;
    }
}
