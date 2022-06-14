package com.example.Assignment_Arelion.Components;

import com.example.Assignment_Arelion.Model.ActorAppearance;
import com.example.Assignment_Arelion.Repository.ActorsAppearancesRepository;
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
public class ActorsAppearancesDataLoader {

    @Autowired
    ActorsAppearancesRepository actorsAppearancesRepository;

    @PostConstruct
    public void loadActorsData()
    {
        try {
            actorsAppearancesRepository.CreateAppearanceTable();
        }
        catch (Exception e)
        {
            log.error(String.valueOf(e));
        }
        try {

            FileReader filereader = new FileReader("C:\\Users\\nagas\\Desktop\\Assignment_Aerlion\\CSVFiles\\test.appearances.csv");
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();
            for (String[] row : allData) {
                String tconst = null;
                int ordering = 0;
                String nconst = null;
                String category = null;
                String job = null;
                String characters = null;
                for(int i = 0; i<row.length;i++)
                {
                    if(!row[i].equals("\\N"))
                    {
                        if(i == 0)
                        {
                            tconst = row[0];
                        }
                        else if(i == 1)
                        {
                            ordering = Integer.parseInt(row[1]);
                        }
                        else if( i== 2)
                        {
                            nconst = row[2];
                        }
                        else if(i== 3)
                        {
                            category = row[3];
                        }
                        else if(i == 4)
                        {
                            job = row[4];
                        }
                        else if(i==5)
                        {
                            characters = row[5];
                        }
                    }
                }
                actorsAppearancesRepository.save(new ActorAppearance(tconst,nconst,category,job,characters,ordering));
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
            actorsAppearancesRepository.deleteAll();
        }
        catch (Exception e)
        {
            log.error(String.valueOf(e));
        }

    }
}
