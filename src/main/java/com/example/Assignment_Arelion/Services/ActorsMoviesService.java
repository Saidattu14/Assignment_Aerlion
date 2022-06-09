package com.example.Assignment_Arelion.Services;

import com.example.Assignment_Arelion.model.*;
import com.example.Assignment_Arelion.repository.ActorsAppearancesRepository;
import com.example.Assignment_Arelion.repository.ActorsRepository;
import com.example.Assignment_Arelion.repository.MoviesRepository;
import com.example.Assignment_Arelion.repository1.ActorsResponseRepositoryImp;
import com.example.Assignment_Arelion.repository1.MoviesResponseRepositoryImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ActorsMoviesService {

    @Autowired
    private MoviesResponseRepositoryImp moviesResponseRepositoryImp;

    @Autowired
    private ActorsResponseRepositoryImp actorsResponseRepositoryImp;

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private ActorsRepository actorsRepository;

    @Autowired
    private ActorsAppearancesRepository actorsAppearancesRepository;


    public ActorsMoviesService(MoviesResponseRepositoryImp moviesResponseRepositoryImp, ActorsResponseRepositoryImp actorsResponseRepositoryImp, MoviesRepository mr, ActorsRepository ar, ActorsAppearancesRepository aar)
    {
        this.moviesResponseRepositoryImp = moviesResponseRepositoryImp;
        this.actorsResponseRepositoryImp = actorsResponseRepositoryImp;
        this.moviesRepository = mr;
        this.actorsRepository = ar;
        this.actorsAppearancesRepository = aar;

    }


    /**
     * This method returns the ActorID object for the given id.
     * @param @id the actor_id
     * @return the ActorID object, or {@code null} if none
     */
    public ActorsResponse actorDataWithId(String id) {

        try {
            return this.actorsResponseRepositoryImp.findBynconst(id);
        }
        catch (NullPointerException e) {
            log.error(String.valueOf(e));
            return null;
        }
    }
    /**
     * This method returns the List of All Actors object for the given page, page_size and name(wildcard search).
     * @param @page, @page_size, @name
     * @return the List Actors object, or {@code empty List} if none
     */
    public List<ActorsResponse> allActors(int page,int page_size,String name){

        int pageoffset = 0;
        List<ActorsResponse> ListActors = new ArrayList<>();
        for(int i = 0; i<page;i++)
        {
            pageoffset = page * i+ 1000;
        }

        try {
            if (name.length() == 0) {

                return  actorsResponseRepositoryImp.findAllActors(pageoffset, page_size);
            }
            else
            {
              return actorsResponseRepositoryImp.findActorsInPageByName(pageoffset,page_size,name);
            }
        }
        catch (Exception e) {
            log.error(String.valueOf(e));
            return Collections.emptyList();
        }
    }
    /**
     * This method returns the List of All ActorsAppearances object for the given actor id with page and page_size.
     * @param @id, @page, @page_size
     * @return the List Appearances objects, or {@code empty List} if none
     */
    public List<ActorsAppearancesResponse> actorAppearances(String id, int page, int page_size) {

        List<ActorAppearance> list1 = new ArrayList<>();
        List<ActorsAppearancesResponse> list2 = new ArrayList<>();
        try
        {
            int pageoffset1 = 0;
            for(int i = 0; i<page;i++)
            {
                pageoffset1 = page * i+ 1000;
            }
            Actors actors = this.actorsRepository.findBynconst(id);
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
                    MoviesResponse mv = this.moviesResponseRepositoryImp.findBytconst(aap.getTconst());
                    list2.add(new ActorsAppearancesResponse(mv.getTconst(),mv.getOriginaltitle(),aap.getCharacters()));
                }
            }
        }
        catch (Exception e) {
            log.error(String.valueOf(e));
        }
        return list2;
    }


    /**
     * This method returns the Movies object for the given page, page_size and name(wildcard search).
     * @param @page, @page_size, @name
     * @return the List Movies object, or {@code empty List} if none
     */
    public List<MoviesResponse> allMovies(int page, int page_size, String name) {

        try
        {   List<Movies> listMovies = new ArrayList<>();
            int pageoffset = 0;
            for(int i = 0; i<page;i++)
            {
                pageoffset = page * i+ 1000;
            }
            if(name.length() == 0)
            {
                return this.moviesResponseRepositoryImp.findAllMovies(pageoffset, page_size);
            }
            else
            {
                return this.moviesResponseRepositoryImp.findMoviesInPageByName(pageoffset, page_size, name);
            }

        }
        catch (Exception e) {
            log.error(String.valueOf(e));
            return Collections.emptyList();
        }
    }


    /**
     * This method returns the Movie object for the given id.
     * @param @id the Movie id
     * @return the Movie object, or {@code null} if none
     */

    public MoviesResponse movieWithId(String id) {
        try
        {
            MoviesResponse mv = this.moviesResponseRepositoryImp.findBytconst(id);
            return mv;
        }
        catch (Exception e) {
            log.error(String.valueOf(e));
            return null;
        }
    }

}