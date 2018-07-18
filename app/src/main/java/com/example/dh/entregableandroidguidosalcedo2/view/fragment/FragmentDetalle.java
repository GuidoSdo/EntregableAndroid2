package com.example.dh.entregableandroidguidosalcedo2.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dh.entregableandroidguidosalcedo2.R;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Artista;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Pintura;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalle extends Fragment {
    private ImageView imageViewPintura;
    private TextView textTituloPintura;
    private TextView textNombreArtista;
    private TextView textNacionalidad;
    private TextView textInfluencias;
    private Context context;

    Pintura pinturaSeleccionada;

    public FragmentDetalle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        Bundle unBundle = getArguments();
        imageViewPintura = view.findViewById(R.id.imageViewPintura);
        textTituloPintura = view.findViewById(R.id.textTituloPintura);
        textNombreArtista = view.findViewById(R.id.textNombreArtista);
        textNacionalidad = view.findViewById(R.id.textNacionalidad);
        textInfluencias = view.findViewById(R.id.textInfluencias);

        String idArtista = unBundle.getString("posicion");

        leerArtista(idArtista);




        return view;
    }


    public void leerArtista(String artistId){
        DatabaseReference mDatabase;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
        DatabaseReference referencePrimerMensaje = mDatabase.child("artists").child(artistId);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Artista mensajeLeido = dataSnapshot.getValue(Artista.class);
                //Toast.makeText(context, mensajeLeido.toString(), Toast.LENGTH_SHORT).show();
                //textTituloPintura.setText(pinturaSeleccionada.getName());

                textNombreArtista.setText(mensajeLeido.getName());
                textNacionalidad.setText(mensajeLeido.getNationality());
                textInfluencias.setText(mensajeLeido.getInfluenced_by());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        referencePrimerMensaje.addListenerForSingleValueEvent(valueEventListener);
    }


}
