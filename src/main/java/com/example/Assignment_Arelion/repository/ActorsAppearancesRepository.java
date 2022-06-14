package com.example.Assignment_Arelion.Repository;
import com.example.Assignment_Arelion.Model.ActorAppearance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
public interface ActorsAppearancesRepository extends JpaRepository<ActorAppearance, Long> {

    /**
     * This method is SQL Query Where it finds the actors appearances data.
     * The Condition here is it finds actor id and movie id fetch that row.
     * @return the row
     */
    @Query(value = "SELECT * From (SELECT * FROM actor_appearances ac WHERE ac.nconst =:nconst AND ac.tconst =:tconst) as Temp OFFSET :pageoffset",nativeQuery = true)
    ActorAppearance findactorappearnces(@Param("nconst") String nconst,@Param("tconst") String tconst, @Param("pageoffset") int pageoffset);


    /**
     * This method is SQL Query Where it creates ActorsAppearances Table.
     */
    @Modifying
    @Transactional
    @Query(value = "CREATE TABLE actor_appearances(\n" +
            "  id SERIAL NOT NULL PRIMARY KEY,\n" +
            "  nconst varchar(255),\n" +
            "  tconst varchar(255),\n" +
            "  ordering int,\n" +
            "  category varchar(255),\n" +
            "  job varchar(255),\n" +
            "  characters varchar(255)\n" +
            ")",nativeQuery = true)
    void CreateAppearanceTable();

    /**
     * This method is SQL Query Where it Inserts ActorAppearances Data in ActorsAppearances Table.
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO actor_appearances" +
            "(nconst," +
            "tconst," +
            "ordering," +
            "category," +
            "job," +
            "characters" +
            ")"+" " +
            "VALUES"+
            "(:nconst," +
            ":tconst," +
            ":ordering," +
            ":category," +
            ":job," +
            ":characters" +
            ")",nativeQuery = true)
    void InsertActorsAppearancesTable(@Param("nconst") String nconst,
                            @Param("tconst") String tconst,
                            @Param("ordering") int ordering,
                            @Param("category") String category,
                            @Param("job") String job,
                            @Param("characters") String characters);
}

