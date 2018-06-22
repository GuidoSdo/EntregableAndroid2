package com.example.dh.entregableandroidguidosalcedo2.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PinturaContainer {
    @SerializedName("paints")
    private List<Pintura> listaDePintura;

    public PinturaContainer(List<Pintura> listaDePinturas) {
        this.listaDePintura = listaDePinturas;
    }

    public List<Pintura> getListaDePintura() {
        return listaDePintura;
    }

}
