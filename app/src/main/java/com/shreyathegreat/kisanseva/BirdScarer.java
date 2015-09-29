package com.shreyathegreat.kisanseva;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class BirdScarer extends ActionBarActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_scarer);
        //Related to the media player
        final MediaPlayer myMediaPlayer = MediaPlayer.create(BirdScarer.this,R.raw.seven);
        //Button related to play btn
        Button myButtonOne = (Button) findViewById(R.id.bu);
        myButtonOne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myMediaPlayer.start();
                myMediaPlayer.setLooping(true);

            }
        });

        //Button related to stop btn
        Button myButtonTwo = (Button) findViewById(R.id.bu2);
        myButtonTwo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myMediaPlayer.stop();

            }
        });

    }
}