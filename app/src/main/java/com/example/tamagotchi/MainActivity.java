package com.example.tamagotchi;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean bound = false;
    private GameService gameService;
    private Intent gameServiceIntent;
    private ProgressBar hungerMeter;
    private MyBroadRequestReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hungerMeter = findViewById(R.id.hungerMeter);

        IntentFilter filter = new IntentFilter("UPDATE PROGRESS BAR");
        receiver = new MyBroadRequestReceiver();
        registerReceiver( receiver, filter);

        gameServiceIntent = new Intent(this, GameService.class);
        if(!bound)
            bindService(gameServiceIntent, mConnection, BIND_AUTO_CREATE);

        startService(gameServiceIntent);

        if(AppConstants.player.getCurrPet() == null){
            Intent releaseIntent = new Intent(MainActivity.this, StartActivity.class );
            startActivity(releaseIntent);
            finish();
        }

        AppConstants.isStopped = false;

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(bound)
            unbindService(mConnection);
        stopService(gameServiceIntent);

        this.unregisterReceiver(receiver);
    }

    @Override
    public void onStop(){
        super.onStop();
        AppConstants.isStopped = true;
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            GameService.GameBinder binder = (GameService.GameBinder) service;
            gameService = binder.getService();
            bound = true;
        }


        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };

    public void release(View view){
        if(AppConstants.player.getCurrPet().getPetState() != PetState.HUNGRY) {
            AppConstants.player.releasePet();
            Toast.makeText(this, "Your pet went away :'(", Toast.LENGTH_LONG).show();
            gameService.stopTimer();
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Cannot release when pet is HUNGRY", Toast.LENGTH_LONG).show();
        }
    }

    public void feed(View view){
        if(AppConstants.player.getCurrPet().getPetState() == PetState.HUNGRY) {
            gameService.stopTimer();
            Intent intent = new Intent(this, FoodOptionsActivity.class);
            startActivityForResult(intent, 1);
        }else{
            Toast.makeText(this, "Cannot feed when pet is still FULL", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            if(resultCode == 1){
                gameService.startTimer();
            }
        }
    }

    public class MyBroadRequestReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int time = intent.getIntExtra("TIMELEFT", 0);
            float percentage = (float) ((float) time / 120.0 * 100.0);
            hungerMeter.setProgress((int) percentage);

           if(AppConstants.player.getCurrPet() == null && !AppConstants.isStopped){
                Intent releaseIntent = new Intent(MainActivity.this, StartActivity.class );
                startActivity(releaseIntent);
                finish();
           }
        }

    }

}
