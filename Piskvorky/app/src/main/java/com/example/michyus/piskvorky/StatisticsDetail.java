package com.example.michyus.piskvorky;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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

    TextView textView_winner;

    CheckBox checkBox_AI;

    private DBHelper games;
    private int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_detail);

        seekBar_gameGridSize = findViewById(R.id.seekBar_gameGridSize);
        textView_gameGridSize = findViewById(R.id.textView_gameGridSize);

        seekBar_countToWin = findViewById(R.id.seekBar_countToWin);
        textView_countToWin = findViewById(R.id.textView_countToWin);

        editText_name_1 = findViewById(R.id.editText_name_1);
        editText_name_2 = findViewById(R.id.editText_name_2);

        textView_winner = findViewById(R.id.textView_winner);

        checkBox_AI = findViewById(R.id.checkBox_AI);

        games = new DBHelper(this);


        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int value = extras.getInt("id");
            if (value >0)
            {
                Cursor rs = games.getData(value);
                id_To_Update = value;
                rs.moveToFirst();

                String name_1 = rs.getString(rs.getColumnIndex(DBHelper.GAMES_COLUMN_NAME_1));
                String name_2 = rs.getString(rs.getColumnIndex(DBHelper.GAMES_COLUMN_NAME_2));

                String winner = rs.getString(rs.getColumnIndex(DBHelper.GAMES_COLUMN_WINNER));


                int moves = rs.getInt(rs.getColumnIndex(DBHelper.GAMES_COLUMN_MOVES));
                int size = rs.getInt(rs.getColumnIndex(DBHelper.GAMES_COLUMN_SIZE));
                int count = rs.getInt(rs.getColumnIndex(DBHelper.GAMES_COLUMN_COUNT));
                int ai = rs.getInt(rs.getColumnIndex(DBHelper.GAMES_COLUMN_AI));

                if (!rs.isClosed())
                {
                    rs.close();
                }

                editText_name_1.setText(name_1);
                editText_name_1.setEnabled(false);
                editText_name_1.setFocusable(false);
                editText_name_1.setClickable(false);


                editText_name_2.setText(name_2);
                editText_name_2.setEnabled(false);
                editText_name_2.setFocusable(false);
                editText_name_2.setClickable(false);

                if (ai > 0){
                    checkBox_AI.setChecked(true);
                }
                else {
                    checkBox_AI.setChecked(false);
                }
                checkBox_AI.setEnabled(false);

                textView_winner.setText(winner + " " + this.getResources().getString(R.string.won));

                seekBar_gameGridSize.setProgress(size);
                textView_gameGridSize.setText(Integer.toString(size));

                seekBar_countToWin.setMax(size + 3);
                seekBar_countToWin.setProgress(count);
                textView_countToWin.setText(Integer.toString(count));

                seekBar_gameGridSize.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });

                seekBar_countToWin.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });

            }
        }


    }
}
