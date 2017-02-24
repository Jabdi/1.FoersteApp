package com.dtu.jacopomattia.a1foersteapp;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static android.content.Context.AUDIO_SERVICE;

/**
 * Created by JacopoMattia on 13-02-2017.
 */

public class Start extends Fragment implements Runnable{

        //static Start aktivitetDerVisesNu = null; -- til aktivitet
        Handler handler = new Handler();
        private ImageView iv;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // super.onCreate(savedInstanceState); --- til aktivitet
            Log.d("Start fragment", "Det kører bare");
            //View rod = inflater.inflate(R.layout.activity_velkomst, container, false);

            iv = new ImageView(getActivity());
            //iv = (ImageView) rod.findViewById(R.id.SplashStartScarface);

            iv.setImageResource(R.mipmap.scarface);
            iv.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.egen_anim));
            // setContentView(iv); -- til Aktivitet

            // Hvis savedInstanceState ikke er null er det en fragmentet  ved at blive genstartet
            if (savedInstanceState == null) {
                handler.postDelayed(this, 3000); // <1> Kør run() om 3 sekunder
            }
            return iv;


        }

        public void run() {
//            startActivity(new Intent(this, Velkomst.class));
//            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//
//            aktivitetDerVisesNu.finish();  // <2> Luk velkomsaktiviteten
//            aktivitetDerVisesNu = null;
// ------------------------------------------------------------------------------------Til aktivitet, følgende til fragment

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.fragmentos, new Velkomst())  // tom container i layout
                    .commit();
            getFragmentManager().beginTransaction()
                    .add(R.id.fragmentImageSwitcher, new ScarfaceBaggrund()).commit();
        }

        /**
         * Kaldes hvis brugeren trykker på tilbage-knappen.
         * I så tilfælde skal vi ikke hoppe videre til næste aktivitet
         */
//        @Override
//        public void finish() {
//            super.finish();
//            handler.removeCallbacks(this);
//        }
//    protected void onDestroy() {
//        //mp.stop();
//        mp.release();
//        super.onDestroy();
//    }
    }
