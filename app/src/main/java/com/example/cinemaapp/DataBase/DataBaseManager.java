package com.example.cinemaapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.cinemaapp.Register;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private Context context;
    private MyDbHelper myDbHelper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }

    public void openDB() {
        db = myDbHelper.getWritableDatabase();
    }

    public void insertToDB(String login, String password) {
        ContentValues cv = new ContentValues();
        cv.put(DataBase.LOGIN, login);
        cv.put(DataBase.PASSWORD, password);
        db.insert(DataBase.TABLE_NAME, null, cv);
    }

    public List<String> getFromDB(){
        List<String> tempList = new ArrayList<>();
        Cursor cursor = db.query(DataBase.TABLE_NAME, null, null, null,
                null, null, null);
        while (cursor.moveToNext()){
            String login = cursor.getString(cursor.getColumnIndexOrThrow(DataBase.LOGIN));
            tempList.add(login);
        }
        cursor.close();
        return tempList;
    }


    public boolean checkAccount(String tLogin, String tPassword)
    {
        boolean flag = false;
        Cursor cursor = db.query(DataBase.TABLE_NAME, null, null, null,
                null, null, null);
        while (cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndexOrThrow(DataBase.LOGIN)).equals(tLogin) &&
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBase.PASSWORD)).equals(tPassword)) {
                Toast.makeText(context, "I worked", Toast.LENGTH_LONG).show();
                flag = true;
                break;
            }
        }
        cursor.close();
        return flag;
    }

    public void closeDB(){
        myDbHelper.close();

    }
}
