package com.example.dh.entregableandroidguidosalcedo2.service;

import com.example.dh.entregableandroidguidosalcedo2.model.pojo.PinturaContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DH on 11/6/2018.
 */

public interface Service {

    // TODO - Esto hay que cambiarlo según la API
    @GET("top-headlines")
    Call<PinturaContainer> getArticulosPorCategoria(@Query("apiKey") String apiKey,
                                                    @Query("sources") String sources);

    @GET("everything")
    Call<PinturaContainer> getArticulosPorTema(@Query("apiKey") String apiKey,
                                               @Query("q") String tema,
                                               @Query("language") String idioma);

    @GET("sources")
    Call<PinturaContainer> getCategorias(@Query("apiKey") String apiKey);
}
