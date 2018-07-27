package com.example.dh.entregableandroidguidosalcedo2.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dh.entregableandroidguidosalcedo2.R;
import com.example.dh.entregableandroidguidosalcedo2.controller.ControllerPintura;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Artista;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Pintura;
import com.example.dh.entregableandroidguidosalcedo2.utils.ResultListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalle extends Fragment {
    private ImageView imageViewPintura;
    private TextView textTituloPintura;
    private TextView textNombreArtista;
    private TextView textNacionalidad;
    private TextView textViewIdArtist;
    private TextView textInfluencias;
    private Integer posPintura;
    private ConexionFragmentDetalle conexionFragmentDetalle;
    String idArtista;
    DatabaseReference mDatabase;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference referenceDataBase;

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
        textViewIdArtist = view.findViewById(R.id.textViewIdArtist);
        textNombreArtista = view.findViewById(R.id.textNombreArtista);
        textNacionalidad = view.findViewById(R.id.textNacionalidad);
        textInfluencias = view.findViewById(R.id.textInfluencias);
        posPintura = unBundle.getInt("posicion");


        cargarPintura();


        ControllerPintura controllerPintura = new ControllerPintura(getContext());
        controllerPintura.obtenerPintura(new ResultListener<List<Pintura>>() {
            @Override
            public void finish(List<Pintura> resultado) {
                textTituloPintura.setText(resultado.get(posPintura).getName());
            }
        });



        return view;
    }

    public void cargarPintura(){

        ResultListener<List<Pintura>> escuchadorVista = new ResultListener<List<Pintura>>() {
            @Override
            public void finish(List<Pintura> resultado) {
                idArtista =  resultado.get(posPintura).getArtistId().toString();
                if(idArtista.equals(resultado.get(posPintura).getArtistId())){
                    cargarArtista(idArtista);

                } else{
                    Toast.makeText(getContext(), "falla", Toast.LENGTH_SHORT).show();
                }

            }
        };
        ControllerPintura controllerPintura = new ControllerPintura(getContext());
        controllerPintura.obtenerPintura(escuchadorVista);
    }


    public void cargarArtista(String idArtista){
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
        referenceDataBase = mDatabase.child("artists").child(idArtista);

        ValueEventListener valueEventListener;
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Artista artistaLeido = dataSnapshot.getValue(Artista.class);

                textNombreArtista.setText(artistaLeido.getName());
                textNacionalidad.setText(artistaLeido.getNationality());
                textInfluencias.setText(artistaLeido.getInfluenced_by());
                textViewIdArtist.setText(artistaLeido.getArtistId());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "ddffdsfgd", Toast.LENGTH_SHORT).show();

            }

        };

        referenceDataBase.addListenerForSingleValueEvent(valueEventListener);

    }

    public interface ConexionFragmentDetalle {
        void clickearonDetalleFragment(Integer pos);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.conexionFragmentDetalle = (ConexionFragmentDetalle) context;
    }

}
