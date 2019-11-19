package com.example.tamagotchi;

public class AppConstants {
    static GameEngine gameEngine;
    static GameState gameState;
    static Player player;

    public static void init(){
        gameEngine = new GameEngine();
        gameState = GameState.NOPET;
        player = new Player();
    }

    public static GameEngine getGameEngine(){
        return gameEngine;
    }

}
