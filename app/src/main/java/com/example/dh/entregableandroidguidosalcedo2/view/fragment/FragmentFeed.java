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

import java.util.List;

public class FragmentFeed extends Fragment implements AdapterRecyclerViewPinturas.ComunicacionAdapterRecycler {
    public static List<Pintura> listaDePinturas;
    private comunicacionFragment comunicacionFragment;
    private RecyclerView recyclerViewFeed;

    public FragmentFeed() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.comunicacionFragment = (comunicacionFragment)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_feed, container, false);

        //BUSCO EL contenedor del recycler view
        recyclerViewFeed = view.findViewById(R.id.RWFeed);
        //cargo las pinturas y las coloco en el recycler
        cargarPintura(getContext());
        return view;
    }
    public void cargarPintura(final Context unContext){
        ControllerPintura noticiaController = new ControllerPintura();
        noticiaController.obtenerPintura(new ResultListener<List<Pintura>>() {
            @Override
            public void finish(List<Pintura> resultado) {

                //Toast.makeText(getContext(), "Se cargo la info de internet", Toast.LENGTH_SHORT).show();

                AdapterRecyclerViewPinturas adapterRecyclerViewPinturas = new AdapterRecyclerViewPinturas(resultado,FragmentFeed.this);
                recyclerViewFeed.setAdapter(adapterRecyclerViewPinturas);

                //CREAMOS Y SETEAMOS layoutManager
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerViewFeed.setLayoutManager(layoutManager);

                listaDePinturas = resultado;

            }
        });
    }

    @Override
    public void seleccionaronLaCelda(Integer posicion, Integer idDePinturaSeleccionada) {
        comunicacionFragment.clickearonLaPintura(posicion, idDePinturaSeleccionada);
    }

    public interface comunicacionFragment {
         void clickearonLaPintura(Integer posicion,Integer idDePinturaSeleccionada);
    }

    }


