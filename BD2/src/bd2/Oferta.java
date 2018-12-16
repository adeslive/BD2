package bd2;

import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class Oferta {
    private static final String COLECCION = "Empleado";
    private String idOferta;
    private String NombreOferta;
    private int cantidadVacantes;
    private String puesto;
    private String nivelEstudio;
    private int añosExperiencia;
    
    public static List<Document> todasEmpresas(ConexionMongo mongo) {
        List<Document> resultado = new ArrayList<>();
        mongo.collection = mongo.database.getCollection(COLECCION);
        mongo.collection.find().into(resultado);
        return resultado;
    }

    //Busca un empleado dado el parametro y su valor
    public static Document buscarEmpresaFiltro(ConexionMongo mongo, String Parametro, String Valor) {
        mongo.collection = mongo.database.getCollection(COLECCION);
        Document resultado = (Document) mongo.collection.find(eq(Parametro, Valor)).first();
        return resultado;
    }
    
    public static Document ofertaAdoc(Oferta of){
        Document nuevaEmpresa = new Document();
        Document temp = new Document();
            
        temp.put("idOferta", of.idOferta);
        temp.put("CIF", of.NombreOferta);
        temp.put("director", of.cantidadVacantes);
        temp.put("direccion", of.puesto);
        temp.put("categorias", of.nivelEstudio);
        temp.put("categorias", of.añosExperiencia);
             
        return nuevaEmpresa;
    }
    
    public static void insertarOferta(ConexionMongo mongo, Oferta of){
        mongo.collection = mongo.database.getCollection(COLECCION);
        mongo.collection.insertOne(ofertaAdoc(of));
    }    
}
