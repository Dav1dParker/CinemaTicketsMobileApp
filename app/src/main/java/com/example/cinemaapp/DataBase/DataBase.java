package com.example.cinemaapp.DataBase;

public class DataBase {
    public static final String TABLE_NAME = "CinemaUsers";
    public static final String _ID = "_id";
    public static final String LOGIN = "logins";
    public static final String PASSWORD = "passwords";
    public static final String DB_NAME = "Cinema.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + LOGIN + " TEXT," + PASSWORD + " TEXT)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
