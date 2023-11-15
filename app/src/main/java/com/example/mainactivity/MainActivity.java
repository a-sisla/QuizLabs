package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Jugar(View v) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent jugar = new Intent(this, GameActivity.class);
        startActivity(jugar, options.toBundle());
    }

    public void IrABaseDeDatos(View v) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent irABaseDeDatos = new Intent(this, QuestionsActivity.class);
        startActivity(irABaseDeDatos, options.toBundle());
    }
}