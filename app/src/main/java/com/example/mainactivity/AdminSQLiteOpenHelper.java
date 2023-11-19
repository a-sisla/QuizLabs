package com.example.mainactivity;

import android.content.ContentValues;
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
        miBBDD.execSQL("create table ranking(id integer primary key autoincrement, nombre text, puntuacion integer, tiempo float)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor obtenerTodosLosDatos() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("preguntas", null, null, null, null, null, null);
    }

    public void insertarDatosRanking(String nombreUsuario, int puntuacion, float tiempoTotal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues registro = new ContentValues();


        registro.put("nombre", nombreUsuario);
        System.out.println("Introducido el nombre:" +nombreUsuario);
        registro.put("puntuacion", puntuacion);
        System.out.println("Puntoss:" +puntuacion);
        registro.put("tiempo", tiempoTotal/1000);
        System.out.println("Tiempoo:" +tiempoTotal/1000);

        db.insert("ranking", null, registro);

        db.close();
    }
    public Cursor obtenerDatosRanking() {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.query("ranking", null, null, null, null, null, "puntuacion DESC, tiempo DESC");
    }




}
