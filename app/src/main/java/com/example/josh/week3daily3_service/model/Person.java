package com.example.josh.week3daily3_service.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Person {

    String name;
    String age;
    String gender;
    Bitmap picture;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public Person(String name, String age, String gender, Bitmap picture) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.picture = picture;
    }

    public Bitmap getPicture() {

        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
