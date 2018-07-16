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

public class DAORetrofitPinturas {


    private String baseURL;
    private Retrofit retrofit;
    private Service service;

    public DAORetrofitPinturas() {

        baseURL = "https://api.myjson.com/bins/x858r/";
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(Service.class);
    }

    public void obtenerPinturasDeInternet(final ResultListener<List<Pintura>> escuchadorDelControlador) {

        Call<PinturaContainer> retrofitListener = service.getPinturas();
        retrofitListener.enqueue(new Callback<PinturaContainer>() {
            @Override
            public void onResponse(Call<PinturaContainer> call, Response<PinturaContainer> response) {

                PinturaContainer pinturaContainer = response.body();
                escuchadorDelControlador.finish(pinturaContainer.getListaDePinturas());
            }

            @Override
            public void onFailure(Call<PinturaContainer> call, Throwable t) {

                escuchadorDelControlador.finish(new ArrayList<Pintura>());
            }
        });
    }
}
