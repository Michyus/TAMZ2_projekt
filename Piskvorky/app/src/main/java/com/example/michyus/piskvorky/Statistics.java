package com.example.michyus.piskvorky;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        boolean darkTheme = prefs.getBoolean("theme", false);
        if (darkTheme){
            setTheme(R.style.Theme_AppCompat_NoActionBar);
        }
        else {
            setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar);
        }

        setContentView(R.layout.activity_statistics);
    }
}
