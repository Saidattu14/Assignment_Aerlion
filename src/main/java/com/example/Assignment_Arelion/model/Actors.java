package com.example.Assignment_Arelion.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "actors")
public class Actors {
    @Id
    private String nconst;
    private String primaryname;
    private long birthyear;
    private long deathyear;
    @Column(columnDefinition = "text[]")
    @Type(type = "com.example.Assignment_Arelion.mapping.CustomStringArrayType")
    private String[] primaryprofession;
    @Column(columnDefinition = "text[]")
    @Type(type = "com.example.Assignment_Arelion.mapping.CustomStringArrayType")
    private String[] knownfortitles;

    public Actors() {
    }

    public String getNconst() {
        return nconst;
    }

    public void setNconst(String nconst) {
        this.nconst = nconst;
    }

    public String getPrimaryname() {
        return primaryname;
    }

    public void setPrimaryname(String primaryname) {
        this.primaryname = primaryname;
    }

    public long getBirthyear() {
        return birthyear;
    }

    public long getDeathyear() {
        return deathyear;
    }

    public String[] getPrimaryprofession() {
        return primaryprofession;
    }

    public void setPrimaryprofession(String[] primaryprofession) {
        this.primaryprofession = primaryprofession;
    }

    public void setDeathyear(long deathyear) {
        this.deathyear = deathyear;
    }

    public String[] getKnownfortitles() {
        return knownfortitles;
    }

    public void setKnownfortitles(String[] knownfortitles) {
        this.knownfortitles = knownfortitles;
    }

    public void setBirthyear(long birthyear) {
        this.birthyear = birthyear;
    }

    /**
     * This is a class constructor where is assign actors data the class variables.
     */
    public Actors(String nconst,String primaryname,long birthyear,long deathyear,String[] primaryprofession,String[] knownfortitles) {
        this.nconst = nconst;
        this.primaryname = primaryname;
        this.birthyear = birthyear;
        this.deathyear = deathyear;
        this.primaryprofession = primaryprofession;
        this.knownfortitles = knownfortitles;
    }
}
