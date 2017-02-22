package com.dtu.jacopomattia.a1foersteapp;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.method.TextKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.Random;

/**
 * Created by JacopoMattia on 13-02-2017.
 */

public class Difficulty extends Fragment implements View.OnClickListener, Runnable {

    private SeekBar seekbar;
    private Button orale;
    private Button ringShorta;
    private Button svans;
    private TextView difficulty;
    private int progress_value;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dificulty); --- Til Aktivitet
        View rod = i.inflate(R.layout.activity_dificulty, container, false);

        seekbar = (SeekBar) rod.findViewById(R.id.seekBarDifficulty);
        orale = (Button) rod.findViewById(R.id.buttonOKchanges);
        ringShorta = (Button) rod.findViewById(R.id.button112);
        svans = (Button) rod.findViewById(R.id.buttonSvans);
        difficulty = (TextView) rod.findViewById(R.id.textViewDifficulty);

        orale.setOnClickListener(this);
        ringShorta.setOnClickListener(this);
        svans.setOnClickListener(this);

//        Intent get = getIntent();
//        progress_value = get.getExtras().getInt("sværhed"); --- Til Aktivitet
        try {
            progress_value = getArguments().getInt("sværhed2");
            seekbar.setProgress(progress_value);
        }catch (NullPointerException e) {
            progress_value=10;
        }





        orale.setEnabled(true);

        if (progress_value == 100) {
            ringShorta.setVisibility(View.VISIBLE);
        } else ringShorta.setVisibility(View.GONE);

        if (progress_value < 5) {
            svans.setVisibility(View.VISIBLE);
        } else svans.setVisibility(View.GONE);


        seekbar.setOnSeekBarChangeListener(

                new SeekBar.OnSeekBarChangeListener() {

                    String stilling;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        ringShorta.setVisibility(View.GONE);
                        svans.setVisibility(View.GONE);
                        orale.setEnabled(true);
                        if (progress > 70 && progress < 99) {
                            stilling = "% min bror <3";
                        } else if (progress < 30 && progress >= 0) {
                            stilling = "% din svans!";
                        } else if (progress == 100) {
                            stilling = "%, frækkere end shorta tillader!";
                            //ringShorta.setVisibility(View.VISIBLE);
                        } else stilling = "% carnalito";

                        if (progress == 100) {
                            difficulty.setText("Fult på " + seekbar.getMax() + stilling);
                        }

                        else difficulty.setText("Du " + progress + " % Scarface ud af " + seekbar.getMax() + stilling);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                        if (progress_value == 100) {
                            difficulty.setText("Fult på " + seekbar.getMax() + stilling);
                            ringShorta.setVisibility(View.VISIBLE);
                        }
                        else if (progress_value == 0) {
                            difficulty.setText("Eow er du helt væk?");
                            orale.setEnabled(false);
                        }
                        else if (progress_value < 5) {
                            svans.setVisibility(View.VISIBLE);
                        }
                        else difficulty.setText("Du " + progress_value + " % Scarface ud af " + seekbar.getMax() + stilling);
                    }
                }
        );
        return rod;
    }

    @Override
    public void onClick(View v) {
        if (v == orale) {
            Toast.makeText(getActivity(), "Changes made shebab", 3000).show();
//            Intent i = new Intent(this, Velkomst.class);
//            i.putExtra("sværhed", seekbar.getProgress());
//            startActivity(i);

            Fragment fragment = new Velkomst();
            Bundle argumenter = new Bundle(); // Overfør data til fragmentet
            argumenter.putInt("sværhed", seekbar.getProgress());
            fragment.setArguments(argumenter);

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentos, fragment)
                    .addToBackStack(null)
                    .commit();

        } else if (v == ringShorta) {
            Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: 112"));
            startActivity(i);

        } else if (v == svans) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://da.wikipedia.org/wiki/Svans"));
            startActivity(i);
        }

    }

    @Override
    public void run() {

    }


}

