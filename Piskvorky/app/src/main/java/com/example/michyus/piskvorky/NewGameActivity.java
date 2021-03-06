package com.example.michyus.piskvorky;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewGameActivity extends AppCompatActivity {
    Button button_gameAgainstAI;
    Button button_gameAgainstHuman;
    Button button_customGame;

    Context context;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        mp = MediaPlayer.create(context, R.raw.click);

        SharedPreferences prefs = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        boolean darkTheme = prefs.getBoolean("theme", false);
        if (darkTheme){
            setTheme(R.style.Theme_AppCompat_NoActionBar);
        }
        else {
            setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar);
        }

        setContentView(R.layout.activity_new_game);

        button_gameAgainstAI = findViewById(R.id.button_gameAgainstAI);
        button_gameAgainstHuman = findViewById(R.id.button_gameAgainstHuman);
        button_customGame = findViewById(R.id.button_customGame);

        button_gameAgainstAI.setOnClickListener(listener_gameAgainstAI);
        button_gameAgainstHuman.setOnClickListener(listener_gameAgainstHuman);
        button_customGame.setOnClickListener(listener_customGame);
    }

    View.OnClickListener listener_gameAgainstHuman = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mp.start();
            Intent intent = new Intent(NewGameActivity.this, GameActivity.class);
            intent.putExtra("aiLevel", 0);
            intent.putExtra("size", 12);
            intent.putExtra("count", 4);
            intent.putExtra("name_1", "Hrac 1");
            intent.putExtra("name_2", "Hrac 2");
            startActivity(intent);
        }
    };

    View.OnClickListener listener_gameAgainstAI = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mp.start();
            Intent intent = new Intent(NewGameActivity.this, GameActivity.class);
            intent.putExtra("aiLevel", 1);
            intent.putExtra("size", 12);
            intent.putExtra("count", 4);
            intent.putExtra("name_1", "Ja");
            intent.putExtra("name_2", "Telefon");
            startActivity(intent);
        }
    };

    View.OnClickListener listener_customGame = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mp.start();
            Intent intent = new Intent(NewGameActivity.this, CustomGameActivity.class);
            startActivity(intent);
        }
    };
}
