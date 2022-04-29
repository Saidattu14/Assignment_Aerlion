
const Query1 = (client,obj) => {
    const query = {
      name :'insert-user1',
      text: 'INSERT INTO actors(nconst,primaryName,birthYear,deathYear,primaryProfession,knownForTitles) VALUES($1, $2,$3,$4,$5,$6)',
      values: [obj.nconst,obj.primaryName,obj.birthYear,obj.deathYear, obj.primaryProfession,obj.knownForTitles],
    }
    client.query(query,function(err,res){
      if(err)
      {
          console.log(obj)
      }
      else
      {  
          console.log(obj);
      }
    });

}
module.exports = Query1;