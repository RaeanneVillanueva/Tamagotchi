package com.example.tamagotchi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class FoodOptionsActivity extends AppCompatActivity {

    Food food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_options);
    }

    public void feedSnack(View view){
        food = new Food("Snack", 30);
        AppConstants.player.feedPet(food);
    }

    public void feedMeal(View view){
        food = new Food("Snack", 60);
        AppConstants.player.feedPet(food);
    }

    public void feedKingsizedMeal(View view){
        food = new Food("Snack", 120);
        AppConstants.player.feedPet(food);
    }
}
