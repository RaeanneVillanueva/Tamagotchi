package com.example.tamagotchi;

public class Player {
    private Pet currPet;

    public Player(){

    }

    public void release(){
        currPet = null;
    }

    public void feed(Food food){
        currPet.feed(food);
    }

    public Pet getCurrPet() {
        return currPet;
    }

    public void setCurrPet(Pet currPet) {
        this.currPet = currPet;
    }


}
