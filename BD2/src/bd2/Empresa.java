package bd2;

import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Victor
 */
public class Empresa {
    private String nombre;
    private String CIF;
    private Empleado director;
    private String direccion;
    private List<String> categorias; 
    
    public static List<Document> todasEmpresas(ConexionMongo mongo) {
        List<Document> resultado = new ArrayList<>();
        mongo.collection = mongo.database.getCollection("Empresa");
        mongo.collection.find().into(resultado);
        return resultado;
    }

    //Busca un empleado dado el parametro y su valor
    public static Document buscarEmpresaFiltro(ConexionMongo mongo, String Parametro, String Valor) {
        mongo.collection = mongo.database.getCollection("Empresa");
        Document resultado = (Document) mongo.collection.find(eq(Parametro, Valor)).first();
        return resultado;
    }
    
    public static Document empresaAdoc(Empresa e){
        Document nuevaEmpresa = new Document();
        Document temp = new Document();
            
        temp.put("nombre", e.nombre);
        temp.put("CIF", e.CIF);
        temp.put("director", Empleado.empleadoAdoc(e.director));
        temp.put("direccion", e.direccion);
        temp.put("categorias", e.categorias);
             
        return nuevaEmpresa;
    }
    
    public static void insertarEmpresa(ConexionMongo mongo, Empresa e){
        mongo.collection = mongo.database.getCollection("Empleado");
        mongo.collection.insertOne(empresaAdoc(e));
    }
}
