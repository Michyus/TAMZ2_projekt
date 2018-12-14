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

        checkBox_AI = findViewById(R.id.checkBox_AI);

        games = new DBHelper(this);


        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            //ziskam ID ktere se ma editovat/zobrazit/mazat poslane z main aktivity
            int value = extras.getInt("id");
            if (value >0)
            {
                //z database vytahnu zaznam pod hledanym ID a ulozim do id_To_Update
                Cursor rs = games.getData(value);
                id_To_Update = value;
                rs.moveToFirst();

                //vytahnu zaznam se jmenem
                String name_1 = rs.getString(rs.getColumnIndex(DBHelper.GAMES_COLUMN_NAME_1));
                String name_2 = rs.getString(rs.getColumnIndex(DBHelper.GAMES_COLUMN_NAME_2));


                int moves = rs.getInt(rs.getColumnIndex(DBHelper.GAMES_COLUMN_MOVES));
                int size = rs.getInt(rs.getColumnIndex(DBHelper.GAMES_COLUMN_SIZE));
                int count = rs.getInt(rs.getColumnIndex(DBHelper.GAMES_COLUMN_COUNT));

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
