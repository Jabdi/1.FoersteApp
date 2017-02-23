package com.dtu.jacopomattia.a1foersteapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SpillerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SpillerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpillerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NAVN = "spillernavn";
    private static final String BILLED = "spillerbillede";
    private static final String LYD = "spillerlyd";

    // TODO: Rename and change types of parameters
    private String spillernavn;
    private String spillerbillede;
    private String spillerlyd;
    static int antalSpillere=0;

    private OnFragmentInteractionListener mListener;

    public SpillerFragment() {
        // Required empty public constructor
        antalSpillere++;
        this.spillernavn="Spiller"+antalSpillere;
        this.spillerbillede="Spiller"+antalSpillere+" Billede";
        this.spillerlyd = "Spiller"+antalSpillere+" Lyd";
    }

    public String GetPlayerName(){
        return spillernavn;
    }
    public void SetPlayerName(String spillernavnet){
        spillernavn = spillernavnet;
    }
    public String GetPlayerPicture(){
        return spillerbillede;
    }
    public void SetPlayerPicture(String spillerbilledet){
        spillerbillede = spillerbilledet;
    }
    public String GetPlayerLyd(){
        return spillerlyd;
    }
    public void SetPlayerLyd(String spillerlyden){
        spillernavn = spillerlyden;
    }

    public static SpillerFragment newInstance(String spillernavn, String spillerbillede, String spillerlyd) {
        SpillerFragment fragment = new SpillerFragment();
        Bundle args = new Bundle();
        args.putString(NAVN, spillernavn);
        args.putString(BILLED, spillerbillede);
        args.putString(LYD, spillerlyd);
        fragment.setArguments(args);
        return fragment;
    }

    public static int getNumberOfPlayers() {
        return antalSpillere;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            spillernavn = getArguments().getString(NAVN);
            spillerbillede = getArguments().getString(BILLED);
            spillerlyd = getArguments().getString(LYD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spiller, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
