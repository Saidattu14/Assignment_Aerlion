package com.example.Assignment_Arelion.Repository1;

import com.example.Assignment_Arelion.Model.MoviesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
@Slf4j
public class MoviesResponseRepositoryImp implements CustomMoviesRepository{

    @PersistenceContext
    private EntityManager entityManager;

    public MoviesResponseRepositoryImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MoviesResponse> findAllMovies(int pageoffset, int pagesize) {
        try {
            Query q = this.entityManager.createNativeQuery("SELECT mv.originaltitle, mv.tconst, mv.startyear FROM movies mv ORDER BY primarytitle ASC LIMIT :pagesize OFFSET :pageoffset",MoviesResponse.class);
            q.setParameter("pagesize" ,pagesize);
            q.setParameter("pageoffset",pageoffset);
            return q.getResultList();
        }
        catch (Exception e)
        {
            log.error(String.valueOf(e));
            return Collections.emptyList();
        }
    }

    @Override
    public MoviesResponse findBytconst(String tconst) {
        try {
            Query q = this.entityManager.createNativeQuery(
                    "SELECT mv.originaltitle, mv.tconst, mv.startyear FROM movies AS mv " +
                    "WHERE mv.tconst =:tconst",MoviesResponse.class);
            q.setParameter("tconst",tconst);
            List<MoviesResponse> list =  q.getResultList();
            return list.get(0);
        }
        catch (Exception e)
        {
            log.error(String.valueOf(e));
            return null;
        }
    }

    @Override
    public List<MoviesResponse> findMoviesInPageByName(int pageoffset, int pagesize, String name) {
        try {
            Query q = this.entityManager.createNativeQuery("SELECT Temp.originaltitle, Temp.tconst, Temp.startyear From " +
                    "(SELECT * FROM movies ORDER BY primarytitle ASC LIMIT :pagesize OFFSET :pageoffset) AS Temp " +
                    "WHERE Temp.originaltitle =:name",MoviesResponse.class);
            q.setParameter("pagesize" ,pagesize);
            q.setParameter("pageoffset",pageoffset);
            q.setParameter("name",name);
            return q.getResultList();
        }
        catch (Exception e)
        {
            log.error(String.valueOf(e));
            return Collections.emptyList();
        }
    }


}
