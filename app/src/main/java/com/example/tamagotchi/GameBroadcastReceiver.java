package com.example.tamagotchi;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import java.text.Normalizer;


public class GameBroadcastReceiver extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "777";
    public static String NOTIFICATION = "notification";
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private static final String default_notification_channel_id = "default" ;

    @Override
    public void onReceive(Context context, Intent intent) {

        //for ringtone
        Uri ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //for notifications
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context. NOTIFICATION_SERVICE );

        //goes to mainactivity when notif is clicked
        Intent fullScreenIntent = new Intent(context, MainActivity.class);
        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(context, 0,
                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable. ic_launcher_background ) ;
        builder.setAutoCancel( true );
        builder.setContentIntent(fullScreenPendingIntent);
        builder.setSound(ringtone);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setFullScreenIntent(fullScreenPendingIntent, true);

//        if(hungry){
//            builder.setContentTitle( "Hungry Pet!" ) ;
//            builder.setContentText("Hey, I am hungry! Feed me!") ;
//        }else {
//            builder.setContentTitle( "Pet Escaped!" ) ;
//            builder.setContentText("I will find food outside, bye!") ;
//        }

        Notification gameNotification = builder.build();
        notificationManager.notify(1, gameNotification);

        //for vibrate
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        //for alarm
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, 3000, fullScreenPendingIntent);

    }

//    public Notification getHungryNotification(Context context) {
//
//        Intent fullScreenIntent = new Intent(context, MainActivity.class);
//        PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(context, 0,
//                fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Notification.Builder builder = new Notification.Builder(context);
//        builder.setContentTitle( "Hungry Pet!" ) ;
//        builder.setContentText("Hey, I am hungry! Feed me!") ;
//        builder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
//        builder.setAutoCancel( true );
//        builder.setFullScreenIntent(fullScreenPendingIntent, true);
//
//        return builder.build() ;
//    }
//
//    public Notification getEscapeNotification(Context context) {
//
//        Notification.Builder builder = new Notification.Builder(context);
//        builder.setContentTitle( "Pet Escaped!" ) ;
//        builder.setContentText("I will find food outside, bye!") ;
//        builder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
//        builder.setAutoCancel( true ) ;
//        return builder.build() ;
//    }
}
