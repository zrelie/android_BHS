package com.example.amichais.bhs;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import java.util.Timer;
import java.util.TimerTask;

public class TimeServiceSrika extends Service {

    private final long INTERVAL = 10 * 1000;;
    private Timer mTimer1;
    private MediaPlayer sound;

    @Override
    public void onCreate() {
        mTimer1 = new Timer();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        sound = MediaPlayer.create(this, R.raw.elarm);
        mTimer1.schedule(new TimeDisplayTimerTask(), INTERVAL);

        return START_STICKY;
    }



    @Override
    public void onDestroy(){
        super.onDestroy();

        sound.stop();
    }

    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {

            new Thread(){
                @Override
                public void run() {
                    sound.start();
                }
            }.start();
        }
    }
}
