package com.example.dh.entregableandroidguidosalcedo2.model.pojo;



public class Artista {
    private Integer artistId;
    private String name;
    private String nationality;
    private String Influenced_by;

    public Artista(Integer artistId, String name, String nationality, String influenced_by) {
        this.artistId = artistId;
        this.name = name;
        this.nationality = nationality;
        this.Influenced_by = influenced_by;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getInfluenced_by() {
        return Influenced_by;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "artistId=" + artistId +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", Influenced_by='" + Influenced_by + '\'' +
                '}';
    }
}
