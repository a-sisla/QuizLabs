package com.example.mainactivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.AdminSQLiteOpenHelper;
import com.example.mainactivity.R;

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

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "gestion", null, 1);
        admin.getWritableDatabase();

        Cursor cursor = admin.obtenerDatosRanking(); //Da error al meterse por aqui
        while (cursor.moveToNext()) {
            nombres.add(cursor.getString(cursor.getColumnIndex("nombre")));
            puntuaciones.add(cursor.getString(cursor.getColumnIndex("puntuacion")));
            tiempos.add(cursor.getString(cursor.getColumnIndex("tiempo")));
        }
    }

}
