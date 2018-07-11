package com.example.dh.entregableandroidguidosalcedo2.controller;

import com.example.dh.entregableandroidguidosalcedo2.model.dao.DAOArchivos;
import com.example.dh.entregableandroidguidosalcedo2.model.dao.DAORetrofit;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Pintura;
import com.example.dh.entregableandroidguidosalcedo2.utils.ResultListener;

import java.util.List;

public class ControllerPintura {

        public void obtenerPintura(final ResultListener<List<Pintura>> escuchadorDeLaVista) {

            ResultListener<List<Pintura>>escuchadorDelControlador = new ResultListener<List<Pintura>>() {
                @Override
                public void finish(List<Pintura> resultado) {
                    escuchadorDeLaVista.finish(resultado);
                }
            };

            List<Pintura> pinturas;
            if(hayInternet()){
                DAORetrofit daoRetrofit = new DAORetrofit();
                daoRetrofit.obtenerPinturasDeInternet(escuchadorDelControlador);
            }else{
                DAOArchivos daoArchivos = new DAOArchivos();
                pinturas = daoArchivos.obtenerProductosDeArchivo();
                escuchadorDeLaVista.finish(pinturas);
            }

        }

        public Boolean hayInternet(){ return true; }

}


