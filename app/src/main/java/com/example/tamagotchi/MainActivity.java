package com.example.tamagotchi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Player player = new Player();
    Food food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view){
        player.setCurrPet(new Pet());
        //go to main
    }

    public void release(View view){
        player.release();
        //go to start
    }

    public void feedSnack(View view){
        food = new Food("Snack", 30);
        player.feed(food);
    }

    public void feedMeal(View view){
        food = new Food("Meal", 60);
        player.feed(food);
    }

    public void feedKingsizedMeal(View view){
        food = new Food("Kingsized Meal", 120);
        player.feed(food);
    }

    public void feed(View view){
        //go to feedOptions
    }

}
