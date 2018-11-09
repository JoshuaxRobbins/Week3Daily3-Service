package com.example.josh.week3daily3_service.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.josh.week3daily3_service.ParcelableHelper;
import com.example.josh.week3daily3_service.model.Person;
import com.example.josh.week3daily3_service.model.PersonGenerator;

import java.util.ArrayList;
import java.util.List;


public class MyIntentService extends IntentService {
    public static final String TAG = "_TAG";
    private static final String START_VIEW = "POPULATE_RECYCLER_VIEW";
    Person person;

    private static String numberOfPeople = "NUMBER_OF_PEOPLE";


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    public static void startActionPopulate(Context context, int number) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(START_VIEW);
        intent.putExtra(numberOfPeople, number);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int count = intent.getIntExtra(numberOfPeople, 0);
        person = PersonGenerator.generate(getApplicationContext());
        ParcelableHelper parcelableHelper = new ParcelableHelper(person.getName(), person.getAge(), person.getGender(), person.getPicture());
        Intent pIntent = new Intent("PERSON_SEND");
        pIntent.putExtra("Person", parcelableHelper);
        LocalBroadcastManager.getInstance(this).sendBroadcast(pIntent);
        Log.d(TAG, "onHandleIntent: ");

    }
}
