package com.example.mainactivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Questions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Questions extends Fragment {

    TextView pregunta;
    Button botonRespuesta1, botonRespuesta2, botonRespuesta3, botonRespuesta4;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Questions() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Questions.
     */
    // TODO: Rename and change types and number of parameters
    public static Questions newInstance(String param1, String param2) {
        Questions fragment = new Questions();
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
        View vista = inflater.inflate(R.layout.fragment_questions, container, false);

        pregunta = vista.findViewById(R.id.pregunta);
        botonRespuesta1 = vista.findViewById(R.id.respuesta1);
        botonRespuesta2 = vista.findViewById(R.id.respuesta2);
        botonRespuesta3 = vista.findViewById(R.id.respuesta3);
        botonRespuesta4 = vista.findViewById(R.id.respuesta4);

        botonRespuesta1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                GameActivity activity = (GameActivity) getActivity();
                if (activity != null)
                    activity.comprobarRespuesta(1);
            }
        });

        botonRespuesta2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                GameActivity activity = (GameActivity) getActivity();
                if (activity != null)
                    activity.comprobarRespuesta(2);
            }
        });

        botonRespuesta3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                GameActivity activity = (GameActivity) getActivity();
                if (activity != null)
                    activity.comprobarRespuesta(3);
            }
        });

        botonRespuesta4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                GameActivity activity = (GameActivity) getActivity();
                if (activity != null)
                    activity.comprobarRespuesta(4);
            }
        });

        Bundle args = getArguments();

        if (args != null) {
            pregunta.setText(args.getString("pr", "-"));
            botonRespuesta1.setText(args.getString("re1", "-"));
            botonRespuesta2.setText(args.getString("re2", "-"));
            botonRespuesta3.setText(args.getString("re3", "-"));
            botonRespuesta4.setText(args.getString("re4", "-"));
        }
        return vista;
    }

    public void setTexts(String pr, String res1, String res2, String res3, String res4) {
        if (pregunta != null && botonRespuesta1 != null && botonRespuesta2 != null && botonRespuesta3 != null && botonRespuesta4 != null) {
            pregunta.setText(pr);
            botonRespuesta1.setText(res1);
            botonRespuesta2.setText(res2);
            botonRespuesta3.setText(res3);
            botonRespuesta4.setText(res4);
        }
    }
}