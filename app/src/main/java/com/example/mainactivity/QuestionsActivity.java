package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class QuestionsActivity extends AppCompatActivity {

    private EditText et_codigo, et_pregunta, et_respuesta1, et_respuesta2, et_respuesta3, et_respuesta4, et_respuesta_correcta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        et_codigo = (EditText) findViewById(R.id.txt_codigo);
        et_pregunta = (EditText) findViewById(R.id.txt_pregunta);
        et_respuesta1 = (EditText) findViewById(R.id.txt_respuesta1);
        et_respuesta2 = (EditText) findViewById(R.id.txt_respuesta2);
        et_respuesta3 = (EditText) findViewById(R.id.txt_respuesta3);
        et_respuesta4 = (EditText) findViewById(R.id.txt_respuesta4);
        et_respuesta_correcta = (EditText) findViewById(R.id.txt_respuestaCorrecta);
    }

    public void Registrar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestion", null, 1);
        SQLiteDatabase BdD = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String pregunta = et_pregunta.getText().toString();
        String respuesta1 = et_respuesta1.getText().toString();
        String respuesta2 = et_respuesta2.getText().toString();
        String respuesta3 = et_respuesta3.getText().toString();
        String respuesta4 = et_respuesta4.getText().toString();
        String respuesta_correcta = et_respuesta_correcta.getText().toString();

        if(!codigo.isEmpty() && !pregunta.isEmpty() && !respuesta1.isEmpty() && !respuesta2.isEmpty() && !respuesta3.isEmpty() && !respuesta4.isEmpty() && !respuesta_correcta.isEmpty() && Integer.parseInt(respuesta_correcta) >= 1 && Integer.parseInt(respuesta_correcta) <= 4) {
            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo);
            registro.put("pregunta", pregunta);
            registro.put("respuesta1", respuesta1);
            registro.put("respuesta2", respuesta2);
            registro.put("respuesta3", respuesta3);
            registro.put("respuesta4", respuesta4);
            registro.put("respuesta_correcta", respuesta_correcta);

            BdD.insert("preguntas", null, registro);
            BdD.close();

            et_codigo.setText("");
            et_pregunta.setText("");
            et_respuesta1.setText("");
            et_respuesta2.setText("");
            et_respuesta3.setText("");
            et_respuesta4.setText("");
            et_respuesta_correcta.setText("");

            Toast.makeText(this, "Pregunta registrada", Toast.LENGTH_SHORT).show();
        } else if (Integer.parseInt(respuesta_correcta) < 1 && Integer.parseInt(respuesta_correcta) > 4) {
            Toast.makeText(this, "La respuesta correcta debe corresponder a un número en el rango [1,4]", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
        }
    }

    public void Buscar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestion", null, 1);
        SQLiteDatabase BdD = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if (!codigo.isEmpty()) {
            Cursor fila = BdD.rawQuery("select pregunta, respuesta1, respuesta2, respuesta3, respuesta4 from preguntas where codigo =" + codigo, null);

            if(fila.moveToFirst()) {
                et_pregunta.setText(fila.getString(0));
                et_respuesta1.setText(fila.getString(1));
                et_respuesta2.setText(fila.getString(2));
                et_respuesta3.setText(fila.getString(3));
                et_respuesta4.setText(fila.getString(4));
                BdD.close();
            } else {
                Toast.makeText(this, "La pregunta no existe", Toast.LENGTH_SHORT).show();
                BdD.close();
            }
        } else {
            Toast.makeText(this, "Debe introducir el código de la pregunta", Toast.LENGTH_SHORT).show();
        }
    }

    public void Eliminar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestion", null, 1);
        SQLiteDatabase BdD = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()) {
            int cantidad = BdD.delete("preguntas", "codigo=" + codigo, null);
            BdD.close();

            et_codigo.setText("");
            et_pregunta.setText("");
            et_respuesta1.setText("");
            et_respuesta2.setText("");
            et_respuesta3.setText("");
            et_respuesta4.setText("");
            et_respuesta_correcta.setText("");

            if(cantidad == 1) {
                Toast.makeText(this, "La pregunta ha sido eliminada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "La pregunta no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debe introducir el código de la pregunta", Toast.LENGTH_SHORT).show();
        }
    }

    public void Modificar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestion", null, 1);
        SQLiteDatabase BdD = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String pregunta = et_pregunta.getText().toString();
        String respuesta1 = et_respuesta1.getText().toString();
        String respuesta2 = et_respuesta2.getText().toString();
        String respuesta3 = et_respuesta3.getText().toString();
        String respuesta4 = et_respuesta4.getText().toString();
        String respuesta_correcta = et_respuesta_correcta.getText().toString();

        if(!codigo.isEmpty() && !pregunta.isEmpty() && !respuesta1.isEmpty() && !respuesta2.isEmpty() && !respuesta3.isEmpty() && !respuesta4.isEmpty() && !respuesta_correcta.isEmpty() && Integer.parseInt(respuesta_correcta) >= 1 && Integer.parseInt(respuesta_correcta) <= 4) {
            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo);
            registro.put("pregunta", pregunta);
            registro.put("respuesta1", respuesta1);
            registro.put("respuesta2", respuesta2);
            registro.put("respuesta3", respuesta3);
            registro.put("respuesta4", respuesta4);
            registro.put("respuesta_correcta", respuesta_correcta);

            int cantidad = BdD.update("preguntas", registro, "codigo=" + codigo, null);
            BdD.close();

            if(cantidad == 1) {
                Toast.makeText(this, "La pregunta ha sido modificada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "La pregunta no existe", Toast.LENGTH_SHORT).show();
            }
        } else if (Integer.parseInt(respuesta_correcta) < 1 && Integer.parseInt(respuesta_correcta) > 4) {
            Toast.makeText(this, "La respuesta correcta debe corresponder a un número en el rango [1,4]", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
        }
    }
}