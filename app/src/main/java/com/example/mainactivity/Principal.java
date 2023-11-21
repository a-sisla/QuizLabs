package com.example.mainactivity;

import android.app.ActivityOptions;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mainactivity.databinding.ActivityPrincipalBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Principal extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityPrincipalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarPrincipal.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.rankingActivity, R.id.questionsActivity)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        MusicPlayerManager.startPlaying(this, R.raw.musicainicio);

        AdminSQLiteOpenHelperP admin = new AdminSQLiteOpenHelperP(this, "gestion", null, 4);
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

        if(n == 0) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_principal);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void Jugar(View v) {
        MusicPlayerManager.pulsarUnaVez(this, R.raw.botonpulsar);
        EditText editTextNombreUsuario = findViewById(R.id.username);
        String nombreUsuario = editTextNombreUsuario.getText().toString();

        if (!nombreUsuario.isEmpty()) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
            Intent jugar = new Intent(this, GameActivity.class);
            jugar.putExtra("nombreUsuario", nombreUsuario);
            startActivity(jugar, options.toBundle());
        } else {
            Toast.makeText(this, "Por favor, ingrese su nombre para empezar la partida", Toast.LENGTH_SHORT).show();
        }
    }
}