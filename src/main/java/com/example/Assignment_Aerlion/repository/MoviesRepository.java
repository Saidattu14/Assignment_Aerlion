package com.example.Assignment_Aerlion.repository;
import com.example.Assignment_Aerlion.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MoviesRepository extends JpaRepository<Movies, Long>{
    /**
     * This method is SQL Query Where it finds a movie data with respective movie id.
     * The Condition here is it finds movie id fetch that row.
     * @return the row
     */
    Movies findBytconst(String tconst);

    /**
     * This method is SQL Query Where it finds the all actors appearances data.
     * The Condition here is it finds movies data fetch that rows with limit and offset values.
     * @return the rows
     */
    @Query(value = "SELECT * FROM movies mv ORDER BY primarytitle ASC LIMIT :pagesize OFFSET :pageoffset",nativeQuery = true)
    List<Movies> findallmovies(@Param("pageoffset") int pageoffset, @Param("pagesize") int pagesize);
    /**
     * This method is SQL Query Where it finds the movie data in particular selected rows.
     * The Condition here is it fetchs rows and searches movie name in those rows and return it.
     * @return the row
     */
    @Query(value = "SELECT * From (SELECT * FROM movies ORDER BY primarytitle ASC LIMIT :pagesize OFFSET :pageoffset) AS Temp WHERE Temp.primarytitle =:name",nativeQuery = true)
    List<Movies> findMovieInPage(@Param("pageoffset") int pageoffset, @Param("pagesize") int pagesize, @Param("name") String name);

}
