package com.example.dh.entregableandroidguidosalcedo2.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dh.entregableandroidguidosalcedo2.R;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Pintura;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.File;
import java.util.List;

public class AdapterRecyclerViewPinturas extends RecyclerView.Adapter {

    private static final String TAG = "AdapterRecyclerViewPinturas";
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
        ImageView imageViewPinturaFeedCelda;
        TextView textViewTituloPintura;

        public PinturaViewHolder(View itemView) {
            super(itemView);
            imageViewPinturaFeedCelda = itemView.findViewById(R.id.imageViewPinturaCelda);
            textViewTituloPintura = itemView.findViewById(R.id.textViewTituloPinturaCelda);
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

             cargarImagenPintura(pintura.getImage(), imageViewPinturaFeedCelda);
        }
    }

    public void setList(List<Pintura> listaDePinturasAgregar) {
        this.listaDePinturasDelRecycler = listaDePinturasAgregar;
        notifyDataSetChanged();
    }

    @SuppressLint("LongLogTag")
    public void cargarImagenPintura(String imagenEnPng, final ImageView imageView){
        // Referencia al storage de lo que quiero traer
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        File file = null;
        try{
            // Archivo temporal donde voy a cargar la imagen
            file = file.createTempFile("images", "png");
            final File finalFile = file;
            storageReference.child(imagenEnPng).getFile(file)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(finalFile.getAbsolutePath());
                            imageView.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG,"fallo la descarga de la imagen");
                    e.printStackTrace();
                }
            });
        }catch(Exception e){
            Log.e(TAG,"ocurrio un problema al bajar la imagen");
            e.printStackTrace();
        }
    }

    public interface ComunicacionAdapterRecycler {
        public void seleccionaronLaCelda(Integer pos);
    }

}