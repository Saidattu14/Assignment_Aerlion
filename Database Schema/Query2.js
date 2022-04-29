
const Query2 = (client,obj) => {
    const query = {
      name :'insert-user2',
      text: 'INSERT INTO actor_appearances(tconst,ordering,nconst,category,job,characters) VALUES($1, $2,$3,$4,$5,$6)',
      values: [obj.tconst,obj.ordering,obj.nconst,obj.category, obj.job,obj.characters],
    }
    client.query(query,function(err,res){
      if(err)
      {
          console.log(err)
      }
      else
      {  
        console.log(obj)
      }
    });

}
module.exports = Query2;