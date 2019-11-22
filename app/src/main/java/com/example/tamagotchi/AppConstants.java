package com.example.tamagotchi;

import android.widget.ProgressBar;

public class AppConstants {
    static Player player = null;
    static boolean hasTimer = false;
    public static void init(){
        player = new Player();
        player.setCurrPet(new Pet());
    }

}
