package com.example.tamagotchi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class FoodOptionsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_options);
    }

    public void feedSnack(View view){
        AppConstants.player.feedPet(new Food("Snack", 30));
    }

    public void feedMeal(View view){
        AppConstants.player.feedPet(new Food("Meal", 60));
    }

    public void feedKingsizedMeal(View view){
        AppConstants.player.feedPet(new Food("Kingsized Meal", 120));
    }
}
