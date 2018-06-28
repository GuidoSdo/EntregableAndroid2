package com.example.dh.entregableandroidguidosalcedo2.service;

import com.example.dh.entregableandroidguidosalcedo2.model.pojo.PinturaContainer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("/bins/x858r/")
    Call<PinturaContainer> getPinturas();



}
