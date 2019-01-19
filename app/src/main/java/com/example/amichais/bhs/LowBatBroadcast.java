package com.example.amichais.bhs;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;

import static android.content.Context.NOTIFICATION_SERVICE;



public class LowBatBroadcast extends BroadcastReceiver {

    private static String CHANNEL1_ID = "channel1";
    private static String CHANNEL1_NAME = "Channel 1 Demo";
    private String name;
    private Context context;
    private static int id = 1;
    private NotificationManager notificationManager;


    public LowBatBroadcast(String name){
        this.name = name;
    }

    private void createNotificationChannel()
    {
        notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            if (notificationManager.getNotificationChannel(CHANNEL1_ID) == null)
            {
                NotificationChannel notificationChannel = new NotificationChannel(
                        CHANNEL1_ID,
                        CHANNEL1_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT);

                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }

    public void showNotification()
    {
        String notificationTitle = "לידיעתך";
        String notificationText = "הסוללה חלשה (נשלח דיווח לבוס). תטעין בהקדם !!";

        Notification notification = new NotificationCompat.Builder(context, CHANNEL1_ID)
                .setSmallIcon(android.R.drawable.ic_menu_view)
                .setContentTitle(notificationTitle)
                .setContentText(notificationText)
                .build();

        notificationManager.notify(id, notification);
    }


    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;

        createNotificationChannel();
        showNotification();

        try {
            SmsManager sm = SmsManager.getDefault();
            sm.sendTextMessage("+972509777869", null, "הסוללה של " + name + " חלשה. הוא זמין בפלאפון החירום.", null, null);
        }catch (Exception e){}

    }

}
