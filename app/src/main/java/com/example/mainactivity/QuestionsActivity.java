package com.example.mainactivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class QuestionsActivity extends AppCompatActivity {

    private EditText et_codigo, et_pregunta, et_respuesta1, et_respuesta2, et_respuesta3, et_respuesta4, et_respuesta_correcta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_launcher);
        }

        et_codigo = (EditText) findViewById(R.id.txt_codigo);
        et_pregunta = (EditText) findViewById(R.id.txt_pregunta);
        et_respuesta1 = (EditText) findViewById(R.id.txt_respuesta1);
        et_respuesta2 = (EditText) findViewById(R.id.txt_respuesta2);
        et_respuesta3 = (EditText) findViewById(R.id.txt_respuesta3);
        et_respuesta4 = (EditText) findViewById(R.id.txt_respuesta4);
        et_respuesta_correcta = (EditText) findViewById(R.id.txt_respuestaCorrecta);

    }

    public void Registrar(View v) {

        AdminSQLiteOpenHelperP admin = new AdminSQLiteOpenHelperP(this, "gestion", null, 4);
        SQLiteDatabase BdD = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String pregunta = et_pregunta.getText().toString();
        String respuesta1 = et_respuesta1.getText().toString();
        String respuesta2 = et_respuesta2.getText().toString();
        String respuesta3 = et_respuesta3.getText().toString();
        String respuesta4 = et_respuesta4.getText().toString();
        String respuesta_correcta = et_respuesta_correcta.getText().toString();

        // Consulta para verificar si ya existe un registro con el mismo código
        Cursor cursor = BdD.rawQuery("SELECT * FROM preguntas WHERE codigo = ?", new String[]{codigo});

        if (cursor.getCount() > 0) {
            MusicPlayerManager.pulsarUnaVez(this, R.raw.sonidoerror2);
            // Ya existe un registro con el mismo código
            Toast.makeText(this, "Ya existe una pregunta con ese código", Toast.LENGTH_SHORT).show();
        } else {
            // No hay registros con el mismo código
            try {

                int codigoInt = Integer.parseInt(codigo);
                int respuestaCorrectaInt = Integer.parseInt(respuesta_correcta);

                if (respuestaCorrectaInt >= 1 && respuestaCorrectaInt <= 4) {
                    ContentValues registro = new ContentValues();
                    registro.put("codigo", codigoInt);
                    registro.put("pregunta", pregunta);
                    registro.put("respuesta1", respuesta1);
                    registro.put("respuesta2", respuesta2);
                    registro.put("respuesta3", respuesta3);
                    registro.put("respuesta4", respuesta4);
                    registro.put("respuesta_correcta", respuestaCorrectaInt);

                    BdD.insert("preguntas", null, registro);
                    BdD.close();

                    et_codigo.setText("");
                    et_pregunta.setText("");
                    et_respuesta1.setText("");
                    et_respuesta2.setText("");
                    et_respuesta3.setText("");
                    et_respuesta4.setText("");
                    et_respuesta_correcta.setText("");

                    MusicPlayerManager.pulsarUnaVez(this, R.raw.botonpulsar);
                    Toast.makeText(this, "Pregunta registrada", Toast.LENGTH_SHORT).show();
                } else {
                    MusicPlayerManager.pulsarUnaVez(this, R.raw.sonidoerror2);
                    Toast.makeText(this, "La respuesta correcta debe estar en el rango [1, 4]", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                MusicPlayerManager.pulsarUnaVez(this, R.raw.sonidoerror2);
                Toast.makeText(this, "El código y la respuesta correcta deben ser números válidos", Toast.LENGTH_SHORT).show();
            }
        }

        cursor.close();
    }

    public void Buscar(View v) {

        AdminSQLiteOpenHelperP admin = new AdminSQLiteOpenHelperP(this, "gestion", null, 4);
        SQLiteDatabase BdD = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if (!codigo.isEmpty()) {
            try {
                Cursor fila = BdD.rawQuery("SELECT pregunta, respuesta1, respuesta2, respuesta3, respuesta4, respuesta_correcta FROM preguntas WHERE codigo = ?", new String[]{codigo});

                if (fila.moveToFirst()) {
                    MusicPlayerManager.pulsarUnaVez(this, R.raw.botonpulsar);
                    int indexPregunta = fila.getColumnIndex("pregunta");
                    int indexRespuesta1 = fila.getColumnIndex("respuesta1");
                    int indexRespuesta2 = fila.getColumnIndex("respuesta2");
                    int indexRespuesta3 = fila.getColumnIndex("respuesta3");
                    int indexRespuesta4 = fila.getColumnIndex("respuesta4");
                    int indexRespuestaCorrecta = fila.getColumnIndex("respuesta_correcta");

                    et_pregunta.setText(fila.getString(indexPregunta));
                    et_respuesta1.setText(fila.getString(indexRespuesta1));
                    et_respuesta2.setText(fila.getString(indexRespuesta2));
                    et_respuesta3.setText(fila.getString(indexRespuesta3));
                    et_respuesta4.setText(fila.getString(indexRespuesta4));
                    et_respuesta_correcta.setText(fila.getString(indexRespuestaCorrecta));
                    BdD.close();
                } else {
                    MusicPlayerManager.pulsarUnaVez(this, R.raw.sonidoerror2);
                    Toast.makeText(this, "La pregunta no existe", Toast.LENGTH_SHORT).show();
                    BdD.close();
                }
            } catch (NumberFormatException e) {
                MusicPlayerManager.pulsarUnaVez(this, R.raw.sonidoerror2);
                Toast.makeText(this, "Ingrese un código de pregunta válido", Toast.LENGTH_SHORT).show();
            }
        } else {
            MusicPlayerManager.pulsarUnaVez(this, R.raw.sonidoerror2);
            Toast.makeText(this, "Debe introducir el código de la pregunta", Toast.LENGTH_SHORT).show();
        }
    }

    public void Eliminar(View v) {

        AdminSQLiteOpenHelperP admin = new AdminSQLiteOpenHelperP(this, "gestion", null, 4);
        SQLiteDatabase BdD = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()) {
            int codigoInt = Integer.parseInt(codigo);
            int cantidad = BdD.delete("preguntas", "codigo=" + codigoInt, null);
            BdD.close();

            et_codigo.setText("");
            et_pregunta.setText("");
            et_respuesta1.setText("");
            et_respuesta2.setText("");
            et_respuesta3.setText("");
            et_respuesta4.setText("");
            et_respuesta_correcta.setText("");

            if(cantidad == 1) {
                MusicPlayerManager.pulsarUnaVez(this, R.raw.botonpulsar);
                Toast.makeText(this, "La pregunta ha sido eliminada", Toast.LENGTH_SHORT).show();
            } else {
                MusicPlayerManager.pulsarUnaVez(this, R.raw.sonidoerror2);
                Toast.makeText(this, "La pregunta no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            MusicPlayerManager.pulsarUnaVez(this, R.raw.sonidoerror2);
            Toast.makeText(this, "Debe introducir el código de la pregunta", Toast.LENGTH_SHORT).show();
        }
    }


    public void Modificar(View v) {

        String codigo = et_codigo.getText().toString();
        String pregunta = et_pregunta.getText().toString();
        String respuesta1 = et_respuesta1.getText().toString();
        String respuesta2 = et_respuesta2.getText().toString();
        String respuesta3 = et_respuesta3.getText().toString();
        String respuesta4 = et_respuesta4.getText().toString();
        String respuesta_correcta = et_respuesta_correcta.getText().toString();

        AdminSQLiteOpenHelperP admin = new AdminSQLiteOpenHelperP(this, "gestion", null, 4);
        SQLiteDatabase BdD = admin.getWritableDatabase();

        if (codigo.isEmpty() || pregunta.isEmpty() || respuesta1.isEmpty() || respuesta2.isEmpty() || respuesta3.isEmpty() || respuesta4.isEmpty() || respuesta_correcta.isEmpty()) {
            MusicPlayerManager.pulsarUnaVez(this, R.raw.sonidoerror2);
            Toast.makeText(this, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
        } else if (Integer.parseInt(respuesta_correcta) < 1 || Integer.parseInt(respuesta_correcta) > 4){
            MusicPlayerManager.pulsarUnaVez(this, R.raw.sonidoerror2);
            Toast.makeText(this, "La respuesta correcta debe corresponder a un número en el rango [1,4]", Toast.LENGTH_SHORT).show();
        } else {
            int codigoInt = Integer.parseInt(codigo);
            int respuestaCorrectaInt = Integer.parseInt(respuesta_correcta);

            ContentValues registro = new ContentValues();

            registro.put("codigo", codigoInt);
            registro.put("pregunta", pregunta);
            registro.put("respuesta1", respuesta1);
            registro.put("respuesta2", respuesta2);
            registro.put("respuesta3", respuesta3);
            registro.put("respuesta4", respuesta4);
            registro.put("respuesta_correcta", respuestaCorrectaInt);

            int cantidad = BdD.update("preguntas", registro, "codigo=" + codigoInt, null);
            BdD.close();

            if (cantidad == 1) {
                MusicPlayerManager.pulsarUnaVez(this, R.raw.botonpulsar);
                Toast.makeText(this, "La pregunta ha sido modificada", Toast.LENGTH_SHORT).show();
            } else {
                MusicPlayerManager.pulsarUnaVez(this, R.raw.sonidoerror2);
                Toast.makeText(this, "La pregunta no existe", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void inicio (View v) {
        MusicPlayerManager.pulsarUnaVez(this, R.raw.botonpulsar);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, Principal.class);
        startActivity(intent, options.toBundle());
    }
}