package com.example.Assignment_Arelion.Repository1;
import com.example.Assignment_Arelion.Model.ActorsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface CustomActorsRepository {
    List<ActorsResponse> findAllActors(@Param("pageoffset") int pageoffset, @Param("pagesize") int pagesize);
    List<ActorsResponse> findActorsInPageByName(@Param("pageoffset") int pageoffset, @Param("pagesize") int pagesize, @Param("name") String name) throws JsonProcessingException;
    ActorsResponse findBynconst(String nconst);
}
