package com.example.dh.entregableandroidguidosalcedo2.model.dao;

import com.example.dh.entregableandroidguidosalcedo2.model.pojo.Pintura;
import com.example.dh.entregableandroidguidosalcedo2.model.pojo.PinturaContainer;
import com.example.dh.entregableandroidguidosalcedo2.service.Service;
import com.example.dh.entregableandroidguidosalcedo2.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DH on 11/6/2018.
 */

public class DAORetrofit {


    private String baseURL;
    private Retrofit retrofit;
    private Service service;

    public DAORetrofit(){

        // TODO - Cambiar esto segun API
        baseURL = "https://newsapi.org/v2/";
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(Service.class);
    }

    public void obtenerPinturasDeInternet(final ResultListener<List<Pintura>> escuchadorDelControlador){

        Call<PinturaContainer> retrofitListener = service.getArticulosPorTema("6cc9dd144ae6445886c4667f1d8332cf", "q","es");
        retrofitListener.enqueue(new Callback<PinturaContainer>() {
            @Override
            public void onResponse(Call<PinturaContainer> call, Response<PinturaContainer> response) {

                PinturaContainer pinturaContainer = response.body();
                escuchadorDelControlador.finish(pinturaContainer.getListaDePintura());
            }

            @Override
            public void onFailure(Call<PinturaContainer> call, Throwable t) {

                escuchadorDelControlador.finish(new ArrayList<Pintura>());
            }
        });
    }

    // TODO - Cambiar esto segun la API
    public void obtenerPinturaPorPosicion(final Integer posicion){
        Call<PinturaContainer> retrofitListener = service.getArticulosPorTema("6cc9dd144ae6445886c4667f1d8332cf","hola","es");
        retrofitListener.enqueue(new Callback<PinturaContainer>() {
            @Override
            public void onResponse(Call<PinturaContainer> call, Response<PinturaContainer> response) {

            }

            @Override
            public void onFailure(Call<PinturaContainer> call, Throwable t) {

            }
        });
    }
}
