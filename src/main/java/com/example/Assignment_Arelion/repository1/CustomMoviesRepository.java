package com.example.Assignment_Arelion.Repository1;

import com.example.Assignment_Arelion.Model.MoviesResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomMoviesRepository {
    List<MoviesResponse> findAllMovies(@Param("pageoffset") int pageoffset, @Param("pagesize") int pagesize);
    MoviesResponse findBytconst(String tconst);
    List<MoviesResponse> findMoviesInPageByName(@Param("pageoffset") int pageoffset, @Param("pagesize") int pagesize, @Param("name") String name);
}
