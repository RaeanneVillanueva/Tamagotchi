package com.example.tamagotchi;

public class Food {
    private String name;
    private int timeAdded;

    public Food(String name, int timeAdded){
        this.name = name;
        this.timeAdded = timeAdded;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getTimeAdded(){
        return timeAdded;
    }

    public void setTimeAdded(int timeAdded){
        this.timeAdded = timeAdded;
    }
}
