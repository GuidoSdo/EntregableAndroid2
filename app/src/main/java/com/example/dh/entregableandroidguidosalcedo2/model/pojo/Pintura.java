package com.example.dh.entregableandroidguidosalcedo2.model.pojo;

public class Pintura {

    private String name;
    private Integer image;
    private Integer artistId;


    public Pintura(String name, Integer image,Integer artistId) {
        this.name = name;
        this.image = image;
        this.artistId = artistId;
    }

    public Integer getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public Integer getArtistId() {
        return artistId;
    }
}
