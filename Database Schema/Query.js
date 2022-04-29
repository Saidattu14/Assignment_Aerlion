const Query = (client,obj) => {
      const query = {
        name :'insert-user',
        text: 'INSERT INTO movies(tconst,titleType,endYear,primaryTitle,originalTitle,genres,runtimeMinutes,startYear,isAdult) VALUES($1, $2,$3,$4,$5,$6,$7,$8,$9)',
        values: [obj.tconst,obj.titleType,obj.endYear,obj.primaryTitle, obj.originalTitle,obj.genres,obj.runtimeMinutes,obj.startYear,obj.isAdult],
      }
      client.query(query,function(err,res){
        if(err)
        {
            console.log(err)
        }
        else
        {  
            console.log(res);
        }});

}

module.exports = Query;