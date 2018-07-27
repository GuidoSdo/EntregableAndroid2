package com.example.dh.entregableandroidguidosalcedo2.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.dh.entregableandroidguidosalcedo2.model.dao.DAOArchivos;
import com.example.dh.entregableandroidguidosalcedo2.model.dao.DAORetrofitPinturas;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Pintura;
import com.example.dh.entregableandroidguidosalcedo2.utils.ResultListener;

import java.util.List;

public class ControllerPintura {

    private Context context;




        public void obtenerPintura(final ResultListener<List<Pintura>> escuchadorDeLaVista) {

            ResultListener<List<Pintura>>escuchadorDelControlador = new ResultListener<List<Pintura>>() {
                @Override
                public void finish(List<Pintura> resultado) {
                    escuchadorDeLaVista.finish(resultado);
                }
            };

            List<Pintura> pinturas;
            if(hayInternet()){
                DAORetrofitPinturas daoRetrofitPinturas = new DAORetrofitPinturas();
                daoRetrofitPinturas.obtenerPinturasDeInternet(escuchadorDelControlador);
            }else{
                DAOArchivos daoArchivos = new DAOArchivos();
                pinturas = daoArchivos.obtenerProductosDeArchivo();
                escuchadorDeLaVista.finish(pinturas);
            }

        }

    public boolean hayInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            Toast.makeText(context, "no hay internet", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public ControllerPintura(Context context) {
        this.context = context;
    }


}


