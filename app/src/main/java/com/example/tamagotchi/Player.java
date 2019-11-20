package com.example.tamagotchi;

public class Player {
    private Pet currPet;

    public Player(){

    }

    public void releasePet(){
        if(currPet.isFull())
            currPet = null;
    }

    public void feedPet(Food food){
        currPet.feed(food);
    }

    public Pet getCurrPet() {
        return currPet;
    }

    public void setCurrPet(Pet currPet) {
        this.currPet = currPet;
    }

    public boolean hasPet(){
        return currPet != null;
    }

}
