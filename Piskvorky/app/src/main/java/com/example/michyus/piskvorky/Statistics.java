package com.example.michyus.piskvorky;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Statistics extends AppCompatActivity {

    DBHelper games;

    private ListView listView_statistics;

    TextView textView_numberOfGames;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        SharedPreferences prefs = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        boolean darkTheme = prefs.getBoolean("theme", false);
        if (darkTheme){
            setTheme(R.style.Theme_AppCompat_NoActionBar);
        }
        else {
            setTheme(R.style.Theme_AppCompat_DayNight_NoActionBar);
        }

        setContentView(R.layout.activity_statistics);

        games = new DBHelper(this);
        games.setAllGames();

        ArrayList arrayList = games.getAllGamesWinner();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        listView_statistics = findViewById(R.id.listView_statistics);
        listView_statistics.setAdapter(arrayAdapter);


        listView_statistics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                MediaPlayer mp;
                mp = MediaPlayer.create(context, R.raw.click);
                mp.start();

                int id_To_Search = arg2+1;
                Intent intent = new Intent(getApplicationContext(),StatisticsDetail.class);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);
                intent.putExtras(dataBundle);
                startActivity(intent);

            }
        });

        textView_numberOfGames = findViewById(R.id.textView_numberOfGames);
        textView_numberOfGames.setText(String.valueOf(arrayList.size()));

    }
}
