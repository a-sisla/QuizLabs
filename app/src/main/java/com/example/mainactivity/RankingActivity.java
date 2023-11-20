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
import android.database.Cursor;
import android.os.Bundle;
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

        // Obtén una referencia al TableLayout
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Crea filas y celdas dinámicamente
        for (int i = 0; i < Math.max(nombres.size(), Math.max(puntuaciones.size(), tiempos.size())); i++) {
            TableRow row = new TableRow(this);

            // Crea celdas y configura el texto
            TextView textView1 = new TextView(this);
            if (i < nombres.size()) {
                textView1.setText(nombres.get(i));
            }
            row.addView(textView1);

            TextView textView2 = new TextView(this);
            if (i < puntuaciones.size()) {
                textView2.setText(puntuaciones.get(i));
            }
            row.addView(textView2);

            TextView textView3 = new TextView(this);
            if (i < tiempos.size()) {
                textView3.setText(tiempos.get(i));
            }
            row.addView(textView3);

            // Agrega la fila al TableLayout
            tableLayout.addView(row);
        }
    }
}

