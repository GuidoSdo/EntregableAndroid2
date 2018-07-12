package com.example.dh.entregableandroidguidosalcedo2.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dh.entregableandroidguidosalcedo2.R;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Pintura;

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

        //Obtengo el context
        context = parent.getContext();
        //Obtengo el inflator
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //inflamos la celda
        View celda = layoutInflater.inflate(R.layout.celda_pintura_feed, parent, false);
        //Al VH le asignamos la celda
        PinturaViewHolder pinturaViewHolder = new PinturaViewHolder(celda);


        return pinturaViewHolder;
    }

    // View Holder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Pintura pintura = listaDePinturas.get(position);
        PinturaViewHolder pinturaViewHolder = (PinturaViewHolder) holder;
        //Pido dato de la lista de noticias. Le pido el titulo.
        pinturaViewHolder.asignarDatos(pintura.getName(), pintura.getImage());
    }

    @Override
    public int getItemCount() {
        return listaDePinturas.size();
    }

    // View holder noticia
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

                    Integer idDePinturaSeleccionada = listaDePinturas.get(posicionSeleccionada).getArtistId();
                    comunicacionAdapterRecyclerView.seleccionaronLaCelda(posicionSeleccionada, idDePinturaSeleccionada);
                }
            });
        }

        public void asignarDatos(String texto, String urlToImage) {
            textViewTituloPintura.setText(texto);
            Glide.with(context).load(urlToImage).into(imageViewPintura);
        }
    }

    public interface ComunicacionAdapterRecycler {
        public void seleccionaronLaCelda(Integer posicion, Integer idDePinturaSeleccionada);
    }
}