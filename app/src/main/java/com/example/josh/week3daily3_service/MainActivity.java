package com.example.josh.week3daily3_service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.josh.week3daily3_service.model.Person;
import com.example.josh.week3daily3_service.services.MusicService;
import com.example.josh.week3daily3_service.services.MyIntentService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "_TAG";
    private RecyclerViewAdapter adapter;
    List<Person> personList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,new IntentFilter("PERSON_SEND"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
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
        Log.d(TAG, "openRecyclerView: ");
        Intent intent = new Intent(getApplicationContext(),MyIntentService.class);
        intent.setAction("ADD_PERSON");
        startService(intent);
    }

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: ");
            ParcelableHelper parcelableHelper = intent.getParcelableExtra("Person");
            Person person = new Person(parcelableHelper.getName(),parcelableHelper.getAge()
                    ,parcelableHelper.getGender(),parcelableHelper.getPicture());
            bindRecyclerView(person);

        }
    };

    private void bindRecyclerView(Person person) {
        personList.add(person);
        if (adapter == null) {
            RecyclerView rvPersonList = findViewById(R.id.rvRecyclerView);
            adapter = new RecyclerViewAdapter(personList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            rvPersonList.setAdapter(adapter);
            rvPersonList.setLayoutManager(layoutManager);
        }
        else{
            adapter.notifyDataSetChanged();
        }
    }

}
