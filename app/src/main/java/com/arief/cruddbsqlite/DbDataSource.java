package com.arief.cruddbsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DbDataSource {

    private DbHelper dbHelper;
    private SQLiteDatabase database;

    private String[] allColumns = {
            DbHelper.COLUMN_ID,
            DbHelper.COLUMN_NAMA,
            DbHelper.COLUMN_MERK,
            DbHelper.COLUMN_ASAL
    };

    public DbDataSource(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Barang createBarang(String nama, String merk, String asal) {
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.COLUMN_NAMA, nama);
        cv.put(DbHelper.COLUMN_MERK, merk);
        cv.put(DbHelper.COLUMN_ASAL, asal);

        long insertId = database.insert(DbHelper.TABLE_NAME, null, cv);

        Cursor cursor = database.query(
                DbHelper.TABLE_NAME,
                allColumns,
                DbHelper.COLUMN_ID + " = ?",
                new String[] { String.valueOf(insertId) },
                null, null, null);

        Barang newBarang = null;
        if (cursor.moveToFirst()) {
            newBarang = cursorToBarang(cursor);
        }
        cursor.close();
        return newBarang;
    }

    private Barang cursorToBarang(Cursor cursor) {
        Barang barang = new Barang();
        barang.setId(cursor.getLong(0));
        barang.setNamaBarang(cursor.getString(1));
        barang.setMerkBarang(cursor.getString(2));
        barang.setAsalNegara(cursor.getString(3));
        return barang;
    }

    public ArrayList<Barang> getAllBarang() {
        ArrayList<Barang> daftarBarang = new ArrayList<>();

        Cursor cursor = database.query(
                DbHelper.TABLE_NAME,
                allColumns,
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                daftarBarang.add(cursorToBarang(cursor));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return daftarBarang;
    }

    public Barang getBarang(long id) {
        Cursor cursor = database.query(
                DbHelper.TABLE_NAME,
                allColumns,
                DbHelper.COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) },
                null, null, null);

        Barang barang = null;
        if (cursor.moveToFirst()) {
            barang = cursorToBarang(cursor);
        }
        cursor.close();
        return barang;
    }

    public void updateBarang(long id, String nama, String merk, String asal) {
        Barang b = new Barang();
        b.setId(id);
        b.setNamaBarang(nama);
        b.setMerkBarang(merk);
        b.setAsalNegara(asal);
        updateBarang(b);
    }

    public void updateBarang(Barang b) {
        ContentValues cv = new ContentValues();
        cv.put(DbHelper.COLUMN_NAMA, b.getNamaBarang());
        cv.put(DbHelper.COLUMN_MERK, b.getMerkBarang());
        cv.put(DbHelper.COLUMN_ASAL, b.getAsalNegara());

        database.update(
                DbHelper.TABLE_NAME,
                cv,
                DbHelper.COLUMN_ID + " = ?",
                new String[] { String.valueOf(b.getId()) });
    }

    public void deleteBarang(long id) {
        database.delete(
                DbHelper.TABLE_NAME,
                DbHelper.COLUMN_ID + " = ?",
                new String[] { String.valueOf(id) });
    }
}