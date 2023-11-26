package com.example.mainactivity;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicPlayerManager {
    private static MediaPlayer mediaPlayer;
    private static int currentSong = -1;

    public static void startPlaying(Context context, int resourceId) {

        if(currentSong != resourceId){
            stopPlaying();

            mediaPlayer = MediaPlayer.create(context, resourceId);
            mediaPlayer.setLooping(true);  // Repetir en bucle
            mediaPlayer.start();

           currentSong = resourceId;
        }

    }
    public static void pulsarUnaVez(Context context, int resourceId) {

        MediaPlayer mediaPlayer2 = MediaPlayer.create(context, resourceId);
        mediaPlayer2.start();

    }

    public static void stopPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
