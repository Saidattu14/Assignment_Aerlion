package com.example.Assignment_Arelion.Model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import javax.persistence.Entity;
import javax.persistence.Id;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class ActorsResponse{

    @Id
    private String nconst;
    private String primaryname;

    public ActorsResponse() {
    }

    public ActorsResponse(String nconst, String primaryname) {
        this.nconst = nconst;
        this.primaryname = primaryname;
    }

    public String getNconst() {
        return nconst;
    }

    public String getPrimaryname() {
        return primaryname;
    }

    public void setNconst(String nconst) {
        this.nconst = nconst;
    }

    public void setPrimaryname(String primaryname) {
        this.primaryname = primaryname;
    }

    @Override
    public String toString() {
        return "ActorsResponse{" +
                "nconst='" + nconst + '\'' +
                ", primaryname='" + primaryname + '\'' +
                '}';
    }
}
