package com.example.josh.week3daily3_service.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.josh.week3daily3_service.MainActivity;
import com.example.josh.week3daily3_service.R;

public class MusicService extends Service {
    public static final String TAG = "_TAG";
    public static final String PLAY_MUSIC = "ACTION_START_FOREGROUND_SERVICE";
    public static final String PAUSE_MUSIC = "ACTION_PAUSE";
    public static final String STOP_MUSIC = "ACTION_STOP_FOREGROUND_SERVICE";
    public static final String CHANNEL_ID = "MUSIC_PLAYER";
    MediaPlayer mediaPlayer;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        if (intent.getAction().equals(PLAY_MUSIC)) {
            Log.d(TAG, "onStartCommand: 2");
            Intent notificationIntent = new Intent(this, MainActivity.class);
            notificationIntent.setAction("main");
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    notificationIntent, 0);

            Intent playIntent = new Intent(this, MusicService.class);
            playIntent.setAction(PLAY_MUSIC);
            PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                    playIntent, 0);

            Intent pauseIntent = new Intent(this, MusicService.class);
            pauseIntent.setAction(PAUSE_MUSIC);
            PendingIntent ppauseIntent = PendingIntent.getService(this, 0,
                    pauseIntent, 0);

            Intent stopIntent = new Intent(this, MusicService.class);
            stopIntent.setAction(STOP_MUSIC);
            PendingIntent pstopIntent = PendingIntent.getService(this, 0,
                    stopIntent, 0);


            Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                    .setContentTitle("Music Player")
                    .setTicker("Music Player")
                    .setContentText("My Music")
                    .setSmallIcon(R.drawable.play_image)
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .addAction(android.R.drawable.ic_media_play,
                            "Play", pplayIntent)
                    .addAction(android.R.drawable.ic_media_pause, "Pause",
                            ppauseIntent)
                    .addAction(R.drawable.stop_image, "Stop",
                            pstopIntent).build();
            startForeground(1, notification);
            startMusicPlayer(true);
        } else if (intent.getAction().equals(PAUSE_MUSIC)) {
            startMusicPlayer(false);

        } else if (intent.getAction().equals(STOP_MUSIC)) {
            Log.i(TAG, "Received Stop Foreground Intent");
            startMusicPlayer(false);
            stopForeground(true);
            stopSelf();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public void startMusicPlayer(boolean play){
        Log.d(TAG, "startMusicPlayer: ");
        if(mediaPlayer == null)
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.song);
        if (play) {
            mediaPlayer.start();
        }
        else
            mediaPlayer.pause();

    }
}
