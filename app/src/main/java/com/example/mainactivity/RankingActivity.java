/*package com.example.mainactivity;

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

        AdminSQLiteOpenHelperP admin = new AdminSQLiteOpenHelperP(this, "gestion", null, 4);
        admin.getWritableDatabase();

        Cursor cursor = admin.obtenerTodosLosDatosRanking();
        while (cursor.moveToNext()) {
            nombres.add(cursor.getString(cursor.getColumnIndex("nombre")));
            puntuaciones.add(cursor.getString(cursor.getColumnIndex("puntuacion")));
            tiempos.add(cursor.getString(cursor.getColumnIndex("tiempo")));
        }

    }

}*/

package com.example.mainactivity;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RankingActivity extends AppCompatActivity {

    List<String> nombres = new ArrayList<>();
    List<String> puntuaciones = new ArrayList<>();
    List<String> tiempos = new ArrayList<>();

    TableLayout tableLayout;

    @SuppressLint("Range")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        AdminSQLiteOpenHelperP admin = new AdminSQLiteOpenHelperP(this, "gestion", null, 4);
        admin.getWritableDatabase();

        Cursor cursor = admin.obtenerTodosLosDatosRanking();
        while (cursor.moveToNext()) {
            nombres.add(cursor.getString(cursor.getColumnIndex("nombre")));
            puntuaciones.add(cursor.getString(cursor.getColumnIndex("puntuacion")));
            tiempos.add(cursor.getString(cursor.getColumnIndex("tiempo")));
        }

        tableLayout = findViewById(R.id.tableLayout);

        TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        TextView nameHeader = new TextView(this);
        nameHeader.setText("NOMBRE");
        nameHeader.setPadding(10, 10, 10, 10);
        nameHeader.setTextColor(getResources().getColor(android.R.color.white));
        nameHeader.setTypeface(null, Typeface.BOLD);
        headerRow.addView(nameHeader);

        TextView scoreHeader = new TextView(this);
        scoreHeader.setText("PUNTUACIÓN");
        scoreHeader.setPadding(10, 10, 10, 10);
        scoreHeader.setTextColor(getResources().getColor(android.R.color.white));
        scoreHeader.setTypeface(null, Typeface.BOLD);
        headerRow.addView(scoreHeader);

        TextView timeHeader = new TextView(this);
        timeHeader.setText("TIEMPO (s)");
        timeHeader.setPadding(10, 10, 10, 10);
        timeHeader.setTextColor(getResources().getColor(android.R.color.white));
        timeHeader.setTypeface(null, Typeface.BOLD);
        headerRow.addView(timeHeader);

        tableLayout.addView(headerRow);

        agregarDatosDesdeListas();
    }

    private void agregarDatosDesdeListas() {
        for (int i = 0; i < nombres.size(); i++) {
            TableRow dataRow = new TableRow(this);
            dataRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            agregarCelda(nombres.get(i), dataRow);
            agregarCelda(puntuaciones.get(i), dataRow);
            agregarCelda(tiempos.get(i), dataRow);

            tableLayout.addView(dataRow);
        }
    }

    private void agregarCelda(String text, TableRow row) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(10, 10, 10, 10);
        textView.setTextColor(getResources().getColor(android.R.color.white));
        textView.setGravity(Gravity.CENTER);
        row.addView(textView);
    }

    public void inicio (View v) {
        MusicPlayerManager.pulsarUnaVez(this, R.raw.botonpulsar);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, Principal.class);
        startActivity(intent, options.toBundle());
    }
}

