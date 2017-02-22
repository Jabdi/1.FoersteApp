package com.dtu.jacopomattia.a1foersteapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

/**
 * Created by JacopoMattia on 21-02-2017.
 */

    public class Hovedaktivitet extends AppCompatActivity {

    static MediaPlayer mp;
    private ImageSwitcher imageSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragtment_layout);

        //-------------------------- ImageSwitcher Set-up ------------------------- Start
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageview = new ImageView(getApplicationContext());
                imageview.setScaleType((ImageView.ScaleType.FIT_CENTER));
                return imageview;
            }
        });
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);
        imageSwitcher.postDelayed(new Runnable() {
            int i = 0;

            public void run() {
                imageSwitcher.setImageResource(
                        i++ % 2 == 0 ?
                                R.mipmap.scarface :
                                R.mipmap.scarface_happy);
                imageSwitcher.postDelayed(this, 4000);

            }
        }, 1000);
        //-------------------------- ImageSwitcher Set-up ------------------------- DONE

        // --------MUSIC-------- Set-up af Mediaplayer og Afspilning af baggrundsmusik

        mp = MediaPlayer.create(this, R.raw.scarface_boliviatheme);
        mp.setVolume(1, 1);
        mp.start();
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (currVolume > maxVolume / 2) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume / 3, AudioManager.FLAG_SHOW_UI);
        }
        // --------MUSIC--------- Musikafspiller initialiseret

        if (savedInstanceState == null) {
            Fragment fragment = new Start();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentos, fragment)  // tom container i layout
                    .commit();
        }

        setTitle("Scarface Spillet");

        // Man kan trykke på app-ikonet i øverste venstre hjørne
        // (og det betyder at brugeren vil navigere op i hierakiet)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff000000")));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu); // tilføj systemets standardmenuer
        // oftes vil man lægge menupunkterne ud i en XML-fil og pakke den ud således
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.afslut) {
            finish();
            //onDestroy();
        } else if (item.getItemId() == R.id.indstillinger) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Hvad syns du om beatet?");

            dialog.setPositiveButton("Start", new android.app.AlertDialog.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mp.seekTo(0);
                    mp.start();
                }
            });
            dialog.setNegativeButton("Stop", new android.app.AlertDialog.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mp.pause();
                }
            });
            dialog.show();
            Toast.makeText(this, "Oralé", Toast.LENGTH_LONG).show();
        } else return super.onOptionsItemSelected(item);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        mp.pause();
        //mp.release();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mp.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
        mp.seekTo(0);
    }

}
