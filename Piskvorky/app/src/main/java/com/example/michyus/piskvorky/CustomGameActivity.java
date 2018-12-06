package com.example.michyus.piskvorky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class CustomGameActivity extends AppCompatActivity {

    SeekBar seekBar_gameGridSize;
    TextView textView_gameGridSize;

    SeekBar seekBar_countToWin;
    TextView textView_countToWin;

    Button button_play;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_game);

        seekBar_gameGridSize = findViewById(R.id.seekBar_gameGridSize);
        textView_gameGridSize = findViewById(R.id.textView_gameGridSize);

        seekBar_countToWin = findViewById(R.id.seekBar_countToWin);
        textView_countToWin = findViewById(R.id.textView_countToWin);

        button_play = findViewById(R.id.button_play);

        seekBar_gameGridSize.setOnSeekBarChangeListener(listener_seekBar_gameGridSize);
        seekBar_countToWin.setOnSeekBarChangeListener(listener_seekBar_countToWin);

        button_play.setOnClickListener(listener_button_play);
    }

    SeekBar.OnSeekBarChangeListener listener_seekBar_gameGridSize = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            String size_str = Integer.toString(progress + 3);
            seekBar_countToWin.setMax(progress);
            textView_gameGridSize.setText(size_str);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    SeekBar.OnSeekBarChangeListener listener_seekBar_countToWin = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            String size_str = Integer.toString(progress + 3);

            textView_countToWin.setText(size_str);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    Button.OnClickListener listener_button_play = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CustomGameActivity.this, GameActivity.class);
            String size_str = (String) textView_gameGridSize.getText();
            String count_str = (String) textView_countToWin.getText();
            intent.putExtra("size", Integer.parseInt(size_str));
            intent.putExtra("count", Integer.parseInt(count_str));
            startActivity(intent);
        }
    };

}
