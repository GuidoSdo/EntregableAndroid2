package com.example.dh.entregableandroidguidosalcedo2.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dh.entregableandroidguidosalcedo2.R;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Pintura;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.util.List;

public class AdapterRecyclerViewPinturas extends RecyclerView.Adapter {

    private List<Pintura> listaDePinturasDelRecycler;
    private Context context;
    private ComunicacionAdapterRecycler comunicacionAdapterRecyclerView;


    // Constructor
    public AdapterRecyclerViewPinturas(List<Pintura> listaDePinturasDelRecycler,
                                       ComunicacionAdapterRecycler comunicacionAdapterRecycler) {
        this.listaDePinturasDelRecycler = listaDePinturasDelRecycler;
        this.comunicacionAdapterRecyclerView = comunicacionAdapterRecycler; //AGREGO AL CONSTRUCTOR LA INTERFACE
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View celda = layoutInflater.inflate(R.layout.celda_pintura_feed, parent, false);
        PinturaViewHolder pinturaViewHolder = new PinturaViewHolder(celda);

        return pinturaViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Pintura pintura = listaDePinturasDelRecycler.get(position);
        PinturaViewHolder pinturaViewHolder = (PinturaViewHolder) holder;
        pinturaViewHolder.asignarDatosPorCelda(pintura);
    }

    @Override
    public int getItemCount() {
        return listaDePinturasDelRecycler.size();
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
                    comunicacionAdapterRecyclerView.seleccionaronLaCelda(posicionSeleccionada);
                }
            });
        }

        public void asignarDatosPorCelda(Pintura pintura) {
            textViewTituloPintura.setText(pintura.getName().toString());
           // descargarImageFireBase(imageViewPintura, pintura);
           //Glide.with(context).load(urlToImage).into(imageViewPintura);
        }
    }
    public void setList(List<Pintura> listaDePinturasAgregar) {
        this.listaDePinturasDelRecycler = listaDePinturasAgregar;
        notifyDataSetChanged();
    }
    public void descargarImageFireBase(ImageView imageView, Pintura pintura) {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        StorageReference imageRef = storageReference.child(pintura.getImage());
        //Glide.with(context).using(new FirebaseImageLoader()).load(imageRef).into(imageView);

    }


    public interface ComunicacionAdapterRecycler {
        public void seleccionaronLaCelda(Integer pos);
    }




}