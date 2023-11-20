package com.example.mainactivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelperR extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelperR(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase miBBDD) {
        miBBDD.execSQL("create table ranking(id int primary key, nombre String, puntuacion int, tiempo float)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor obtenerTodosLosDatos() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("ranking", null, null, null, null, null, null);
        //"puntuacion DESC, tiempo DESC"
    }
}
