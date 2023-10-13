package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private int puntuacion = 0;
    private TextView puntosTextView;

    private int numeroPreguntaActual = 1;

    Pregunta1 pregunta1;
    Pregunta2 pregunta2;
    Pregunta3 pregunta3;
    Pregunta4 pregunta4;
    Pregunta5 pregunta5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        puntosTextView = findViewById(R.id.puntosTextView);
        puntosTextView.setText("Puntos: " + puntuacion);

        /*
        pregunta1 = new Pregunta1();
        pregunta2 = new Pregunta2();
        pregunta3 = new Pregunta3();
        pregunta4 = new Pregunta4();
        pregunta5 = new Pregunta5();
        */
        //getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments,pregunta1).commit();
        mostrarPreguntaActual();

    }

    private void mostrarPreguntaActual() {
        Fragment preguntaFragment = null;

        // Determinar cuál es la pregunta actual y cargar el fragmento correspondiente
        switch (numeroPreguntaActual) {
            case 1:
                preguntaFragment = new Pregunta1();
                break;
            case 2:
                preguntaFragment = new Pregunta2();
                break;
            case 3:
                preguntaFragment = new Pregunta3();
                break;
            case 4:
                preguntaFragment = new Pregunta4();
                break;
            case 5:
                preguntaFragment = new Pregunta5();
            default:
                //Mostrar fragment "Fin del juego, has conseguido x puntos" Pulsar botón volver menu principal
                break;
        }

        if (preguntaFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments, preguntaFragment).commit();
        }
    }

    public void respuestaCorrecta(){
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments2, new fragment_preguntaCorrecta()).commit();
    }

    //Se le llamará cuando en el fragment de respuesta correcta/incorrecta se pulse al botón de siguiente pregunta
    public void avanzarSiguientePregunta() {
        numeroPreguntaActual++;
        mostrarPreguntaActual();
    }

    public void aumentarPuntuacion() {
        puntuacion += 3;
        actualizarPuntosTextView();
    }

    public void restarPuntuacion() {
        puntuacion -= 2;
    }

    private void actualizarPuntosTextView() {
        puntosTextView.setText("Puntuación: " + puntuacion);
    }

}