package com.arief.cruddbsqlite;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "tbl_barang";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAMA = "nama_barang";
    public static final String COLUMN_MERK = "merk_barang";
    public static final String COLUMN_ASAL = "asal_negara";
    public static final String db_name = "barang.db";
    public static final int db_version = 1;
    public static final String db_create = "create table " + TABLE_NAME + "("+
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_NAMA + " varchar(50) not null, " +
            COLUMN_MERK + " varchar(50) not null, " +
            COLUMN_ASAL + " varchar(50) not null);";

    public DbHelper(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }






}
