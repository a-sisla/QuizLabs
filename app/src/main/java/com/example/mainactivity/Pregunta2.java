package com.example.mainactivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pregunta2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pregunta2 extends Fragment implements AdapterView.OnItemClickListener {


    private ListView listView;
    private List<String> respuestas;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Pregunta2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pregunta2.
     */
    // TODO: Rename and change types and number of parameters
    public static Pregunta2 newInstance(String param1, String param2) {
        Pregunta2 fragment = new Pregunta2();
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
        View vista = inflater.inflate(R.layout.fragment_pregunta2, container, false);

        listView = vista.findViewById(R.id.listViewPreg2);

        respuestas = new ArrayList<>();
        // Añade tus respuestas aquí
        respuestas.add("1914");
        respuestas.add("1921");
        respuestas.add("1939");
        respuestas.add("1905");

        ArrayAdapter<String> adaptadorRespuestas = new ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, respuestas);

        listView.setAdapter(adaptadorRespuestas);

        // Configura el click listener para el ListView
        listView.setOnItemClickListener(this);

        return vista;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Maneja el evento de clic en un elemento del ListView
        String respuestaSeleccionada = respuestas.get(position);
        // Aquí puedes hacer algo con la respuesta seleccionada, por ejemplo, mostrarla en un Toast

        GameActivity activity = (GameActivity) getActivity();

        if (respuestaSeleccionada.equals("1914")) {
            activity.sumarPuntuacion();
            activity.mostrarFragmentRespuestaCorrecta();
        } else {
            activity.restarPuntuacion();
            activity.mostrarFragmentRespuestaIncorrecta();
        }
    }
}