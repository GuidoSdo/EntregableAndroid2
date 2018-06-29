package com.example.dh.entregableandroidguidosalcedo2.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dh.entregableandroidguidosalcedo2.R;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Pintura;

import java.util.List;

/*
public class AdapterRecyclerViewPinturas extends RecyclerView.Adapter{
*/

public class AdapterRecyclerViewPinturas {

   /* private List<Pintura> listaDePinturas;
    Context context;

    private ComunicacionAdapterRecycler comunicacionAdapterRecycler;

    public AdapterRecyclerViewPinturas(List<Pintura> listaDePinturas, ComunicacionAdapterRecycler comunicacionAdapterRecycler) {
        this.listaDePinturas = listaDePinturas;
        this.comunicacionAdapterRecycler = comunicacionAdapterRecycler; //AGREGO AL CONSTRUCTOR LA INTERFACE
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Obtengo el context
        context = parent.getContext();
        //Obtengo el inflator
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        //inflamos la celda
        View celda = layoutInflater.inflate(R.layout.celda_pinturas,parent,false);
        //Al VH le asignamos la celda
        PinturaViewHolder pinturaViewHolder = new PinturaViewHolder(celda);



        return pinturaViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Pintura pintura = listaDePinturas.get(position);
        PinturaViewHolder pinturaViewHolder = (PinturaViewHolder) holder;
        //Pido dato de la lista de noticias. Le pido el titulo.
        pinturaViewHolder.asignarDatos(pintura.getTitle(),pintura.getUrlToImage());
    }

    @Override
    public int getItemCount() {
        return listaDePinturas.size();
    }

    private class PinturaViewHolder extends RecyclerView.ViewHolder{

        TextView textoTituloNoticia;
        ImageView imageView;

        public PinturaViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgNoticia);
            textoTituloNoticia = itemView.findViewById(R.id.textViewTituloNoticia);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer posicionSeleccionada = getAdapterPosition();

                    String urlDeNoticiaSeleccionada = listaDeNoticias.get(posicionSeleccionada).getUrl();
                    comunicacionAdapterRecycler.seleccionaronLaCelda(posicionSeleccionada,urlDeNoticiaSeleccionada);
                }
            });

        }

        public void asignarDatos(String texto, String urlToImage){
            textoTituloNoticia.setText(texto);
            Glide.with(context).load(urlToImage).into(imageView);

        }
    }

    public interface ComunicacionAdapterRecycler {
        public void seleccionaronLaCelda(Integer posicion,String urlNoticia);
    }*/

}
