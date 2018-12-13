package com.example.michyus.piskvorky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    Button button_resetStatistics;
    Switch switch_theme;
    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    boolean darkTheme;
    DBHelper games_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        editor = getSharedPreferences("sharedPreferences", MODE_PRIVATE).edit();
        prefs = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        darkTheme = prefs.getBoolean("theme", false);

        button_resetStatistics = findViewById(R.id.button_resetStatistics);
        switch_theme = findViewById(R.id.switch_theme);

        button_resetStatistics.setOnClickListener(listener_resetStatistics);
        switch_theme.setOnClickListener(listener_theme);

        switch_theme.setChecked(darkTheme);
        games_db = new DBHelper(this);
    }

    View.OnClickListener listener_resetStatistics = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            games_db.removeAll();
        }
    };

    View.OnClickListener listener_theme = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (darkTheme){
                editor.putBoolean("theme", false);
                Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();
            }
            else {
                editor.putBoolean("theme", true);
                Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_SHORT).show();
            }
            editor.apply();
        }
    };

    @Override
    public void onStop(){
        super.onStop();


        Intent intent = new Intent(Settings.this, MainActivity.class);
        startActivity(intent);


    }
}

