package com.example.Assignment_Arelion.Model;

import javax.persistence.*;


@Entity
@Table(name = "actor_appearances")
public class ActorAppearance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int ordering;
    private String tconst;
    private String nconst;
    private String category;
    private String job;
    private String characters;


    public ActorAppearance() {
    }


    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNconst() {
        return nconst;
    }

    public void setNconst(String nconst) {
        this.nconst = nconst;
    }

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * This is a class Constructor of the ActorAppearance.
     */
    public ActorAppearance(String tconst, String nconst, String category, String job, String characters, int ordering) {
        this.tconst = tconst;
        this.nconst = nconst;
        this.category = category;
        this.job = job;
        this.characters = characters;
        this.ordering = ordering;
    }



}
