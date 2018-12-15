package com.example.michyus.piskvorky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName03.db";
    public static final String GAMES_TABLE_NAME = "games";
    public static final String GAMES_COLUMN_ID = "id";
    public static final String GAMES_COLUMN_WINNER = "winner";
    public static final String GAMES_COLUMN_NAME_1 = "name_1";
    public static final String GAMES_COLUMN_NAME_2 = "name_2";
    public static final String GAMES_COLUMN_MOVES = "moves";
    public static final String GAMES_COLUMN_SIZE = "size";
    public static final String GAMES_COLUMN_COUNT = "count";
    public static final String GAMES_COLUMN_AI = "ai";



    public static ArrayList<String> arrayList = new ArrayList<String>();

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE games " + "(id INTEGER PRIMARY KEY, winner TEXT, name_1 TEXT, name_2 TEXT, moves INT, size INT, count INT, ai INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS games");
        onCreate(db);
    }

    public boolean insertGame(String winner, String name_1, String name_2, int moves, int size, int count, int ai)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GAMES_COLUMN_WINNER, winner);
        contentValues.put(GAMES_COLUMN_NAME_1, name_1);
        contentValues.put(GAMES_COLUMN_NAME_2, name_2);
        contentValues.put(GAMES_COLUMN_MOVES, moves);
        contentValues.put(GAMES_COLUMN_SIZE, size);
        contentValues.put(GAMES_COLUMN_COUNT, count);
        contentValues.put(GAMES_COLUMN_AI, ai);
        db.insert(GAMES_TABLE_NAME, null, contentValues);
        return true;
    }

    //Cursor representuje vracena data
    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from games where id=" + id + "", null);
        return res;
    }


    public void setAllGames()
    {
        arrayList.clear();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from games", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            arrayList.add(res.getString(res.getColumnIndex(GAMES_COLUMN_NAME_1)) + "   X   " + res.getString(res.getColumnIndex(GAMES_COLUMN_NAME_2)) + "   |   " + res.getString(res.getColumnIndex(GAMES_COLUMN_WINNER)));
            res.moveToNext();
        }
    }

    public ArrayList<String> getAllGamesWinner()
    {
        return arrayList;
    }

    public void removeAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(GAMES_TABLE_NAME, "1", null);
    }
}
