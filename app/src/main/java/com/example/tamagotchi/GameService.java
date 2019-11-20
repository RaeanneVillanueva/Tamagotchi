package com.example.tamagotchi;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

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
        context = this;
    }


    public void startHunger(int seconds) {
        int updateInterval = 1000;
        timer.scheduleAtFixedRate(new updateTask(seconds), 0, updateInterval);
    }



    private class updateTask extends TimerTask {
        private int seconds;
        public updateTask(int seconds){
            super();
            this.seconds = seconds;
        }
        int i = 0;
        @Override
        public void run()
        {
            i++;
            if(i == seconds) {
                //NOTIF!!!
            }else {
                //time left
            }
        }
    }

    public class GameBinder extends Binder {
        GameService getService(){
            return GameService.this;
        }
    }
}
