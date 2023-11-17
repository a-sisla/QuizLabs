package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MusicPlayerManager.startPlaying(this, R.raw.musicainicio);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestion", null, 1);
        SQLiteDatabase BdD = admin.getWritableDatabase();

        List<Integer> codigos = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        List<String> preguntas = new ArrayList<>(Arrays.asList(
                "¿Quién ganó el mundial de fútbol en 2010?",
                "¿En qué año comenzó la Primera Guerra Mundial?",
                "¿Cuál de estos elementos químicos es líquido a temperatura ambiente?",
                "Es el órgano que bombea sangre por todo el cuerpo",
                "¿Qué celebran los cristianos el 25 de Diciembre?"
                ));
        List<String> respuestas1 = new ArrayList<>(Arrays.asList(
                "Francia",
                "1905",
                "Hierro",
                "Pulmones",
                "El nacimiento de Jesús"
        ));
        List<String> respuestas2 = new ArrayList<>(Arrays.asList(
                "Brasil",
                "1914",
                "Plutonio",
                "Estómago",
                "La muerte de Jesús"
        ));
        List<String> respuestas3 = new ArrayList<>(Arrays.asList(
                "España",
                "1921",
                "Plata",
                "Corazón",
                "La resurrección de Jesús"
        ));
        List<String> respuestas4 = new ArrayList<>(Arrays.asList(
                "Argentina",
                "1939",
                "Mercurio",
                "Cerebro",
                "La crucifixión de Jesús"
        ));
        List<Integer> respuestas_correctas = new ArrayList<>(Arrays.asList(3, 2, 4, 3, 1));

        Cursor cursor = admin.obtenerTodosLosDatos();
        int n = cursor.getCount();

        if (n == 0) {
            for (int i = 0; i < 5; i++) {
                ContentValues registro = new ContentValues();
                registro.put("codigo", codigos.get(i));
                registro.put("pregunta", preguntas.get(i));
                registro.put("respuesta1", respuestas1.get(i));
                registro.put("respuesta2", respuestas2.get(i));
                registro.put("respuesta3", respuestas3.get(i));
                registro.put("respuesta4", respuestas4.get(i));
                registro.put("respuesta_correcta", respuestas_correctas.get(i));
                BdD.insert("preguntas", null, registro);
            }
        }
        BdD.close();
    }

    public void Jugar(View v) {
        MusicPlayerManager.pulsarUnaVez(this, R.raw.botonpulsar);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent jugar = new Intent(this, GameActivity.class);
        startActivity(jugar, options.toBundle());
    }

    public void IrABaseDeDatos(View v) {
        MusicPlayerManager.pulsarUnaVez(this, R.raw.botonpulsar);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent irABaseDeDatos = new Intent(this, QuestionsActivity.class);
        startActivity(irABaseDeDatos, options.toBundle());
    }
    

}