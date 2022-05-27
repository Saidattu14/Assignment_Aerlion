package com.example.Assignment_Arelion.repository;
import com.example.Assignment_Arelion.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
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

    /**
     * This method is SQL Query Where it creates Movies Table.
     */
    @Modifying
    @Transactional
    @Query(value = "CREATE TABLE movies(\n" +
            "    tconst varchar(255) NOT NULL PRIMARY KEY,\n" +
            "    titleType varchar(255),\n" +
            "    endYear int,\n" +
            "\t  primaryTitle varchar(255),\n" +
            "\t  originalTitle varchar(255),\n" +
            "\t  genres varchar(255),\n" +
            "\t  runtimeMinutes int,\n" +
            "\t  startYear int,\n" +
            "\t  isAdult boolean\n" +
            ")",nativeQuery = true)
    void CreateMovieTable();

    /**
     * This method is SQL Query Where it Inserts Actor Data in Actors Table.
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO movies" +
            "(tconst," +
            "titleType," +
            "endYear," +
            "primaryTitle," +
            "originalTitle," +
            "genres," +
            "runtimeMinutes," +
            "startYear," +
            "isAdult)" + " "+
            "VALUES(" +
            ":tconst," +
            ":titleType," +
            ":endYear," +
            ":primaryTitle," +
            ":originalTitle," +
            ":genres," +
            ":runtimeMinutes," +
            ":startYear," +
            ":isAdult)",nativeQuery = true)
    void InsertMovieTable(@Param("tconst") String tconst,
                            @Param("titleType") String titleType,
                            @Param("endYear") int endYear,
                            @Param("primaryTitle") String primaryTitle,
                            @Param("originalTitle") String originalTitle,
                            @Param("genres") String genres,
                            @Param("runtimeMinutes") int runtimeMinutes,
                            @Param("startYear") int startYear,
                            @Param("isAdult") boolean isAdult);
}
