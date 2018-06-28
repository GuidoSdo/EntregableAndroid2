package com.example.dh.entregableandroidguidosalcedo2.model.pojo;

public class Pintura {

    private String name;
    private String image;
    private Integer artistId;


    public Pintura(String name, String image, Integer artistId) {
        this.name = name;
        this.image = image;
        this.artistId = artistId;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public Integer getArtistId() {
        return artistId;
    }

    @Override
    public String toString() {
        return "Pintura{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", artistId=" + artistId +
                '}';
    }
}
