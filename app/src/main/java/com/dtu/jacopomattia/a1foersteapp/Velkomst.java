package com.dtu.jacopomattia.a1foersteapp;

import android.app.Activity;
import java.lang.Runnable;

import android.support.v4.app.Fragment;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;


import java.util.Random;
//import java.util.logging.Handler;

public class Velkomst extends Fragment implements View.OnClickListener, Runnable  {

    private Button b;
    private Button bReset;
    private Button bIzzy;
    private EditText gæt;
    private TextView Titel;
    private Random rand;
    private int tallet;
    private Handler handler;
    private int diff;
    private WebView webView;


    @Override
    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_velkomst); -- Til aktivitet
        View rod = i.inflate(R.layout.activity_velkomst, container, false);



        b = (Button) rod.findViewById(R.id.buttonOK);
        bReset = (Button) rod.findViewById(R.id.buttonReset);
        bIzzy = (Button) rod.findViewById(R.id.buttondifficulty);
        gæt = (EditText) rod.findViewById(R.id.editTextGæt);
        Titel = (TextView) rod.findViewById(R.id.textView2);
        webView = (WebView) rod.findViewById(R.id.webview);
        webView.loadUrl("http://javabog.dk");



        rand = new Random();
        tallet = rand.nextInt(10);
        handler = new android.os.Handler();

        b.setOnClickListener(this);
        bReset.setOnClickListener(this);
        bIzzy.setOnClickListener(this);

        gæt.setEnabled(true);
        b.setEnabled(true);
        bReset.setEnabled(false);


        //skaf data fra andet fragment
        try {
//            Intent i = getIntent();
//            diff = i.getExtras().getInt("sværhed"); ------- Til aktivitet
            diff = getArguments().getInt("sværhed");

            if (diff != 10) {tallet = rand.nextInt(diff);
                if (diff < 5) {webView.setVisibility(View.VISIBLE);}
                else webView.setVisibility(View.GONE);
            }
            else {tallet = rand.nextInt(10); webView.setVisibility(View.GONE);}


        }
        catch (NullPointerException e) {diff=10; tallet = rand.nextInt(diff); webView.setVisibility(View.GONE);}

        return rod;
    }

    @Override
    public void onClick(View v) {

        //String talletS = String.valueOf(tallet);
        //svar.setText("svaret er: "+talletS);
        if (v == b) {
            bReset.setEnabled(true);
            if (gæt.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Hvalaverduman!? Skriv noget din bums", Toast.LENGTH_LONG).show();
            } else {
                int gættet = Integer.parseInt(gæt.getText().toString());
                if (gættet == tallet) {
                    Titel.setText("Oralé, du bare på g");
                    //tallet = rand.nextInt(10);
                    gæt.setEnabled(false);
                    b.setEnabled(false);
                    bReset.setEnabled(false);

                    handler.postDelayed(this, 3000);

//                    Intent i = new Intent(this, Hurra.class);
//                    i.putExtra("sværhed", diff);
//                    startActivity(i);
//                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

                    Fragment f = new Hurra();
                    Bundle arg = new Bundle();
                    arg.putInt("sværhed", diff);
                    f.setArguments(arg);

                    getFragmentManager().beginTransaction().replace(R.id.fragmentos, f).commit();

                } else if (gættet < tallet) {
                    Titel.setText("Eow, højere dig");
                } else {
                    Titel.setText("Bro, det for højt det der");
                }
            }
        }
        else if (v == bReset) {
            diff = 10;
            tallet = rand.nextInt(diff);
            Toast.makeText(getActivity(), "nyt tal til dig bri", Toast.LENGTH_LONG).show();
            bReset.setEnabled(false);
            gæt.setText("");
        }
        else if (v == bIzzy) {
//            Intent i = new Intent(this, Difficulty.class);
//            i.putExtra("sværhed", diff);
//            startActivity(i);

            Fragment f = new Difficulty();
            Bundle arg = new Bundle();
            arg.putInt("sværhed2", diff);
            f.setArguments(arg);
            getFragmentManager().beginTransaction().replace(R.id.fragmentos, f).commit();

        }
    }

    @Override
    public void run() {
        //startActivity(new Intent(this, Hurra.class));
        //overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

}