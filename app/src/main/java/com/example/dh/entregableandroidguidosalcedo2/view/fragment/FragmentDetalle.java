package com.example.dh.entregableandroidguidosalcedo2.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dh.entregableandroidguidosalcedo2.R;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Pintura;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalle extends Fragment {


    public FragmentDetalle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        final Bundle unBundle = getArguments();
        Pintura pinturaParaMostrar= FragmentFeed.listaDePinturas.get(unBundle.getInt("posicion"));

        return view;
    }

}
