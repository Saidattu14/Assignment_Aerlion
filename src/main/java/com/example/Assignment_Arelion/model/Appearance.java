package com.example.Assignment_Arelion.model;

public class Appearance {

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
    /**
     * This is a class constructor where is assign appearances data for the class variables.
     */
    public Appearance(ActorAppearance list,Movies movie) {
        this.appearances = list;
        this.movie = movie;
    }
}
