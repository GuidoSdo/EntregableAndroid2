package com.example.dh.entregableandroidguidosalcedo2.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class ArtistaContainer {
    @SerializedName("artists")
    private List<Artista> listaDeArtistas;

    public ArtistaContainer(List<Artista> listaDeArtistas) {
        this.listaDeArtistas = listaDeArtistas;
    }

    public List<Artista> getlistaDeArtistas() {
        return listaDeArtistas;
    }

}
