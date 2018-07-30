package com.example.dh.entregableandroidguidosalcedo2.view.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.List;

public class FragmentDetalle extends Fragment {
    private ImageView imageViewPintura;
    private TextView textTituloPintura;
    private TextView textNombreArtista;
    private TextView textNacionalidad;
    private TextView textViewIdArtist;
    private TextView textInfluencias;
    private Integer posPintura;
    private ConexionFragmentDetalle conexionFragmentDetalle;
    private String idArtista;
    private DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference referenceDataBase;
    private static final String NODO_ARTISTA = "artists";
    public static final String TAG = "FragmentDetalle" ;
    private static final String POSICION_KEY = "posicion";

    public FragmentDetalle() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);
        Bundle unBundle = getArguments();

        imageViewPintura = view.findViewById(R.id.imageViewPinturaDetalle);
        textTituloPintura = view.findViewById(R.id.textTituloPintura);
        textViewIdArtist = view.findViewById(R.id.textViewIdArtist);
        textNombreArtista = view.findViewById(R.id.textNombreArtista);
        textNacionalidad = view.findViewById(R.id.textNacionalidad);
        textInfluencias = view.findViewById(R.id.textInfluencias);
        posPintura = unBundle.getInt(POSICION_KEY);


        ControllerPintura controllerPintura = new ControllerPintura(getContext());
        controllerPintura.obtenerPintura(new ResultListener<List<Pintura>>() {
            @Override
            public void finish(List<Pintura> resultado) {
                textTituloPintura.setText(resultado.get(posPintura).getName());
                idArtista =  resultado.get(posPintura).getArtistId().toString();
                if(idArtista.equals(resultado.get(posPintura).getArtistId())){
                    cargarArtista(idArtista);
                    cargarPintura(resultado.get(posPintura).getImage(),imageViewPintura);
                } else{
                    Toast.makeText(getContext(), "falla", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void cargarPintura(String imagenEnPng, final ImageView imageView){

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

    public void cargarArtista(final String idArtista){

        //Referencia al padre artists
        firebaseDatabase = FirebaseDatabase.getInstance();
        //Obtengo la URL de la base de datos
        mDatabase = firebaseDatabase.getReference();
        //Apunto a la referencia de Artista
        referenceDataBase = mDatabase.child(NODO_ARTISTA);
        //Escuchador de tudo el arbol de artistas
        referenceDataBase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               if(dataSnapshot.exists()){

                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Artista artistaDelFor = snapshot.getValue(Artista.class);
                        Log.w(TAG,"ArtistaId"+ artistaDelFor.getArtistId());

                        if(artistaDelFor.getArtistId().equals(idArtista)){
                            textNombreArtista.setText(artistaDelFor.getName());
                            textNacionalidad.setText(artistaDelFor.getNationality());
                            textInfluencias.setText(artistaDelFor.getInfluenced_by());
                            textViewIdArtist.setText(artistaDelFor.getArtistId());
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "no existe dicho artista", Toast.LENGTH_SHORT).show();
            }
        });
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
