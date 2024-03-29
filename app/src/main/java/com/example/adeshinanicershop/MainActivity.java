package com.example.adeshinanicershop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Method to lauch menu activity
    public void launchMenuActivity(View view) {
        Log.d(LOG_TAG, "Start shopping Button clicked!");
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
