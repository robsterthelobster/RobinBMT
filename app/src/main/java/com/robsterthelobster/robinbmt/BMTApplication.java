package com.robsterthelobster.robinbmt;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by robin on 7/27/2016.
 */
public class BMTApplication extends Application{
    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
