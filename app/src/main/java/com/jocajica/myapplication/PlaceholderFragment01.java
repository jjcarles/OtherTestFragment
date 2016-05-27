package com.jocajica.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class PlaceholderFragment01 extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "PlaceholderFragment01";

    private TextView textView;
    private Button button;

    private IFragment01Listener mCallback;

    public interface IFragment01Listener {
        public void OnFragment01Listener();
    }

    public PlaceholderFragment01() {
    }

    public static PlaceholderFragment01 newInstance(Bundle arguments) {
        PlaceholderFragment01 fragment = new PlaceholderFragment01();

        if (arguments != null) {
            fragment.setArguments(arguments);
        }

        return fragment;
    }

    // El fragment se ha adjuntado al Activity
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mCallback = (IFragment01Listener) activity;
        }catch(Exception ex){
            Log.e(TAG, "IFragment01Listener not implemented.");
        }
    }

    // El Fragment ha sido creado
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // El Fragment va a cargar su layout, el cual debemos especificar
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment01, container, false);
        if (rootView != null) {
            textView = (TextView) rootView.findViewById(R.id.section_label);
            button = (Button)rootView.findViewById(R.id.button01);
        }

        return rootView;
    }

    // El layout ya se ha cargado
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.OnFragment01Listener();
            }
        });

        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
    }

    // La vista ha sido creada y cualquier configuración guardada está cargada
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    // El Activity que contiene el Fragment ha terminado su creación
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true); //Indicamos que este Fragment tiene su propio menu de opciones
    }

    // El Fragment ha sido quitado de su Activity y ya no está disponible
    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }
}
