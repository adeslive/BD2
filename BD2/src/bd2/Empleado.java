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
    List<Persona> dfamiliares;
     
    public Empleado(){
        dfamiliares = new ArrayList<>();
    }

    public static List<Document> todosEmpleados(ConexionMongo mongo) {
        List<Document> resultado = new ArrayList<>();
        ConexionMongo cm = mongo;
        cm.collection = cm.database.getCollection("Empleado");
        cm.collection.find().into(resultado);
        return resultado;
    }

    public static Document buscarEmpleadoNombre(ConexionMongo mongo, String Nombre) {
        ConexionMongo cm = mongo;
        cm.collection = cm.database.getCollection("Empleado");
        Document resultado = (Document) cm.collection.find(Filters.or(eq("pnombre", Nombre),
                eq("snombre", Nombre))).first();
        return resultado;
    }
    
    public static Document buscarEmpleadoApellido(ConexionMongo mongo, String Apellido) {
        ConexionMongo cm = mongo;
        cm.collection = cm.database.getCollection("Empleado");
        Document resultado = (Document) cm.collection.find(Filters.or(eq("papellido", Apellido), 
                eq("sapellido", Apellido))).first();
        return resultado;
    }

    //Busca un empleado dado el parametro y su valor
    public static Document buscarEmpleadoFiltro(ConexionMongo mongo, String Parametro, String Valor) {
        ConexionMongo cm = mongo;
        cm.collection = cm.database.getCollection("Empleado");
        Document resultado = (Document) cm.collection.find(eq(Parametro, Valor)).first();
        return resultado;
    }
    
    public static void insertarEmpleado(ConexionMongo mongo, Empleado e){
        ConexionMongo cm = mongo;
        cm.collection = cm.database.getCollection("Empleado");
        Document nuevoEmpleado = e.personaAdoc();
        List<Document> familiares = new ArrayList<>();
          
        e.dfamiliares.forEach((p) -> {
            try{
                familiares.add(p.personaAdoc());
            } catch (NullPointerException ex){
                
            }
        });
        nuevoEmpleado.put("dfamiliares", familiares);
        cm.collection.insertOne(nuevoEmpleado);
    }
    
    public void agregarFamiliar(Persona familiar){
        if (this.dfamiliares.size()<3){
            dfamiliares.add(familiar);
        }
    }
}
