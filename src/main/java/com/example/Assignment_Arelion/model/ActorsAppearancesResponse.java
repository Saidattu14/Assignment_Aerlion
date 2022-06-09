package com.example.Assignment_Arelion.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ActorsAppearancesResponse {

    @Id
    private String tconst;
    private String originaltitle;
    private String characters;

    public ActorsAppearancesResponse() {
    }

    public ActorsAppearancesResponse(String tconst, String originaltitle, String characters) {
        this.tconst = tconst;
        this.originaltitle = originaltitle;
        this.characters = characters;
    }

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public String getOriginaltitle() {
        return originaltitle;
    }

    public void setOriginaltitle(String originaltitle) {
        this.originaltitle = originaltitle;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

}
