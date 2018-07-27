package com.example.dh.entregableandroidguidosalcedo2.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dh.entregableandroidguidosalcedo2.R;
import com.example.dh.entregableandroidguidosalcedo2.controller.ControllerPintura;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Pintura;
import com.example.dh.entregableandroidguidosalcedo2.utils.ResultListener;
import com.example.dh.entregableandroidguidosalcedo2.view.adapter.AdapterRecyclerViewPinturas;

import java.util.ArrayList;
import java.util.List;

public class FragmentFeed extends Fragment implements AdapterRecyclerViewPinturas.ComunicacionAdapterRecycler {
    private ComunicacionFragment comunicacionFragment;
    private RecyclerView recyclerViewFeed;
    private AdapterRecyclerViewPinturas adapterRecyclerViewPinturas;
    public static List<Pintura> listaDePinturasDelAdapter;
    public FragmentFeed() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.comunicacionFragment = (ComunicacionFragment)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_feed, container, false);

        recyclerViewFeed = view.findViewById(R.id.RWFeed);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewFeed.setLayoutManager(layoutManager);

        listaDePinturasDelAdapter = new ArrayList<>();
        adapterRecyclerViewPinturas = new AdapterRecyclerViewPinturas(listaDePinturasDelAdapter,this);
        recyclerViewFeed.setAdapter(adapterRecyclerViewPinturas);
        cargarPintura();

        return view;
    }

    public void cargarPintura(){

        ResultListener<List<Pintura>> escuchadorVista = new ResultListener<List<Pintura>>() {
            @Override
            public void finish(List<Pintura> resultado) {
                adapterRecyclerViewPinturas.setList(resultado);
            }
        };
        ControllerPintura controllerPintura = new ControllerPintura(getContext());
        controllerPintura.obtenerPintura(escuchadorVista);
    }




    @Override
    public void seleccionaronLaCelda(Integer pos) {
        comunicacionFragment.clickearonLaPintura(pos);

    }

    public interface ComunicacionFragment {
         void clickearonLaPintura(Integer pos);
    }

}


