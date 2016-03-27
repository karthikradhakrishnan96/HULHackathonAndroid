package com.hulhack.quandrum.wireframes.gcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.hulhack.quandrum.wireframes.R;
import com.hulhack.quandrum.wireframes.activities.NavActivity;

public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";

    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle extras) {
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        Log.e("GCMListener", "we been tickled lads");
        for (String key : extras.keySet()) {
            Log.i("GCM", key);
        }
        String mes = extras.getString("title");

        Log.i("GCM", "Received : " + extras.getString("title"));


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent intent = new Intent(this, NavActivity.class);
// use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
        Notification n = new Notification.Builder(this)
                .setContentTitle("Alert!")
                .setContentText(mes)
                .setSmallIcon(R.drawable.brij)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setStyle(new Notification.BigTextStyle().bigText(mes))
                .setContentIntent(pIntent).build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, n);
    }


}


