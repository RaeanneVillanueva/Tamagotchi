package com.example.tamagotchi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean bound;
    private GameService gameService;
    private Intent gameServiceIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameServiceIntent = new Intent(this, GameService.class);
        if(!bound)
            bindService(gameServiceIntent, mConnection, BIND_AUTO_CREATE);
        startService(gameServiceIntent);

        gameService.startHunger(AppConstants.player.getCurrPet().getTimeUntilHungry());

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(bound)
            unbindService(mConnection);
        stopService(gameServiceIntent);
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            GameService.GameBinder binder = (GameService.GameBinder) service;
            gameService = (binder.getService());
            bound = true;
            Log.d(" - SERVICE","ServiceConnection made");
        }

//        @Override
//        public void onServiceConnected(ComponentName className,
//                                       IBinder service) {
//            MusicService.LocalBinder binder = (MusicService.LocalBinder) service;
//            musicService = (binder.getService());
//            bound = true;
//            Log.d(" - SERVICE","ServiceConnection made");
//        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };

    public void release(View view){
        AppConstants.player.releasePet();
        Toast.makeText(this, "Your pet went away :'(", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();
    }

    public void feed(View view){
        Intent intent = new Intent(this, FoodOptionsActivity.class);
        startActivityForResult(intent, 1);
    }

}
