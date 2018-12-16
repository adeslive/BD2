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
    private static final String CONEXION = "mongodb://localhost:27017";
    private static final String DATABASE = "BD2";
    private MongoClient mongoClient;
    public MongoDatabase database;
    public MongoCollection collection;
    
    public ConexionMongo(){
        try{
            mongoClient = new MongoClient(new MongoClientURI(CONEXION));
            database = mongoClient.getDatabase(DATABASE);
        }catch (Exception e){
        
        }
    }
    
    public void ejecutarConsulta(final Void t){
    }
    
    public MongoClient getClient(){
        return mongoClient; 
    }
}
