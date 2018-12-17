package bd2;

import com.google.gson.Gson;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;

public class Oferta {
    private static final String COLECCION = "Oferta";
    private String idOferta;
    private String NombreOferta;
    private int cantidadVacantes;
    private final Map<String, String> puesto;
    private final Map<String, String> rsanitarios;
    private final Map<String, String> racademicos;
    private final Map<String, String> rlegales;
    private final Map<String, String> rprofesionales;
    
    public Oferta(){
        puesto = new HashMap<>();
        rprofesionales = new HashMap<>();
        rsanitarios = new HashMap<>();
        racademicos = new HashMap<>();
        rlegales = new HashMap<>();
    }
    
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
        nuevaOferta.put("rprofesionales", of.rprofesionales);
        nuevaOferta.put("rsanitarios", of.rsanitarios);
        nuevaOferta.put("racademicos", of.racademicos);
        nuevaOferta.put("rlegales", of.rlegales);
        
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
    
    public void definirOferta(String nombre, String salario, String area){
        puesto.put("nombre", nombre );
        puesto.put("salario", salario );
        puesto.put("area", area);
    }
    
    public void agregarRsanitarios(String nombreParam , String valor){
        if (this.rprofesionales.size()<3){
            rprofesionales.put(nombreParam, valor);
        }
    }
    
    public void agregarRacademicos(String nombreParam , String valor){
        if (this.rsanitarios.size()<3){
            rsanitarios.put(nombreParam, valor);
        }
    }
    
    public void agregarRlegales(String nombreParam , String valor){
        if (this.rprofesionales.size()<3){
            rprofesionales.put(nombreParam, valor);
        }
    }
    
    public void agregarRprofesionales(String nombreParam , String valor){
        if (this.rprofesionales.size()<3){
            rprofesionales.put(nombreParam, valor);
        }
    }
}
