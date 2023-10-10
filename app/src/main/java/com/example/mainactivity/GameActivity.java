package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private int puntuacion = 0;
    private TextView puntosTextView;

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
        puntosTextView.setText("Puntuación: " + puntuacion);

        pregunta1 = new Pregunta1();
        pregunta2 = new Pregunta2();
        pregunta3 = new Pregunta3();
        pregunta4 = new Pregunta4();
        pregunta5 = new Pregunta5();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments,pregunta1).commit();

    }

    public void aumentarPuntuacion() {
        puntuacion += 3;
    }

    public void restarPuntuacion() {
        puntuacion -= 2;
    }

    private void actualizarPuntosTextView() {
        puntosTextView.setText("Puntuación: " + puntuacion);
    }

}