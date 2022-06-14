package com.example.Assignment_Arelion.Components;

import com.example.Assignment_Arelion.Model.Movies;
import com.example.Assignment_Arelion.Repository.MoviesRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileReader;
import java.util.List;

@Component
@Slf4j
public class MoviesDataLoader {

    @Autowired
    MoviesRepository moviesRepository;

    @PostConstruct
    public void loadMoviesData()
    {
        try {
            moviesRepository.CreateMovieTable();
        }
        catch (Exception e)
        {
            log.error(String.valueOf(e));
        }
        try {

            FileReader filereader = new FileReader("C:\\Users\\nagas\\Desktop\\Assignment_Aerlion\\CSVFiles\\test.movies.csv");
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData) {
                String tconst = row[0];
                String titleType = row[1];
                String primaryTitle = row[2];
                String originalTitle = row[3];
                boolean isAdult = Boolean.parseBoolean(row[4]);
                int startYear = Integer.parseInt(row[5]);
                int endYear = Integer.parseInt(row[6]);
                int runtimeMinutes = Integer.parseInt(row[7]);
                String genres = row[8];
                moviesRepository.save(new Movies(tconst,titleType,primaryTitle,originalTitle,isAdult,startYear,endYear,runtimeMinutes,genres));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @PreDestroy
    public void removeMoviesData()
    {
        try {
            moviesRepository.deleteAll();
        }
        catch (Exception e)
        {
            log.error(String.valueOf(e));
        }
    }
}
