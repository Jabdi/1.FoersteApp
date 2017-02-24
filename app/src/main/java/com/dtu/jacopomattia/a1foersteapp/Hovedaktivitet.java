package com.dtu.jacopomattia.a1foersteapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
//import android.R;

/**
 * Created by JacopoMattia on 21-02-2017.
 */

    public class Hovedaktivitet extends AppCompatActivity {

    static MediaPlayer mp;
    static FloatingActionButton fab;
    private int toggle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragtment_layout);


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
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff000000")));

        // ---------------- Lyd Knap ----------------------
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggle==1) {
                    fab.setImageResource(android.R.drawable.ic_lock_silent_mode_off);
                    toggle=0;
                    mp.start();
                }
                else {
                    fab.setImageResource(android.R.drawable.ic_lock_silent_mode);
                    toggle=1;
                    mp.pause();
                }

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        //--------------Lyd Knap --------------------------

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
        }
        else if (item.getItemId() == R.id.indstillinger) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Vælg ny profil?");
            dialog.setPositiveButton("Ja tjak", new android.app.AlertDialog.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                startActivity(new Intent(getApplication(), Hoved_Navigation_Activity.class));
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                }
            });
            dialog.setNegativeButton("Nej du helt færdig", null);
            dialog.show();
        }
        else if (item.getItemId() == R.id.Musik) {
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

    public void visLydKnap(){
        fab.setVisibility(View.VISIBLE);
    }
    public void skjulLydKnap(){
        fab.setVisibility(View.INVISIBLE);
    }

}
