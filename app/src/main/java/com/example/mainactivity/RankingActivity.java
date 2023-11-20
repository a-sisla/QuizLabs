package com.example.mainactivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RankingActivity extends AppCompatActivity {

     List<String> nombres = new ArrayList<>();
     List<String> puntuaciones = new ArrayList<>();
     List<String> tiempos = new ArrayList<>();

    @SuppressLint("Range")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        AdminSQLiteOpenHelperR admin = new AdminSQLiteOpenHelperR(this, "gestion", null, 1);
        admin.getWritableDatabase();

        Cursor cursor = admin.obtenerTodosLosDatos(); //Da error al meterse por aqui
        while (cursor.moveToNext()) {
            nombres.add(cursor.getString(cursor.getColumnIndex("nombre")));
            puntuaciones.add(cursor.getString(cursor.getColumnIndex("puntuacion")));
            tiempos.add(cursor.getString(cursor.getColumnIndex("tiempo")));
        }
    }

}
