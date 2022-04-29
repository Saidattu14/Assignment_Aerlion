package com.example.Assignment_Aerlion.repository;
import com.example.Assignment_Aerlion.model.Actors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorsRepository extends JpaRepository<Actors, Long> {
      Actors findBynconst(String nconst);
      @Query(value = "SELECT * FROM actors ac ORDER BY primaryname ASC LIMIT :pagesize OFFSET :pageoffset",nativeQuery = true)
      List<Actors> findAllActors(@Param("pageoffset") int pageoffset, @Param("pagesize") int pagesize);

      @Query(value = "SELECT * From (SELECT * FROM actors ORDER BY primaryname ASC LIMIT :pagesize OFFSET :pageoffset) AS Temp WHERE Temp.primaryname =:name",nativeQuery = true)
      List<Actors> findActorInPage(@Param("pageoffset") int pageoffset,@Param("pagesize") int pagesize,@Param("name") String name);

//      @Query(value = "SELECT * FROM actors ac ORDER BY primaryname ASC LIMIT :pagesize OFFSET :pageoffset)",nativeQuery = true)
//      List<Actors> findActorInPage(@Param("pageoffset") int pageoffset,@Param("pagesize") int pagesize);



}

