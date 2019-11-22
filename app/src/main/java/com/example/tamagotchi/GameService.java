package com.example.tamagotchi;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.tamagotchi.GameBroadcastReceiver.NOTIFICATION_CHANNEL_ID;

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

                //NOTIFY!!!
                //if hungry
                notify(hungryNotification());

                //if escaped
                notify(escapedNotification());
            }else {
                Log.d("GAMESERVICE","Time Left: " + (seconds - (i %seconds)));
            }
        }


        //functions for notify, it includes alarm, ringtone, and notification

        //triggers the notification to pop up
        public void notify(Notification notification) {
            Intent intent = new Intent(context, GameBroadcastReceiver.class);
            intent.putExtra(GameBroadcastReceiver.NOTIFICATION, notification);
            intent.putExtra(GameBroadcastReceiver.NOTIFICATION_ID, 1);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                    + 5000, pendingIntent);
        }

        //creates a notification if the pet is hungry
        public Notification hungryNotification() {
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder( context, NOTIFICATION_CHANNEL_ID) ;
            builder.setContentTitle( "Your pet is hungry!" ) ;
            builder.setContentText("Ship shop, feed me please :(") ;
            builder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
            builder.setAutoCancel( true );
            builder.setContentIntent(pendingIntent);
            builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
            return builder.build() ;
        }

        //creates a notification when the pet escaped
        public Notification escapedNotification() {
            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder( context, NOTIFICATION_CHANNEL_ID) ;
            builder.setContentTitle( "Your pet escaped" ) ;
            builder.setContentText("Bye bye now! I find food outside.") ;
            builder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
            builder.setAutoCancel( true );
            builder.setContentIntent(pendingIntent);
            builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
            return builder.build() ;
        }

    }

    public class GameBinder extends Binder {
        GameService getService(){
            return GameService.this;
        }
    }
}
