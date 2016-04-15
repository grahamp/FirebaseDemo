package com.nauto.firebasetest;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by grahampoor on 4/10/16.
 */
public class FirebaseTestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
