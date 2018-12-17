package bd2;

import com.google.gson.Gson;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;

/**
 *
 * @author Victor
 */
public class Empresa {
    private static final String COLECCION = "Empresa";
    private String nombre;
    private String CIF;
    private Empleado director;
    private final Map<String, String> direccion;
    private final Map<String, String> categorias;
    private final Map<String, String> oferta; 
    
    public Empresa(){
        oferta = new HashMap<>();
        direccion = new HashMap<>();
        categorias = new HashMap<>();
    }
    /*                  0          1           3           4           5
        direccion     Pais   Departamento   Municipio    Colonia     Bloque
    
    */
    public static List<Document> todasEmpresas(ConexionMongo mongo) {
        List<Document> resultado = new ArrayList<>();
        mongo.setCollection(mongo.getDatabase().getCollection(COLECCION));
        mongo.getCollection().find().into(resultado);
        return resultado;
    }

    //Busca un empleado dado el parametro y su valor
    public static Document buscarEmpresaFiltro(ConexionMongo mongo, String Parametro, String Valor) {
        mongo.setCollection(mongo.getDatabase().getCollection(COLECCION));
        Document resultado = (Document) mongo.getCollection().find(eq(Parametro, Valor)).first();
        return resultado;
    }
    
    public static Document empresaAdoc(Empresa e){
        Document nuevaEmpresa = new Document();
            
        nuevaEmpresa.put("nombre", e.nombre);
        nuevaEmpresa.put("CIF", e.CIF);
        nuevaEmpresa.put("director", Empleado.empleadoAdoc(e.director));
        nuevaEmpresa.put("direccion", e.getDireccion());
        nuevaEmpresa.put("categorias", e.getCategorias());
        
        return nuevaEmpresa;
    }
    
    public static Empresa docAempresa(Document d){
        Empresa temp = new Gson().fromJson(d.toJson(), Empresa.class);
        return temp;
    }    
    
    public static void insertarEmpresa(ConexionMongo mongo, Empresa e){
        mongo.setCollection(mongo.getDatabase().getCollection(COLECCION));
        mongo.getCollection().insertOne(empresaAdoc(e));
    }
    
    public void agregarOferta(String nombreParam, String valor){
        getOferta().put(nombreParam, valor);
    }
    
    public void eliminiarOferta(String nombreParam, String valor){
        getOferta().remove(nombreParam);
    }

    public Map<String, String> getDireccion() {
        return direccion;
    }

    public Map<String, String> getCategorias() {
        return categorias;
    }

    public Map<String, String> getOferta() {
        return oferta;
    }
}
