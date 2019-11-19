package com.example.tamagotchi;

public class Pet {
    private String name;
    private int timeUntilHungry;
    private PetState petState;

    public Pet(){
        this.timeUntilHungry = 30;
    }

    public void feed(Food food) {
        setTimeUntilHungry(food.getTimeAdded());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getTimeUntilHungry() {
        return timeUntilHungry;
    }

    public void setTimeUntilHungry(int timeUntilHungry) {
        this.timeUntilHungry = timeUntilHungry;
        if (timeUntilHungry >= 30) {
            petState = PetState.FULL;
        }
        if (timeUntilHungry >= 60) {
            petState = PetState.VERYFULL;
        }
        if (timeUntilHungry >= 120) {
            petState = PetState.BLOATED;
        }
    }

    public PetState getPetState() {
        return petState;
    }

    public boolean isFull(){
        if(petState == PetState.FULL || petState == PetState.VERYFULL || petState == PetState.BLOATED)
            return true;
        return false;
    }

}
