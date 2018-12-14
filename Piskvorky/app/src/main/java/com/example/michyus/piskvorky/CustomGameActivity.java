package com.example.michyus.piskvorky;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CustomGameActivity extends AppCompatActivity {

    SeekBar seekBar_gameGridSize;
    TextView textView_gameGridSize;

    SeekBar seekBar_countToWin;
    TextView textView_countToWin;

    EditText editText_name_1;
    EditText editText_name_2;

    CheckBox checkBox_AI;
    Spinner spinner_AI;

    Button button_play;

    Context context;

    SharedPreferences.Editor editor;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_game);

        context = this.getApplicationContext();

        seekBar_gameGridSize = findViewById(R.id.seekBar_gameGridSize);
        textView_gameGridSize = findViewById(R.id.textView_gameGridSize);

        seekBar_countToWin = findViewById(R.id.seekBar_countToWin);
        textView_countToWin = findViewById(R.id.textView_countToWin);

        editText_name_1 = findViewById(R.id.editText_name_1);
        editText_name_2 = findViewById(R.id.editText_name_2);

        checkBox_AI = findViewById(R.id.checkBox_AI);
        spinner_AI = findViewById(R.id.spinner_AI);

        button_play = findViewById(R.id.button_play);

        seekBar_gameGridSize.setOnSeekBarChangeListener(listener_seekBar_gameGridSize);
        seekBar_countToWin.setOnSeekBarChangeListener(listener_seekBar_countToWin);

        checkBox_AI.setOnClickListener(listener_chekBox_AI);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.AI_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_AI.setAdapter(adapter);

        button_play.setOnClickListener(listener_button_play);

        spinner_AI.setEnabled(checkBox_AI.isChecked());
        editText_name_2.setEnabled(!checkBox_AI.isChecked());


        //TODO sharePreferences
        editor = getSharedPreferences("sharedPreferences", MODE_PRIVATE).edit();
        prefs = getSharedPreferences("sharedPreferences", MODE_PRIVATE);

        editText_name_1.setText(prefs.getString("name_1", "Name_1"));
        editText_name_2.setText(prefs.getString("name_2", "Name_2"));
    }

    SeekBar.OnSeekBarChangeListener listener_seekBar_gameGridSize = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            String size_str = Integer.toString(progress + 3);
            seekBar_countToWin.setMax(progress + 3);
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
            String size_str = Integer.toString(progress);
            textView_countToWin.setText(size_str);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    CheckBox.OnClickListener listener_chekBox_AI = new CheckBox.OnClickListener(){
        @Override
        public void onClick(View v) {
            spinner_AI.setEnabled(checkBox_AI.isChecked());
            editText_name_2.setEnabled(!checkBox_AI.isChecked());
        }
    };

    Button.OnClickListener listener_button_play = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CustomGameActivity.this, GameActivity.class);
            String size_str = (String) textView_gameGridSize.getText();
            String count_str = (String) textView_countToWin.getText();

            editor.putString("size", size_str);
            editor.putString("count", count_str);
            editor.putString("name_1", editText_name_1.getText().toString());
            editor.putString("name_2", editText_name_2.getText().toString());
            editor.apply();

            intent.putExtra("size", Integer.parseInt(size_str));
            intent.putExtra("count", Integer.parseInt(count_str));
            intent.putExtra("name_1", editText_name_1.getText().toString());
            intent.putExtra("name_2", editText_name_2.getText().toString());
            if(checkBox_AI.isChecked()){
                intent.putExtra("aiLevel", spinner_AI.getSelectedItemPosition()+1);
                intent.putExtra("name_2", spinner_AI.getSelectedItem().toString());
            }

            Toast.makeText(context,Integer.toString(spinner_AI.getSelectedItemPosition()), Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
    };

}
