package com.dtu.jacopomattia.a1foersteapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

/**
 * Created by JacopoMattia on 23-02-2017.
 */

public class ScarfaceBaggrund extends Fragment {
    Handler handler = new Handler();
    private ImageSwitcher imageSwitcher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // super.onCreate(savedInstanceState); --- til aktivitet
        Log.d("Start fragment", "Det kører bare");

        View rod = inflater.inflate(R.layout.fragtment_layout, container, false);
                //-------------------------- ImageSwitcher Set-up ------------------------- Start
                imageSwitcher = (ImageSwitcher) rod.findViewById(R.id.imageSwitcher1);
                imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                    @Override
                    public View makeView() {
                        ImageView imageview = new ImageView(getActivity());
                        imageview.setScaleType((ImageView.ScaleType.FIT_CENTER));
                        return imageview;
                    }
                });
                Animation in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left);
                Animation out = AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_out_right);
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



        // Hvis savedInstanceState ikke er null er det en fragmentet  ved at blive genstartet
//        if (savedInstanceState == null) {
//            handler.postDelayed(getActivity(), 3000); // <1> Kør run() om 3 sekunder
//        }
        return rod;


    }




}
