const {Client} = require('pg');
var Query = require('./Query')
var Query1 = require('./Query1');
var Query2 = require('./Query2');
const csv = require('csv-parser');
const fs = require('fs');
const createCsvWriter = require('csv-writer').createObjectCsvWriter;
const client = new Client({
    user: 'user',
    host: 'localhost',
    password: '123456',
    port: 5432,
    database : 'default_database'
  })
const create_table_movies = `CREATE TABLE movies(
    tconst varchar(255) NOT NULL PRIMARY KEY,
    titleType varchar(255),
    endYear int,
	  primaryTitle varchar(255),
	  originalTitle varchar(255),
	  genres varchar(255),
	  runtimeMinutes int,
	  startYear int,
	  isAdult boolean
)`;
const create_table_actors = `CREATE TABLE actors(
    nconst varchar(255) NOT NULL PRIMARY KEY,
    primaryName varchar(255),
    birthYear int,
    deathYear int,
    primaryProfession text[],
    knownForTitles text[]
)`;

const create_table_actor_appearances = `CREATE TABLE actor_appearances(
  id SERIAL NOT NULL PRIMARY KEY,
  nconst varchar(255),
  tconst varchar(255),
  ordering int,
  category varchar(255),
  job varchar(255),
  characters varchar(255)
)`;

  try 
  {
    client.connect()
    console.log("Database_connected")
  }catch (error) 
  {
    console.log("DataBase is not Connected")
  }
var count = 0;
const visited_movies = []
const insert_data = (client) => {
  fs.createReadStream('test.movies.csv')
    .pipe(csv())
    .on('data', (row) => { 
      if(row.isAdult == '0')
      {
        row.isAdult = false;
      }
      else
      {
        row.isAdult = true;
      }
      row.startYear = parseInt(row.startYear);
      if(row.runtimeMinutes == '\\N')
      {
        row.runtimeMinutes = 0;
      }
      else
      {
        row.runtimeMinutes = parseInt(row.runtimeMinutes);
      }
      if(row.endYear == '\\N')
      {
        row.endYear = 0;
      }
      else
      {
        row.endYear = parseInt(row.endYear);
      }
      Query(client,row);
  })
  .on('end', () => {
    console.log('CSV file successfully processed');
  });
}

const inserting_actors_data = (client,visited_movies) => {
  fs.createReadStream('test.actors.csv')
    .pipe(csv())
    .on('data', (row) => { 
      let text =  []
      let newknownForTitles = []
      text = row.knownForTitles.split(",");
      for(let i= 0; i<text.length;i++)
      {
         if(visited_movies[text[i]] == 1)
         {
           newknownForTitles.push(text[i])
         }
      }
      row.knownForTitles = newknownForTitles;
      if(newknownForTitles.length != 0)
      {
        count = count + 1;
      }
      row.primaryProfession = row.primaryProfession.split(",")
      if(row.birthYear == '\\N' || row.birthYear == '\N' || row.birthYear == NaN)
      {
        row.birthYear = 0;
      }
      else
      {
        row.birthYear = parseInt(row.birthYear);
      }
      if(row.deathYear == '\\N' || row.deathYear ==  '\N' || row.deathYear ==  NaN)
      {
        row.deathYear = 0;
      }
      else
      {
        row.deathYear = parseInt(row.deathYear);
      }
      
      Query1(client,row);
      
  })
  .on('end', () => {
    console.log(count)
  });
}


const setting_actors_data = (client) => {
  fs.createReadStream('test.movies.csv')
    .pipe(csv())
    .on('data', (row) => { 
      visited_movies[row.tconst] = 1;
  })
  .on('end', () => {
     inserting_actors_data(client,visited_movies)
  });
}

const actors_appearnces_data = (client) => {
  fs.createReadStream('test.principals.csv')
    .pipe(csv())
    .on('data', (row) => { 
      if(row.category == '\\N' || row.category == '\N' || row.category == NaN)
      {
        row.category = '';
      }
      if(row.job == '\\N' || row.job == '\N' || row.job == NaN)
      {
        row.job = '';
      }
      if(row.characters == '\\N' || row.characters == '\N' || row.characters == NaN)
      {
        row.characters = '';
      }
      Query2(client,row)
  })
  .on('end', () => {
     console.log("over")
  });
}



const services = async() => {
  
  client.query("SELECT * FROM actors",(err,res) => {
      console.log(res)
    });

// client.query("drop table actor_appearances",(err,res) => {
//     console.log(res,err)
//     client.query(create_table_actor_appearances,function(err,res) {
//       if(err)
//       {
//         console.log(err);
//       }
//       else
//       {
//         console.log("Table Created1");
//         actors_appearnces_data(client);
//       }
//     });
// })
  
 
//   // client.query("SELECT * FROM movies",(err,res) => {
//   //   console.log(res)
//   // })
 

//   // client.query("SELECT * FROM actor_appearances",(err,res) => {
//   //   console.log(res,err)
//   // })
//   client.query("drop table movies",(err,res) => {
//     console.log(res,err)
//     client.query(create_table_movies,function(err,res) {
//       if(err)
//       {
//         console.log(err);
//       }
//       else
//       {
//        console.log("Table Created1");
//        insert_data(client);
//       }
//     });
//   })
// client.query("drop table actors",(err,res) => {
//     console.log(res,err)
//     client.query(create_table_actors,function(err,res) {
//       if(err)
//       {
//         console.log(err);
//       }
//       else
//       {
//        console.log("Table Created1");
//        setting_actors_data(client);
//       }
//     });
//   })

  
}
services()