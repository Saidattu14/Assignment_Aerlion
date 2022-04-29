package com.example.Assignment_Aerlion.repository;


import com.example.Assignment_Aerlion.model.Actors;
import com.example.Assignment_Aerlion.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MoviesRepository extends JpaRepository<Movies, Long>{

    Movies findBytconst(String tconst);

    @Query(value = "SELECT * FROM movies mv ORDER BY primarytitle ASC LIMIT :pagesize OFFSET :pageoffset",nativeQuery = true)
    List<Movies> findallmovies(@Param("pageoffset") int pageoffset, @Param("pagesize") int pagesize);

    @Query(value = "SELECT * From (SELECT * FROM movies ORDER BY primarytitle ASC LIMIT :pagesize OFFSET :pageoffset) AS Temp WHERE Temp.primarytitle =:name",nativeQuery = true)
    List<Movies> findMovieInPage(@Param("pageoffset") int pageoffset, @Param("pagesize") int pagesize, @Param("name") String name);

}
