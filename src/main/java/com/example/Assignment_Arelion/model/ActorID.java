package com.example.Assignment_Arelion.model;

import java.util.List;


public class ActorID {

      private List<Movies> knownfortitles;
      private String nconst;
      private String primaryname;
      private long birthyear;
      private long deathyear;
      private String[] primaryprofession;

    public ActorID() {
    }

    /**
     * This is a class constructor where is assign data for the data in the class variables.
     */
    public ActorID(Actors m1, List<Movies> list) {
        this.nconst = m1.getNconst();
        this.primaryname = m1.getPrimaryname();
        this.birthyear = m1.getBirthyear();
        this.deathyear = m1.getDeathyear();
        this.primaryprofession = m1.getPrimaryprofession();
        this.knownfortitles = list;
    }

    public String getNconst() {
        return nconst;
    }

    public List<Movies> getKnownfortitles() {
        return knownfortitles;
    }

    public long getDeathyear() {
        return deathyear;
    }

    public long getBirthyear() {
        return birthyear;
    }

    public String[] getPrimaryprofession() {
        return primaryprofession;
    }

    public String getPrimaryname() {
        return primaryname;
    }
}
