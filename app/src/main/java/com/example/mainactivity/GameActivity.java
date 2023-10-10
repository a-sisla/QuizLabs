package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private int puntuacion = 0;
    private TextView puntosTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        puntosTextView = findViewById(R.id.puntosTextView);
        puntosTextView.setText("Puntuación: " + puntuacion);
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