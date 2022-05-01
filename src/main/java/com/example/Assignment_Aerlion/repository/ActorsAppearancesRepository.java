package com.example.Assignment_Aerlion.repository;
import com.example.Assignment_Aerlion.model.ActorAppearance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ActorsAppearancesRepository extends JpaRepository<ActorAppearance, Long> {
    /**
     * This method is SQL Query Where it finds the actors appearances data.
     * The Condition here is it finds actor id and movie id fetch that row.
     * @return the row
     */
    @Query(value = "SELECT * From (SELECT * FROM actor_appearances ac WHERE ac.nconst =:nconst AND ac.tconst =:tconst) as Temp OFFSET :pageoffset",nativeQuery = true)
    ActorAppearance findactorappearnces(@Param("nconst") String nconst,@Param("tconst") String tconst, @Param("pageoffset") int pageoffset);
}

