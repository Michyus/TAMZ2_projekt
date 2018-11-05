package com.example.michyus.piskvorky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button_newGame;
    Button button_unfinishedGames;
    Button button_quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_newGame = findViewById(R.id.button_newGame);
        button_unfinishedGames = findViewById(R.id.button_unfinishedGames);
        button_quit = findViewById(R.id.button_quit);

        button_newGame.setOnClickListener(listener_newGame);
        button_unfinishedGames.setOnClickListener(listener_unfinishedGames);
        button_quit.setOnClickListener(listener_quit);
    }

    View.OnClickListener listener_newGame = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "listener_newGame", Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener listener_unfinishedGames = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "listener_unfinishedGames", Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener listener_quit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "listener_quit", Toast.LENGTH_SHORT).show();
        }
    };
}
