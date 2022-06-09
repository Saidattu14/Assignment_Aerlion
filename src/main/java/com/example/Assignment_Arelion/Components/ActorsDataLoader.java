package com.example.Assignment_Arelion.Components;

import com.example.Assignment_Arelion.model.Actors;
import com.example.Assignment_Arelion.repository.ActorsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.io.FileReader;
import java.util.List;
import com.opencsv.*;

@Component
@Slf4j
public class ActorsDataLoader {

    @Autowired
    ActorsRepository actorsRepository;

    @PostConstruct
    public void loadActorsData()
    {
        try {
            actorsRepository.CreateActorTable();
        }
        catch (Exception e)
        {
            log.error(String.valueOf(e));
        }

        try {

            FileReader filereader = new FileReader("C:\\Users\\nagas\\Desktop\\Assignment_Aerlion\\CSVFiles\\test.actors.csv");
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData) {
                String nconst = row[0];
                String primaryName = row[1];
                int birthYear = Integer.parseInt(row[2]);
                int deathYear = Integer.parseInt(row[3]);
                String[] primaryProfession = row[4].split(",");
                String[] knownForTitles = row[5].split(",");
                actorsRepository.save(new Actors(nconst,primaryName,birthYear,deathYear,primaryProfession,knownForTitles));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @PreDestroy
    public void removeData()
    {
        try {
            actorsRepository.deleteAll();
        }
        catch (Exception e)
        {
            log.error(String.valueOf(e));
        }

    }
}
