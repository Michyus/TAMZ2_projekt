package com.example.michyus.piskvorky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private GameEngine gameEngine;
    private FrameLayout gameFrame;

    public TextView textView_moveOf;

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

        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        int aiLevel = intent.getIntExtra("aiLevel", 0);
        int size = intent.getIntExtra("size", 12);
        int count = intent.getIntExtra("count", 4);
        String name_1 = intent.getStringExtra("name_1");
        String name_2 = intent.getStringExtra("name_2");

        gameEngine = new GameEngine(this, aiLevel, size, count, name_1, name_2);

        textView_moveOf = findViewById(R.id.textView_moveOf);
        gameFrame = findViewById(R.id.gameFrame);

        gameFrame.addView(new Graphics(this, gameEngine));
        gameFrame.setOnTouchListener(new TouchListener(gameEngine));
    }
}
