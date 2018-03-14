package com.example.soundpool;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements SoundPool.OnLoadCompleteListener {

    final String LOG_TAG = "myLogs";
    final int MAX_STREAMS = 1;

    SoundPool sp;
    int soundIdShot;
    int soundIdExplosion;


    int streamIDShot;
    int streamIDExplosion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(this);

        soundIdShot = sp.load(this, R.raw.shot, 1);
        Log.d(LOG_TAG, "soundIdShot = " + soundIdShot);

        try {
            soundIdExplosion = sp.load(getAssets().openFd("explosion.ogg"), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(LOG_TAG, "soundIdExplosion = " + soundIdExplosion);

    }

    public void onClick(View view) {
        streamIDShot = sp.play(soundIdShot, 1, 1, 0, 10, 1);
        Log.d(LOG_TAG, "streamIDShot = " + streamIDShot);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        streamIDExplosion = sp.play(soundIdExplosion, 1, 1, 0, 0, 1);
        Log.d(LOG_TAG, "streamIDExplosion = " + streamIDExplosion);    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        Log.d(LOG_TAG, "onLoadComplete, sampleId = " + sampleId + ", status = " + status);
    }
}
