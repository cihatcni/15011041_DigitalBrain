package com.cihatcni.a15011041_digitalbrain;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();
        System.out.println("ALARM ÇALIYOOOOOOOOOOOOOOOOOOOOOOR...................................");
        Context ctx = context.getApplicationContext();
        String NOTIFICATION_CHANNEL_ID = "Digital Brain";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.notif)
                .setContentTitle("NOTEEEEEE")
                .setContentText("NOTUN VAR ")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(12457826, builder.build());
        
    }
}
