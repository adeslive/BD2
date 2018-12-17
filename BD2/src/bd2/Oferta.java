package bd2;

import com.google.gson.Gson;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class Oferta {
    private static final String COLECCION = "Oferta";
    private String idOferta;
    private String NombreOferta;
    private int cantidadVacantes;
    private String puesto;
    private String nivelEstudio;
    private int añosExperiencia;
    
    public static List<Document> todasOfertas(ConexionMongo mongo) {
        List<Document> resultado = new ArrayList<>();
        mongo.setCollection(mongo.getDatabase().getCollection(COLECCION));
        mongo.getCollection().find().into(resultado);
        return resultado;
    }

    //Busca un empleado dado el parametro y su valor
    public static Document buscarOfertaFiltro(ConexionMongo mongo, String Parametro, String Valor) {
        mongo.setCollection(mongo.getDatabase().getCollection(COLECCION));
        Document resultado = (Document) mongo.getCollection().find(eq(Parametro, Valor)).first();
        return resultado;
    }
    
    public static Document ofertaAdoc(Oferta of){
        Document nuevaOferta = new Document();
            
        nuevaOferta.put("idOferta", of.idOferta);
        nuevaOferta.put("nombreOferta", of.NombreOferta);
        nuevaOferta.put("cantidadVacantes", of.cantidadVacantes);
        nuevaOferta.put("puesto", of.puesto);
        nuevaOferta.put("nivelEstudio", of.nivelEstudio);
        nuevaOferta.put("añosExperiencia", of.añosExperiencia);
             
        return nuevaOferta;
    }
    
    public static Oferta docAoferta(Document of){
        Oferta temp = new Gson().fromJson(of.toJson(), Oferta.class);
        return temp;
    }
    
    public static void insertarOferta(ConexionMongo mongo, Oferta of){
        mongo.setCollection(mongo.getDatabase().getCollection(COLECCION));
        mongo.getCollection().insertOne(ofertaAdoc(of));
    }    
}
