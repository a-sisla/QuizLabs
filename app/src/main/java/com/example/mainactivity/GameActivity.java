package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private long tiempoInicio = 0;
    private long tiempoFin = 0;
    Questions questions;
    PreguntaCorrecta preguntaCorrecta;
    PreguntaIncorrecta preguntaIncorrecta;
    int numero_de_preguntas;
    List<String> preguntas = new ArrayList<>();
    List<String> respuestas1 = new ArrayList<>();
    List<String> respuestas2 = new ArrayList<>();
    List<String> respuestas3 = new ArrayList<>();
    List<String> respuestas4 = new ArrayList<>();
    List<Integer> respuestasCorrectas = new ArrayList<>();
    private int puntuacion = 0;
    private int numeroPreguntaActual = 0;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        MusicPlayerManager.startPlaying(this, R.raw.musicaquiz);

        tiempoInicio = System.currentTimeMillis();

        questions = new Questions();
        preguntaCorrecta = new PreguntaCorrecta();
        preguntaIncorrecta = new PreguntaIncorrecta();

        AdminSQLiteOpenHelperP admin1 = new AdminSQLiteOpenHelperP(this, "gestion", null, 4);
        Cursor cursor = admin1.obtenerTodosLosDatos();
        numero_de_preguntas = cursor.getCount();

        while (cursor.moveToNext()) {
            preguntas.add(cursor.getString(cursor.getColumnIndex("pregunta")));
            respuestas1.add(cursor.getString(cursor.getColumnIndex("respuesta1")));
            respuestas2.add(cursor.getString(cursor.getColumnIndex("respuesta2")));
            respuestas3.add(cursor.getString(cursor.getColumnIndex("respuesta3")));
            respuestas4.add(cursor.getString(cursor.getColumnIndex("respuesta4")));
            respuestasCorrectas.add(cursor.getInt(cursor.getColumnIndex("respuesta_correcta")));
        }

        Bundle args = new Bundle();
        args.putString("pr", preguntas.get(0));
        args.putString("re1", respuestas1.get(0));
        args.putString("re2", respuestas2.get(0));
        args.putString("re3", respuestas3.get(0));
        args.putString("re4", respuestas4.get(0));
        questions.setArguments(args);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments, questions).commit();
    }

    public void mostrarPreguntaActual() {

        if (numeroPreguntaActual < numero_de_preguntas) {
            Bundle args = new Bundle();
            args.putString("pr", preguntas.get(numeroPreguntaActual));
            args.putString("re1", respuestas1.get(numeroPreguntaActual));
            args.putString("re2", respuestas2.get(numeroPreguntaActual));
            args.putString("re3", respuestas3.get(numeroPreguntaActual));
            args.putString("re4", respuestas4.get(numeroPreguntaActual));
            questions.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments, questions).commit();
        } else {

            tiempoFin = System.currentTimeMillis();
            float tiempoTotal = tiempoFin - tiempoInicio;

            AdminSQLiteOpenHelperP admin = new AdminSQLiteOpenHelperP(this, "gestion", null, 4);
            SQLiteDatabase BdD = admin.getWritableDatabase();
            Intent intent1 = getIntent();
            String dato = intent1.getStringExtra("nombreUsuario");

            ContentValues registro = new ContentValues();

            registro.put("nombre", dato);
            registro.put("puntuacion", puntuacion);
            registro.put("tiempo", tiempoTotal/1000);
            BdD.insert("ranking", null, registro);
            BdD.close();

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra("puntos", puntuacion);
            startActivity(intent, options.toBundle()); 
        }
    }

    public void reinciar() {
        puntuacion = 0;
        numeroPreguntaActual = 0;
        tiempoInicio = System.currentTimeMillis();
    }
    public void sumarPuntuacion() {
        puntuacion += 3;
    }
    public void restarPuntuacion() {
        puntuacion -= 2;
    }

    public void avanzarPregunta() {
        numeroPreguntaActual++;
    }

    public void mostrarFragmentRespuestaCorrecta(){
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments, preguntaCorrecta).commit();
    }

    public void mostrarFragmentRespuestaIncorrecta(){
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments, preguntaIncorrecta).commit();
    }

    public void comprobarRespuesta(int respuesta) {
        if (respuestasCorrectas.get(numeroPreguntaActual) == respuesta) {
            MusicPlayerManager.pulsarUnaVez(this, R.raw.sonidocorrecto);
            this.sumarPuntuacion();
            this.mostrarFragmentRespuestaCorrecta();
        } else {
            MusicPlayerManager.pulsarUnaVez(this, R.raw.sonidoerror);
            this.restarPuntuacion();
            this.mostrarFragmentRespuestaIncorrecta();
        }
    }

}