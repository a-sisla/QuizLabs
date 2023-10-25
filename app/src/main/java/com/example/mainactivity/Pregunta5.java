package com.example.mainactivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pregunta5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pregunta5 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Pregunta5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pregunta5.
     */
    // TODO: Rename and change types and number of parameters
    public static Pregunta5 newInstance(String param1, String param2) {
        Pregunta5 fragment = new Pregunta5();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_pregunta5, container, false);

        ImageButton botonRespuesta1 = vista.findViewById(R.id.preg5resp1);
        ImageButton botonRespuesta2 = vista.findViewById(R.id.preg5resp2);
        ImageButton botonRespuesta3 = vista.findViewById(R.id.preg5resp3);
        ImageButton botonRespuesta4 = vista.findViewById(R.id.preg5resp4);

        botonRespuesta1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                GameActivity activity = (GameActivity) getActivity();
                if (activity != null) {
                    activity.restarPuntuacion();
                    activity.mostrarFragmentRespuestaIncorrecta();
                }
            }
        });

        botonRespuesta2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                GameActivity activity = (GameActivity) getActivity();
                if (activity != null) {
                    activity.restarPuntuacion();
                    activity.mostrarFragmentRespuestaIncorrecta();
                }
            }
        });

        botonRespuesta3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                GameActivity activity = (GameActivity) getActivity();
                if (activity != null) {
                    activity.restarPuntuacion();
                    activity.mostrarFragmentRespuestaIncorrecta();
                }
            }
        });

        botonRespuesta4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                GameActivity activity = (GameActivity) getActivity();
                if (activity != null) {
                    activity.sumarPuntuacion();
                    activity.mostrarFragmentRespuestaCorrecta();
                }
            }
        });

        return vista;
    }
}