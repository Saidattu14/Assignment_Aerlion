package com.example.Assignment_Aerlion.repository;
import com.example.Assignment_Aerlion.model.Actors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorsRepository extends JpaRepository<Actors, Long> {
      /**
       * This method is SQL Query Where it finds the actor data.
       * The Condition here is it finds actor id fetch that row.
       * @return the row
       */
      Actors findBynconst(String nconst);

      /**
       * This method is SQL Query Where it finds the all actors data.
       * The Condition here is it finds all actors data with limit and offset..
       * @return the rows
       */
      @Query(value = "SELECT * FROM actors ac ORDER BY primaryname ASC LIMIT :pagesize OFFSET :pageoffset",nativeQuery = true)
      List<Actors> findAllActors(@Param("pageoffset") int pageoffset, @Param("pagesize") int pagesize);

      /**
       * This method is SQL Query Where it finds the actor in particular page.
       * The Condition here is it finds particular row and search the data with selected username.
       * @return the row
       */
      @Query(value = "SELECT * From (SELECT * FROM actors ORDER BY primaryname ASC LIMIT :pagesize OFFSET :pageoffset) AS Temp WHERE Temp.primaryname =:name",nativeQuery = true)
      List<Actors> findActorInPage(@Param("pageoffset") int pageoffset,@Param("pagesize") int pagesize,@Param("name") String name);

}

