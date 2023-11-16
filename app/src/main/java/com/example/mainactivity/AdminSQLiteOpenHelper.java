package com.example.mainactivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase miBBDD) {
        miBBDD.execSQL("create table preguntas(codigo int primary key, pregunta text, respuesta1 text, respuesta2 text, respuesta3 text, respuesta4 text, respuesta_correcta int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor obtenerTodosLosDatos() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("preguntas", null, null, null, null, null, null);
    }
}
