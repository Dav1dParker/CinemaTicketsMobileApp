package com.example.cinemaapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "DataStorage.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        // you can use an alternate constructor to specify a database location
        // (such as a folder on the sd card)
        // you must ensure that this folder is available and you have permission
        // to write to it
        //super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);

    }

    private Context context;
    private SQLiteDatabase db;
    private SQLiteQueryBuilder qb;
    private String[] sqlSelect;
    private String sqlTables;


    public void OpenDBfilms() {
        db = getReadableDatabase();
        qb = new SQLiteQueryBuilder();

        sqlSelect = new String[]{"ID", "Name", "Description", "Age", "Image"};
        sqlTables = "Films";
        qb.setTables(sqlTables);
    }

    public void CloseDBfilms() {
        db.close();
    }

    public void OpenDBUsers() {
        db = getReadableDatabase();
        qb = new SQLiteQueryBuilder();

        sqlSelect = new String[]{"_id", "logins", "passwords"};
        sqlTables = "CinemaUsers";
        qb.setTables(sqlTables);
    }

    public void CloseDBUsers() {
        db.close();
    }


    public Cursor films() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ID", "Name", "Description", "Age", "Image"};
        String sqlTables = "Films";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null,
                null, null, null);

        c.moveToFirst();

        while (c.moveToNext()) {

            System.out.println(c.getString(c.getColumnIndexOrThrow("Name")));
            //Toast.makeText(context, "I worked", Toast.LENGTH_LONG).show();

        }
        c.close();
        return c;
    }

    public boolean checkAccount(String tLogin, String tPassword) {
        boolean flag = false;
        Cursor cursor = qb.query(db, sqlSelect, null, null,
                null, null, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndexOrThrow("logins")).equals(tLogin) &&
                    cursor.getString(cursor.getColumnIndexOrThrow("passwords")).equals(tPassword)) {
                //Toast.makeText(context, "I worked", Toast.LENGTH_LONG).show();
                flag = true;
                break;
            }
        }
        cursor.close();
        return flag;
    }


    public void insertToUserDB(String login, String password) {
        ContentValues cv = new ContentValues();
        cv.put("logins", login);
        cv.put("passwords", password);
        db.insert("CinemaUsers", null, cv);
    }

    public List<String> getFilmInfo(int id) {
        List<String> tempList = new ArrayList<>();
        Cursor cursor = qb.query(db, sqlSelect, null, null,
                null, null, null);
        while (cursor.moveToNext()) {
            if (id == cursor.getInt(cursor.getColumnIndexOrThrow("ID"))) {
                tempList.add(cursor.getString(cursor.getColumnIndexOrThrow("Name")));
                tempList.add(cursor.getString(cursor.getColumnIndexOrThrow("Description")));
                tempList.add(cursor.getString(cursor.getColumnIndexOrThrow("Age")));
                tempList.add(cursor.getString(cursor.getColumnIndexOrThrow("Image")));
            }
        }
        cursor.close();
        return tempList;
    }

    public int getDatabaseSize()
    {
        Cursor c = qb.query(db, sqlSelect, null, null,
                null, null, null);

        c.moveToLast();
        return c.getInt(c.getColumnIndexOrThrow("ID"));
    }

    public void killDB() {
        context.deleteDatabase("DataStorage.db");
    }


    /*
    public Cursor getEmployees() {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String [] sqlSelect = {"0 _id", "FirstName", "LastName"};
        String sqlTables = "Employees";

        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, null, null,
                null, null, null);

        c.moveToFirst();
        return c;

    }
    */

}