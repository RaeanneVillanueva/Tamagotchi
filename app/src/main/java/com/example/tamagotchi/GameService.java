package com.example.tamagotchi;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class GameService extends Service {

    private final IBinder gameBind = new GameBinder();
    private static Timer timer = new Timer();
    private GameEngine gameEngine = AppConstants.gameEngine;
    private Context context;

    @Override
    public IBinder onBind(Intent intent) {
        return gameBind;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        gameEngine = null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("GAMESERVICE", "onCreate()");
        feed(AppConstants.player.getCurrPet().getTimeUntilHungry());
        context = this;
    }


    public void feed(int seconds) {
        int updateInterval = 1000;
        timer.scheduleAtFixedRate(new updateTask(seconds), 0, updateInterval);
    }

    public void stopTimer(){
        timer.cancel();
        timer.purge();
    }



    private class updateTask extends TimerTask {
        private int seconds;
        public updateTask(int seconds){
            super();
            this.seconds = seconds;
        }


        int i = 0;
        @Override
        public void run(){
            i++;
            if(i == seconds) {
                Log.d("GAMESERVICE","NOTIF");
            }else {
                Log.d("GAMESERVICE","Time Left: " + (seconds - (i %seconds)));
            }
        }
    }

    public class GameBinder extends Binder {
        GameService getService(){
            return GameService.this;
        }
    }
}
