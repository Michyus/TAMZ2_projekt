package com.example.michyus.piskvorky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Statistics extends AppCompatActivity {

    DBHelper games;

    private ListView listView_statistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                /*
                //abych vedel jake id v poli mam hledat
                int id_To_Search = arg2+1;
                Log.d("Clicked item id", " "+ arg2);
                //TODO 2: zavolat aktivitu, ktera bude zobrazovat informace o zaznamu v db a predat ji hledane id zaznamu
                Intent intent = new Intent(getApplicationContext(),DisplayContact.class);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);
                intent.putExtras(dataBundle);
                startActivity(intent);
                */
            }
        });

    }
}
