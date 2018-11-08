package com.example.josh.week3daily3_service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.josh.week3daily3_service.model.Person;
import com.example.josh.week3daily3_service.model.PersonGenerator;

import java.util.ArrayList;
import java.util.List;

public class ParcelableHelper implements Parcelable {
    String name;
    String age;
    String gender;
    Bitmap picture;

    public ParcelableHelper(String name, String age, String gender, Bitmap picture) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Bitmap getPicture() {
        return picture;
    }

    protected ParcelableHelper(Parcel in) {
        name = in.readString();
        age = in.readString();
        gender = in.readString();
        picture = in.readParcelable(getClass().getClassLoader());
    }

    public static final Creator<ParcelableHelper> CREATOR = new Creator<ParcelableHelper>() {
        @Override
        public ParcelableHelper createFromParcel(Parcel in) {
            return new ParcelableHelper(in);
        }

        @Override
        public ParcelableHelper[] newArray(int size) {
            return new ParcelableHelper[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(age);
        dest.writeString(gender);
        dest.writeParcelable(picture,flags);
    }
}
