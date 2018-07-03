package com.example.audiofocus;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {
    final static String LOG_TAG = "myLogs";

    AudioManager audioManager;

    AFListener afListenerMusic;
    AFListener afListenerSound;

    MediaPlayer mpMusic;
    MediaPlayer mpSound;

    Integer MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }

    public void onClickMusic(View view) {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

        mpMusic = new MediaPlayer();
        try {
            mpMusic.setDataSource("mnt/sdcard/Music/music.mp3");
            mpMusic.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mpMusic.setOnCompletionListener(this);

        afListenerMusic = new AFListener(mpMusic, "Music");
        int requestResult = audioManager.requestAudioFocus(afListenerMusic,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        Log.d(LOG_TAG, "Music request focus, result: " + requestResult);

        mpMusic.start();
    }

    public void onClickSound(View view) {
        int durationHint = AudioManager.AUDIOFOCUS_GAIN;
        switch (view.getId()) {
            case R.id.btnPlaySoundG:
                durationHint = AudioManager.AUDIOFOCUS_GAIN;
                break;
            case R.id.btnPlaySoundGT:
                durationHint = AudioManager.AUDIOFOCUS_GAIN_TRANSIENT;
                break;
            case R.id.btnPlaySoundGTD:
                durationHint = AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK;
                break;
        }

        mpSound = MediaPlayer.create(this, R.raw.explosion);
        mpSound.setOnCompletionListener(this);

        afListenerSound = new AFListener(mpSound, "Sound");
        int requestResult = audioManager.requestAudioFocus(afListenerSound,
                AudioManager.STREAM_MUSIC, durationHint);
        Log.d(LOG_TAG, "Sound request focus, result: " + requestResult);

        mpSound.start();

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (mp == mpMusic) {
            Log.d(LOG_TAG, "Music: abandon focus");
            audioManager.abandonAudioFocus(afListenerMusic);
        } else if (mp == mpSound) {
            Log.d(LOG_TAG, "Sound: abandon focus");
            audioManager.abandonAudioFocus(afListenerSound);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mpMusic != null)
            mpMusic.release();
        if (mpSound != null)
            mpSound.release();
        if (afListenerMusic != null)
            audioManager.abandonAudioFocus(afListenerMusic);
        if (afListenerSound != null)
            audioManager.abandonAudioFocus(afListenerSound);
    }

    class AFListener implements AudioManager.OnAudioFocusChangeListener {

        String label = "";
        MediaPlayer mp;

        public AFListener(MediaPlayer mp, String label) {
            this.label = label;
            this.mp = mp;
        }

        @Override
        public void onAudioFocusChange(int focusChange) {
            String event = "";
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS:
                    event = "AUDIOFOCUS_LOSS";
                    mp.pause();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    event = "AUDIOFOCUS_LOSS_TRANSIENT";
                    mp.pause();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    event = "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK";
                    mp.setVolume(0.5f, 0.5f);
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    event = "AUDIOFOCUS_GAIN";
                    if (!mp.isPlaying())
                        mp.start();
                    mp.setVolume(1.0f, 1.0f);
                    break;
            }
            Log.d(LOG_TAG, label + " onAudioFocusChange: " + event);
        }
    }
}
