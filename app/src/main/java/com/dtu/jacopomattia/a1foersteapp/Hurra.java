package com.dtu.jacopomattia.a1foersteapp;

import android.app.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by JacopoMattia on 13-02-2017.
 */

public class Hurra extends Fragment implements Runnable {

    //static Hurra aktivitetDerVisesNu = null;
    Handler handler = new Handler();
    private int diff;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);


        //Intent i = getIntent();
        diff = getArguments().getInt("sværhed");



        ImageView iv = new ImageView(getActivity());
        iv.setImageResource(R.mipmap.scarface_happy);
        //iv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.egen_anim));
        //setContentView(iv); -- Til aktivitet

        // Hvis savedInstanceState ikke er null er det en aktivitet der er ved at blive genstartet
        if (savedInstanceState == null) {
            handler.postDelayed(this, 3000); // <1> Kør run() om 3 sekunder
    }
        //aktivitetDerVisesNu = this; --- Til aktivitet
        return iv;
    }


    @Override
    public void run() {
//        Intent k = new Intent(this, Velkomst.class);
//        k.putExtra("sværhed", diff);
//        startActivity(k);


        Fragment fragment = new Velkomst();
        Bundle arg = new Bundle();
        arg.putInt("sværhed", diff);
        fragment.setArguments(arg);

        getFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragmentos, fragment)  // tom container i layout
                .commit();
    }
}
