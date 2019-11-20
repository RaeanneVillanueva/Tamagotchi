package com.example.tamagotchi;

import android.widget.ProgressBar;

public class AppConstants {
    static GameEngine gameEngine;
    static Player player;
    static ProgressBar progressBar;

    public static void init(){
        gameEngine = new GameEngine();
        player = new Player();
    }

    public static GameEngine getGameEngine(){
        return gameEngine;
    }

}
