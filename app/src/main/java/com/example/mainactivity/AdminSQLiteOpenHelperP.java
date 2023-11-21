package com.example.mainactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelperP extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelperP(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase miBBDD) {
        miBBDD.execSQL("create table preguntas(codigo int primary key, pregunta text, respuesta1 text, respuesta2 text, respuesta3 text, respuesta4 text, respuesta_correcta int)");
        miBBDD.execSQL("create table ranking(nombre String, puntuacion int, tiempo float)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS preguntas");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ranking");
        onCreate(sqLiteDatabase);
    }

    public Cursor obtenerTodosLosDatos() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("preguntas", null, null, null, null, null, null);
    }

    public Cursor obtenerTodosLosDatosRanking() {
        SQLiteDatabase db = this.getReadableDatabase();
        String orderBy = "puntuacion DESC, tiempo ASC";
        return db.query("ranking", null, null, null, null, null, orderBy);
    }
}
