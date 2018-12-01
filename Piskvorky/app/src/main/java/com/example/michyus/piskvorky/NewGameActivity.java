package com.example.michyus.piskvorky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewGameActivity extends AppCompatActivity {
    Button button_gameAgainstAI;
    Button button_gameAgainstHuman;
    Button button_customGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            Intent intent = new Intent(NewGameActivity.this, GameActivity.class);
            intent.putExtra("aiLevel", 0);
            startActivity(intent);
        }
    };

    View.OnClickListener listener_gameAgainstAI = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(NewGameActivity.this, GameActivity.class);
            intent.putExtra("aiLevel", 1);
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
