package com.example.Assignment_Aerlion.model;

public class Appearance<mv> {

    public ActorAppearance getAppearances() {
        return appearances;
    }

    public void setAppearances(ActorAppearance appearances) {
        this.appearances = appearances;
    }

    private ActorAppearance appearances;
    private Movies movie;

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }

    public Appearance(ActorAppearance list,Movies movie) {
        this.appearances = list;
        this.movie = movie;
    }
}
