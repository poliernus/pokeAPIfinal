package com.example.pokeapifinal;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushNotificationService extends FirebaseMessagingService {


    //Notification method. editar lo que quieras mostrar
    @SuppressLint("NewApi")
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        String title = message.getNotification().getTitle();
        String body = message.getNotification().getBody();
        String CHANNELD_ID = "MESSAGE";
        CharSequence name;
        NotificationChannel channel = new NotificationChannel(
                CHANNELD_ID,
                "Message Notification",
                NotificationManager.IMPORTANCE_HIGH);

        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Context context;
        Notification.Builder notification = new Notification.Builder(this, CHANNELD_ID)
                .setContentTitle(title)
                        .setContentText(body)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                        .setAutoCancel(true);
        NotificationManagerCompat.from(this).notify(1,notification.build());
        super.onMessageReceived(message);



    }
}
