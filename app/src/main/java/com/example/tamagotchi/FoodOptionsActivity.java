package com.example.tamagotchi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FoodOptionsActivity extends AppCompatActivity {

    Food food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_options);
        getSupportActionBar().hide();
    }

    public void feedSnack(View view){
        feed("Snack", 30);
    }

    public void feedMeal(View view){
        feed("Meal", 60);
    }

    public void feedKingsizedMeal(View view){
        feed("KingsizedMeal", 120);
    }

    private void feed(String name, int timeAdded){
        food = new Food(name, timeAdded);
        AppConstants.player.feedPet(food);
        Intent intent = new Intent();
        setResult(1, intent);
        finish();
    }
}
