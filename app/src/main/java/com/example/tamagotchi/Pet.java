package com.example.tamagotchi;

public class Pet {
    private String name;
    private int timeUntilHungry;

    public Pet(){
        this.timeUntilHungry = 30;
    }

    public void feed(Food food) {
        this.timeUntilHungry = food.getTimeAdded();
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
    }
}
