package com.example.Assignment_Aerlion.repository;
import com.example.Assignment_Aerlion.model.ActorAppearance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorsAppearancesRepository extends JpaRepository<ActorAppearance, Long> {
    @Query(value = "SELECT * FROM actor_appearances ac WHERE ac.nconst = ?1 and ac.tconst = ?2",nativeQuery = true)
    ActorAppearance findactorappearnces(String nconst, String tconst);
}

