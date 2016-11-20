package com.example.emad.splashscreen;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;

/**
 * Created by Ahsan on 10-09-2016.
 */
public class MyDBHandler {

    Context context;
    private SQLiteDatabase db;
    private Cursor c;

    public MyDBHandler(Context c) {
        context = c;
        db = context.openOrCreateDatabase("FoundITDB", Context.MODE_PRIVATE, null);
        createUserTable();
    }


    public void createUserTable() {
        //  db.execSQL("Drop TABLE IF EXISTS User");
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                "User(username VARCHAR, email VARCHAR, password VARCHAR);" +
                "");

    }



    public void setUserData(String UserName, String email, String Password) {

        db.execSQL("INSERT INTO User " +
                "VALUES('" +
                UserName + "'," +
                "'" + email + "'," +
                "'" + Password + "'" +
                ");");
    }



    public String getName() {
        String name = null;
        c = db.rawQuery("SELECT name_person FROM User", null);
        while (c.moveToNext()) {
            name = c.getString(0).toString();
        }
        return name;
    }

    public boolean UserExists(String Username,String PassWord) {
        c = db.rawQuery("SELECT * FROM User where username = \"" + Username + "\"" + " AND password = \"" + PassWord + "\"" + ";", null);
        return c.getCount() > 0;
    }
}