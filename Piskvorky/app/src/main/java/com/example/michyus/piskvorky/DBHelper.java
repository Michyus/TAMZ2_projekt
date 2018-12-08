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


    public static ArrayList<String> arrayList = new ArrayList<String>();

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table games " + "(id integer primary key, name text)");
        db.execSQL("CREATE TABLE games " + "(id INTEGER PRIMARY KEY, winner TEXT, name_1 TEXT, name_2 TEXT, moves INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS games");
        onCreate(db);
    }

    public boolean insertGame(String winner, String name_1, String name_2, int moves)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GAMES_COLUMN_WINNER, winner);
        db.insert(GAMES_TABLE_NAME, null, contentValues);
        return true;
    }

    //Cursor representuje vracena data
    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from games where id=" + id + "", null);
        //Cursor res =  db.rawQuery( "select * from games LIMIT 1 OFFSET "+id+"", null );
        return res;
    }

    public boolean updateGame (Integer id, String name, int cost, int type)
    {
        //TODO update zaznam
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("winner", name);
        db.update(GAMES_TABLE_NAME, contentValues,"id="+id,null);

        return true;
    }

    public void setAllGames()
    {
        arrayList.clear();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from games", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            arrayList.add(res.getString(res.getColumnIndex(GAMES_COLUMN_WINNER)));
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
