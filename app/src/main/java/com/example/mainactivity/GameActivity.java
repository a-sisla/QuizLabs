package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    Pregunta1 pregunta1;
    Pregunta2 pregunta2;
    Pregunta3 pregunta3;
    Pregunta4 pregunta4;
    Pregunta5 pregunta5;
    PreguntaCorrecta preguntaCorrecta;
    PreguntaIncorrecta preguntaIncorrecta;

    private int puntuacion = 0;
    private int numeroPreguntaActual = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pregunta1 = new Pregunta1();
        pregunta2 = new Pregunta2();
        pregunta3 = new Pregunta3();
        pregunta4 = new Pregunta4();
        pregunta5 = new Pregunta5();
        preguntaCorrecta = new PreguntaCorrecta();
        preguntaIncorrecta = new PreguntaIncorrecta();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, pregunta1).commit();
    }

    public void mostrarPreguntaActual() {
        Fragment preguntaFragment = null;

        // Determinar cuál es la pregunta actual y cargar el fragment correspondiente
        switch (numeroPreguntaActual) {
            case 1:
                preguntaFragment = pregunta1;
                break;
            case 2:
                preguntaFragment = pregunta2;
                break;
            case 3:
                preguntaFragment = pregunta3;
                break;
            case 4:
                preguntaFragment = pregunta4;
                break;
            case 5:
                preguntaFragment = pregunta5;
        }

        if (preguntaFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments, preguntaFragment).commit();
        } else {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra("puntos", puntuacion);
            startActivity(intent, options.toBundle());
        }
    }

    public void reinciar() {
        puntuacion = 0;
        numeroPreguntaActual = 1;
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
}