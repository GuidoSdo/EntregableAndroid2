package com.example.dh.entregableandroidguidosalcedo2.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dh.entregableandroidguidosalcedo2.R;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Artista;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Pintura;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class AdapterRecyclerViewPinturas extends RecyclerView.Adapter {

    private List<Pintura> listaDePinturas;
    private Context context;

    private ComunicacionAdapterRecycler comunicacionAdapterRecyclerView;

    // Constructor
    public AdapterRecyclerViewPinturas(List<Pintura> listaDePinturas,
                                       ComunicacionAdapterRecycler comunicacionAdapterRecycler) {
        this.listaDePinturas = listaDePinturas;
        this.comunicacionAdapterRecyclerView = comunicacionAdapterRecycler; //AGREGO AL CONSTRUCTOR LA INTERFACE
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View celda = layoutInflater.inflate(R.layout.celda_pintura_feed, parent, false);
        PinturaViewHolder pinturaViewHolder = new PinturaViewHolder(celda);

        return pinturaViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Pintura pintura = listaDePinturas.get(position);
        PinturaViewHolder pinturaViewHolder = (PinturaViewHolder) holder;
        pinturaViewHolder.asignarDatos(pintura.getName(), pintura.getImage());
    }

    @Override
    public int getItemCount() {
        return listaDePinturas.size();
    }

    private class PinturaViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTituloPintura;
        ImageView imageViewPintura;

        public PinturaViewHolder(View itemView) {
            super(itemView);
            imageViewPintura = itemView.findViewById(R.id.imageViewPintura);
            textViewTituloPintura = itemView.findViewById(R.id.textViewTituloPintura);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer posicionSeleccionada = getAdapterPosition();

                    String idDePinturaSeleccionada = listaDePinturas.get(posicionSeleccionada).getArtistId();
                    comunicacionAdapterRecyclerView.seleccionaronLaCelda(idDePinturaSeleccionada,posicionSeleccionada);
                }
            });
        }

        public void asignarDatos(String texto, String urlToImage) {
            textViewTituloPintura.setText(texto);
           //Glide.with(context).load(urlToImage).into(imageViewPintura);
        }
    }

    public interface ComunicacionAdapterRecycler {
        public void seleccionaronLaCelda(String idDePinturaSeleccionada,Integer pos);
    }




}