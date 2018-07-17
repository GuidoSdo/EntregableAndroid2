package com.example.dh.entregableandroidguidosalcedo2.view.fragment;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dh.entregableandroidguidosalcedo2.R;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Pintura;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalle extends Fragment {
    private ImageView imageViewPintura;
    private TextView textTituloPintura;
    private TextView textNombreArtista;
    private TextView textNacionalidad;
    private TextView textInfluencias;


    public FragmentDetalle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        final Bundle unBundle = getArguments();
        Pintura pinturaParaMostrar= FragmentFeed.listaDePinturas.get(unBundle.getInt("idPintura"));


        imageViewPintura = view.findViewById(R.id.imageViewPintura);
        textTituloPintura = view.findViewById(R.id.textTituloPintura);
        textNombreArtista = view.findViewById(R.id.textNombreArtista);
        textNacionalidad = view.findViewById(R.id.textNacionalidad);
        textInfluencias = view.findViewById(R.id.textInfluencias);

        textTituloPintura.setText(pinturaParaMostrar.getName());


        return view;
    }
    private void buscarArtistPorId(Integer idArtist){

    }

}
