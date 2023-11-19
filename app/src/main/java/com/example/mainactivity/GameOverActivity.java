package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    private TextView miTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        MusicPlayerManager.startPlaying(this, R.raw.musicafin);

        miTextView = findViewById(R.id.textViewPuntos);

        Intent intent = getIntent();
        int dato = intent.getIntExtra("puntos", 0);

        System.out.println(dato);
        miTextView.setText(String.valueOf(dato));

    }

    public void inicio (View v) {
        MusicPlayerManager.pulsarUnaVez(this, R.raw.botonpulsar);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent, options.toBundle());
    }

    public void ranking (View v) {
        MusicPlayerManager.pulsarUnaVez(this, R.raw.botonpulsar);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent, options.toBundle());
    }


}