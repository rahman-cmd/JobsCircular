package com.samimhossain.jobscircular;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        getFireBaseMsg(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }


    public void getFireBaseMsg(String title, String MsgBody){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notifChannel =new NotificationChannel("NotifId","NotifId", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager myNotifManager = getSystemService(NotificationManager.class);
            myNotifManager.createNotificationChannel(notifChannel);
        }

        Intent myIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getService(
                this,
                0,
                myIntent,
                0);


        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this, "NotifId");
        nBuilder.setContentTitle(title);
        nBuilder.setContentText(MsgBody);
        nBuilder.setSmallIcon(R.drawable.ic_launcher_background);
        nBuilder.setAutoCancel(true);
        nBuilder.setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1, nBuilder.build());


    }
}
