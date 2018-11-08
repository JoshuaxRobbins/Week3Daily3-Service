package com.example.josh.week3daily3_service.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;

import com.example.josh.week3daily3_service.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonGenerator {

    public static Person generate(Context context){
        List<Person> personList = new ArrayList<>();

            Person person = new Person(getRandomName(),getRandomAge(),getRandomGender(),getRandomPicture(context));


        return person;

    }

    public static Bitmap getRandomPicture(Context context){

        List<Bitmap> pictures = new ArrayList<>();
        pictures.add(BitmapFactory.decodeResource(context.getResources(),R.drawable.emoji1));
        pictures.add(BitmapFactory.decodeResource(context.getResources(),R.drawable.emoji2));
        pictures.add(BitmapFactory.decodeResource(context.getResources(),R.drawable.emoji3));

        return pictures.get(new Random().nextInt(pictures.size()));
    }

    public static String getRandomName(){
        List<String> names = new ArrayList<>();
        names.add("Josh");
        names.add("Assem");
        names.add("Nathan");
        names.add("Keenen");
        names.add("Tim");
        names.add("Michael");
        names.add("Bernardo");

        return names.get(new Random().nextInt(names.size()));


    }

    public static String getRandomAge(){
        return String.valueOf((new Random().nextInt(40)));
    }

    public static String getRandomGender(){
        List<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        return genders.get(new Random().nextInt(2));

    }
}
