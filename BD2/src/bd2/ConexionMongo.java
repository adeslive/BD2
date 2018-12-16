package bd2;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author adesl
 */
public class ConexionMongo {
    private MongoClient mongoClient;
    public MongoDatabase database;
    public MongoCollection collection, persona;
    
    public ConexionMongo(){
        try{
            mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
            database = mongoClient.getDatabase("BD2");
            persona = database.getCollection("Persona");
        }catch (Exception e){
        
        }
    }
    
    public MongoClient getClient(){
        return mongoClient; 
    }
}
