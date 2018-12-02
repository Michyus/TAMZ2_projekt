package com.example.michyus.piskvorky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button_newGame;
    Button button_statistics;
    Button button_settings;
    Button button_quit;

    SharedPreferences.Editor editor;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        editor = getSharedPreferences("sharedPreferences", MODE_PRIVATE).edit();
        prefs = getSharedPreferences("sharedPreferences", MODE_PRIVATE);


        if (prefs.contains("theme")){
            boolean darkTheme = prefs.getBoolean("theme", false);
            if (darkTheme){
                setTheme(R.style.Theme_AppCompat_NoActionBar);
            }
            else {
                setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar);
            }
        }
        else {
            editor.putBoolean("theme", false);
            editor.apply();
            setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar);
        }


        setContentView(R.layout.activity_main);

        button_newGame = findViewById(R.id.button_newGame);
        button_statistics = findViewById(R.id.button_statistics);
        button_settings = findViewById(R.id.button_settings);
        button_quit = findViewById(R.id.button_quit);

        button_newGame.setOnClickListener(listener_newGame);
        button_statistics.setOnClickListener(listener_statistics);
        button_settings.setOnClickListener(listener_settings);
        button_quit.setOnClickListener(listener_quit);
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
            boolean darkTheme = prefs.getBoolean("theme", false);
            if (darkTheme){
                setTheme(R.style.Theme_AppCompat_NoActionBar);
            }
            else {
                setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar);
            }
    }


    View.OnClickListener listener_newGame = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, NewGameActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener listener_statistics = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Statistics.class);
            startActivity(intent);
        }
    };

    View.OnClickListener listener_settings = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
        }
    };

    View.OnClickListener listener_quit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "listener_quit", Toast.LENGTH_SHORT).show();
        }
    };
}
