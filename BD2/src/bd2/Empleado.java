/*
e * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd2;

import com.google.gson.Gson;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.Document;

public class Empleado extends Persona {

    private static final String COLECCION = "Empleado";
    private String direccion;
    private String sexo;
    private String identificacion;
    private final Map<String, String> dsanitarios;
    private final Map<String, String> dacademicos;
    private final Map<String, String> dlegales;
    private final Map<String, String> dprofesionales;
    private final Map<String, Document>  dfamiliares;
    private final Map<String, String>  solicitudes;  // <Id solicitud, estado>
     
    public Empleado(){
        dsanitarios = new HashMap<>();
        dacademicos = new HashMap<>();
        dlegales = new HashMap<>();
        dprofesionales = new HashMap<>();
        dfamiliares = new HashMap<>();
        solicitudes = new HashMap<>();
    }

    public static List<Document> todosEmpleados(ConexionMongo mongo) {
        List<Document> resultado = new ArrayList<>();
        mongo.setCollection(mongo.getDatabase().getCollection(COLECCION));
        mongo.getCollection().find().into(resultado);
        return resultado;
    }

    public static Document buscarEmpleadoNombre(ConexionMongo mongo, String Nombre) {
        mongo.setCollection(mongo.getDatabase().getCollection(COLECCION));
        Document resultado = (Document) mongo.getCollection().find(Filters.or(eq("pnombre", Nombre),
                eq("snombre", Nombre))).first();
        return resultado;
    }
    
    public static Document buscarEmpleadoApellido(ConexionMongo mongo, String Apellido) {
        mongo.setCollection(mongo.getDatabase().getCollection(COLECCION));
        Document resultado = (Document) mongo.getCollection().find(Filters.or(eq("papellido", Apellido), 
                eq("sapellido", Apellido))).first();
        return resultado;
    }

    //Busca un empleado dado el parametro y su valor
    public static Document buscarEmpleadoFiltro(ConexionMongo mongo, String Parametro, String Valor) {
        mongo.setCollection(mongo.getDatabase().getCollection(COLECCION));
        Document resultado = (Document) mongo.getCollection().find(eq(Parametro, Valor)).first();
        return resultado;
    }
    
    public static Document empleadoAdoc(Empleado e){
        Document nuevoEmpleado = Persona.personaAdoc(e);
        nuevoEmpleado.put("identificacion", e.getIdentificacion());
        nuevoEmpleado.put("dsanitarios", e.getDsanitarios());
        nuevoEmpleado.put("dacademicos", e.getDacademicos());
        nuevoEmpleado.put("dlegales", e.getDlegales());
        nuevoEmpleado.put("dprofesionales", e.getDprofesionales());
        nuevoEmpleado.put("dfamiliares", e.getDfamiliares());
        return nuevoEmpleado;
    }
   
    public static Empleado docAempleado(Document d){
        Empleado temp = new Gson().fromJson(d.toJson(), Empleado.class);
        return temp;
    }
    
    public static void insertarEmpleado(ConexionMongo mongo, Empleado e){
        mongo.setCollection(mongo.getDatabase().getCollection(COLECCION));
        mongo.getCollection().insertOne(empleadoAdoc(e));
    }
    
    public static void eliminarEmpleado(ConexionMongo mongo, Empleado e){
        mongo.setCollection(mongo.getDatabase().getCollection(COLECCION));
        mongo.getCollection().deleteOne(eq("identificacion", e.getIdentificacion()));
    }
    
    public static void eliminarTodosEmpleados(ConexionMongo mongo, Empleado e){
        mongo.setCollection(mongo.getDatabase().getCollection(COLECCION));
        mongo.getCollection().deleteMany(new Document());
    }
    
    public void agregarFamiliar(Persona familiar){
        if (this.getDfamiliares().size()<3){
            getDfamiliares().put(familiar.getRelacion(), Persona.personaAdoc(familiar));
        }
    }
    
    public void agregarSanitarios(String nombreParam , String valor){
        if (this.getDsanitarios().size()<3){
            getDsanitarios().put(nombreParam, valor);
        }
    }
    
    public void agregarAcademicos(String nombreParam , String valor){
        if (this.getDacademicos().size()<3){
            getDacademicos().put(nombreParam, valor);
        }
    }
    
    public void agregarLegales(String nombreParam , String valor){
        if (this.getDlegales().size()<3){
            getDlegales().put(nombreParam, valor);
        }
    }
    
    public void agregarProfesionales(String nombreParam , String valor){
        if (this.getDprofesionales().size()<3){
            getDprofesionales().put(nombreParam, valor);
        }
    }
    
    public void agregarSolicitud(String idOferta , String estado){
        getDprofesionales().put(idOferta, estado);
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Map<String, String> getDsanitarios() {
        return dsanitarios;
    }

    public Map<String, String> getDacademicos() {
        return dacademicos;
    }

    public Map<String, String> getDlegales() {
        return dlegales;
    }

    public Map<String, String> getDprofesionales() {
        return dprofesionales;
    }

    public Map<String, Document> getDfamiliares() {
        return dfamiliares;
    }

    public Map<String, String> getSolicitudes() {
        return solicitudes;
    }
    
}
