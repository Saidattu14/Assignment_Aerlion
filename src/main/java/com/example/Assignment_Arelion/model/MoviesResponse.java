package com.example.Assignment_Arelion.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.persistence.Entity;
import javax.persistence.Id;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class MoviesResponse {

    @Id
    private String tconst;
    private String originaltitle;
    private long startyear;

    public MoviesResponse() {
    }

    public MoviesResponse(String tconst, String originaltitle, long startyear) {
        this.tconst = tconst;
        this.originaltitle = originaltitle;
        this.startyear = startyear;
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

    public long getStartyear() {
        return startyear;
    }

    public void setStartyear(long startyear) {
        this.startyear = startyear;
    }
}
