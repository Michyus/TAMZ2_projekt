package com.example.michyus.piskvorky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class StatisticsDetail extends AppCompatActivity {

    SeekBar seekBar_gameGridSize;
    TextView textView_gameGridSize;

    SeekBar seekBar_countToWin;
    TextView textView_countToWin;

    EditText editText_name_1;
    EditText editText_name_2;

    CheckBox checkBox_AI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_detail);
    }
}
