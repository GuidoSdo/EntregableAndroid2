package com.example.dh.entregableandroidguidosalcedo2.model.pojo;

public class Pintura {

    private String name;
    private String image;
    private String artistId;


    public Pintura(String name, String image, String artistId) {
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

    public String getArtistId() {
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
