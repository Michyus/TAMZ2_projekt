package com.example.michyus.piskvorky;

import android.content.Intent;
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
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        int aiLevel = intent.getIntExtra("aiLevel", 0);

        gameEngine = new GameEngine(this, aiLevel);

        textView_moveOf = findViewById(R.id.textView_moveOf);
        gameFrame = findViewById(R.id.gameFrame);

        gameFrame.addView(new Graphics(this, gameEngine));
        gameFrame.setOnTouchListener(new TouchListener(gameEngine));
    }
}
