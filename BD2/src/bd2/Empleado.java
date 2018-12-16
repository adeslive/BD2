/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd2;

import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class Empleado extends Persona {
    private String direccion;
    private String sexo;
    private final List<String> dsanitarios;
    private final List<String> dacademicos;
    private final List<String> dlegales;
    private final List<String> dprofesionales;
    private final List<Persona> dfamiliares;
     
    public Empleado(){
        dsanitarios = new ArrayList<>();
        dacademicos = new ArrayList<>();
        dlegales = new ArrayList<>();
        dprofesionales = new ArrayList<>();
        dfamiliares = new ArrayList<>();
    }
    
    /*                      0           1            2            3            4 
        dsanitarios    estadosalud    alergias   operaciones
        dfamiliares    ------------------------------------------------------
        dacademicos      escuela     secundaria  universidad    maestria      doctorado
        dlegales        smilitar       años        ecivil
        dprofesional     titulo      puestos
   
    */

    public static List<Document> todosEmpleados(ConexionMongo mongo) {
        List<Document> resultado = new ArrayList<>();
        mongo.collection = mongo.database.getCollection("Empleado");
        mongo.collection.find().into(resultado);
        return resultado;
    }

    public static Document buscarEmpleadoNombre(ConexionMongo mongo, String Nombre) {
        mongo.collection = mongo.database.getCollection("Empleado");
        Document resultado = (Document) mongo.collection.find(Filters.or(eq("pnombre", Nombre),
                eq("snombre", Nombre))).first();
        return resultado;
    }
    
    public static Document buscarEmpleadoApellido(ConexionMongo mongo, String Apellido) {
        mongo.collection = mongo.database.getCollection("Empleado");
        Document resultado = (Document) mongo.collection.find(Filters.or(eq("papellido", Apellido), 
                eq("sapellido", Apellido))).first();
        return resultado;
    }

    //Busca un empleado dado el parametro y su valor
    public static Document buscarEmpleadoFiltro(ConexionMongo mongo, String Parametro, String Valor) {
        mongo.collection = mongo.database.getCollection("Empleado");
        Document resultado = (Document) mongo.collection.find(eq(Parametro, Valor)).first();
        return resultado;
    }
    
    public static Document empleadoAdoc(Empleado e){
        Document nuevoEmpleado = e.personaAdoc();
        List<Document> familiares = new ArrayList<>();

        nuevoEmpleado.put("dsanitarios", e.dsanitarios);
        nuevoEmpleado.put("dacademicos", e.dacademicos);
        nuevoEmpleado.put("dlegales", e.dlegales);
        nuevoEmpleado.put("dprofesionales", e.dprofesionales);
       
        e.dfamiliares.forEach((p) -> {
 
            try{
                if (p.relacion.equals("hermano") || p.relacion.equals("hermano")){
                }
                familiares.add(p.personaAdoc());
                
            } catch (NullPointerException ex){
                
            }
        });
        nuevoEmpleado.put("dfamiliares", familiares);
        return nuevoEmpleado;
    }
    
    public static void insertarEmpleado(ConexionMongo mongo, Empleado e){
        mongo.collection = mongo.database.getCollection("Empleado");
        mongo.collection.insertOne(empleadoAdoc(e));
    }
    
    public void agregarFamiliar(Persona familiar){
        if (this.dfamiliares.size()<3){
            dfamiliares.add(familiar);
        }
    }
    
    public void agregarSanitarios(int i, String sanitario){
        if (this.dsanitarios.size()<3){
            dsanitarios.add(i,sanitario);
        }
    }
    
    public void agregarAcademicos(int i, String acad){
        if (this.dacademicos.size()<5){
            dacademicos.add(i,acad);
        }
    }
    
    public void agregarLegales(int i, String legal){
        if (this.dlegales.size()<3){
            dlegales.add(i,legal);
        }
    }
    
    public void agregarProfesionales(int i, String profes){
        if (this.dsanitarios.size()<3){
            dsanitarios.add(i,profes);
        }
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
}
