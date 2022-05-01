package com.example.Assignment_Aerlion.Services;

import com.example.Assignment_Aerlion.model.*;
import com.example.Assignment_Aerlion.repository.ActorsAppearancesRepository;
import com.example.Assignment_Aerlion.repository.ActorsRepository;
import com.example.Assignment_Aerlion.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ActorsMoviesService {

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private ActorsRepository actorsRepository;

    @Autowired
    private ActorsAppearancesRepository actorsAppearancesRepository;

    private Actors actors;
    private Movies movies;


    public ActorsMoviesService(MoviesRepository mr, ActorsRepository ar, ActorsAppearancesRepository aar)
    {

        this.moviesRepository = mr;
        this.actorsRepository = ar;
        this.actorsAppearancesRepository = aar;

    }
    /**
     * This method returns the ActorID object for the given id.
     * @param @id the actor_id
     * @return the ActorID object, or {@code null} if none
     */
    public ActorID ActorDataWithId(String id) {

        try {
            actors = this.actorsRepository.findBynconst(id);
            List<Movies> list = new ArrayList<>();
            Arrays.stream(actors.getKnownfortitles())
                    .forEach(x-> list.add(this.moviesRepository.findBytconst(x)));
            return new ActorID(actors, list);
        }
        catch (NullPointerException e) {
//            System.out.println(e);
            return null;
        }
    }
    /**
     * This method returns the List of All Actors object for the given page, page_size and name(wildcard search).
     * @param @page, @page_size, @name
     * @return the List Actors object, or {@code empty List} if none
     */
    public List<Actors> AllActors(int page,int page_size,String name){

        int pageoffset = 0;
        List<Actors> ListActors = new ArrayList<>();
        for(int i = 0; i<page;i++)
        {
            pageoffset = page * i+ 1000;
        }

        try {
            if (name.length() == 0) {
                ListActors.addAll(this.actorsRepository.findAllActors(pageoffset, page_size));
            } else ListActors.addAll(this.actorsRepository.findActorInPage(pageoffset, page_size, name));
        }
        catch (Exception e) {
//            System.out.println(e);
            return null;
        }
     return ListActors;
    }
    /**
     * This method returns the List of All ActorsAppearances object for the given actor id with page and page_size.
     * @param @id, @page, @page_size
     * @return the List Appearances objects, or {@code empty List} if none
     */
    public List<Appearance> ActorAppearances(String id, int page, int page_size) {

        List<ActorAppearance> list1 = new ArrayList<>();
        List<Appearance> list2 = new ArrayList<>();
        try
        {
            int pageoffset1 = 0;
            for(int i = 0; i<page;i++)
            {
                pageoffset1 = page * i+ 1000;
            }
            actors = this.actorsRepository.findBynconst(id);
            int pageoffset = pageoffset1;
            String[] knownfortitles = actors.getKnownfortitles();
            for(int i= 0; i<knownfortitles.length;i++)
            {
                if(i < page_size)
                {
                    list1.add(this.actorsAppearancesRepository
                            .findactorappearnces(actors
                                    .getNconst(),knownfortitles[i],pageoffset));
                }
            }

            for (ActorAppearance aap : list1) {
                if (aap != null) {
                    list2.add(new Appearance(aap, this.moviesRepository.findBytconst(aap.getTconst())));
                }
            }

        }
        catch (Exception e) {
//            System.out.println(e);
            return null;
        }
        return list2;
    }
    /**
     * This method returns the Movies object for the given page, page_size and name(wildcard search).
     * @param @page, @page_size, @name
     * @return the List Movies object, or {@code empty List} if none
     */
    public List<Movies> AllMovies(int page, int page_size, String name) {

        try
        {   List<Movies> Listmovies = new ArrayList<>();
            int pageoffset = 0;
            for(int i = 0; i<page;i++)
            {
                pageoffset = page * i+ 1000;
            }
            if(name.length() == 0)
            {
                Listmovies.addAll(this.moviesRepository.findallmovies(pageoffset, page_size));
            }
            else
            {
                Listmovies.addAll(this.moviesRepository.findMovieInPage(pageoffset, page_size, name));
            }
            return Listmovies;
        }
        catch (Exception e) {
//            System.out.println(e);
            return null;
        }
    }
    /**
     * This method returns the Movie object for the given id.
     * @param @id the Movie id
     * @return the Movie object, or {@code null} if none
     */

    public Movies MovieWithId(String id) {
        try
        {
            movies = this.moviesRepository.findBytconst(id);
            if(movies == null)
            {
                return null;
            }
        }
        catch (Exception e) {
//            System.out.println(e);
            return null;
        }
        return movies;

    }

}