package com.example.michyus.piskvorky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameActiviry extends AppCompatActivity {
    TextView textView_moveOf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activiry);

        textView_moveOf = findViewById(R.id.textView_moveOf);
    }
}
