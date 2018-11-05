package com.example.michyus.piskvorky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewGameActivity extends AppCompatActivity {
    Button button_standardGame;
    Button button_customGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        button_standardGame = findViewById(R.id.button_standardGame);
        button_customGame = findViewById(R.id.button_customGame);

        button_standardGame.setOnClickListener(listener_standardGame);
        button_customGame.setOnClickListener(listener_customGame);
    }

    View.OnClickListener listener_standardGame = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(NewGameActivity.this, GameActiviry.class);
            // TODO send Extra to GameActivity grid size, AI Difficulty/2 players
            startActivity(intent);
        }
    };

    View.OnClickListener listener_customGame = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO create new Activity with options
            //Intent intent = new Intent(NewGameActivity.this, newactivitu.class);
            //startActivity(intent);
        }
    };
}
