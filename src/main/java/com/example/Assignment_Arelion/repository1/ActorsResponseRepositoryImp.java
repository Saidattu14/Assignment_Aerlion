package com.example.Assignment_Arelion.Repository1;

import com.example.Assignment_Arelion.Model.ActorsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
public class ActorsResponseRepositoryImp implements CustomActorsRepository{

    @PersistenceContext
    private EntityManager entityManager;

    public ActorsResponseRepositoryImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<ActorsResponse> findAllActors(@Param("pageoffset") int pageoffset, @Param("pagesize") int pagesize)
    {
       try {
           Query q = this.entityManager.createNativeQuery("SELECT ac.nconst, ac.primaryname FROM actors ac ORDER BY primaryname ASC LIMIT :pagesize OFFSET :pageoffset", ActorsResponse.class);
           q.setParameter("pagesize" ,pagesize);
           q.setParameter("pageoffset",pageoffset);
           return q.getResultList();
       }
       catch (Exception e)
       {
           return Collections.emptyList();
       }
    }

    @Override
    public List findActorsInPageByName(@Param("pageoffset") int pageoffset, @Param("pagesize") int pagesize, @Param("name") String name) throws JsonProcessingException {
        try {
             Query q = this.entityManager.createNativeQuery("SELECT" +
                            " Temp.nconst, Temp.primaryname" +
                            " From (SELECT * FROM actors ORDER BY primaryname ASC LIMIT :pagesize OFFSET :pageoffset) AS Temp" +
                            " WHERE Temp.primaryname =:name",ActorsResponse.class);
            q.setParameter("pagesize" ,pagesize);
            q.setParameter("pageoffset",pageoffset);
            q.setParameter("name",name);
            return q.getResultList();
        }
        catch (Exception e)
        {
            return Collections.emptyList();
        }
    }

    @Override
    public ActorsResponse findBynconst(String nconst)
    {
        try {
            Query q = this.entityManager.createNativeQuery("SELECT Temp.nconst, Temp.primaryname From actors Temp WHERE Temp.nconst =:nconst",ActorsResponse.class);
            q.setParameter("nconst",nconst);
            List<ActorsResponse> list =  q.getResultList();
            return list.get(0);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
