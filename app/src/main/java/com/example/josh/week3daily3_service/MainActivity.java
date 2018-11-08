package com.example.josh.week3daily3_service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.josh.week3daily3_service.services.MusicService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

    }

    // Foreground Service Stuffs
    private void createNotificationChannel(){

        String name = "Music";
        String description = "Description and stuff";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel(MusicService.CHANNEL_ID,name,importance);
        channel.setDescription(description);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String textSize;
        Intent intent = new Intent(MainActivity.this,MusicService.class);
        switch (item.getItemId()) {
            case R.id.action_start:
                intent.setAction(MusicService.PLAY_MUSIC);
                break;
            case R.id.action_pause:
                intent.setAction(MusicService.PAUSE_MUSIC);
                break;
            case R.id.action_stop:
                intent.setAction(MusicService.STOP_MUSIC);
                break;



        }
        startService(intent);
        return super.onOptionsItemSelected(item);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return true;
    }

    public void openRecyclerView(View view) {
        Intent intent = new Intent();
    }
}
