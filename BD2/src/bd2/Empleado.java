/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd2;

import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;

public class Empleado extends Persona {
    
    List<Persona> dfamiliares;
    
    
    public Empleado(){
        dfamiliares = Arrays.asList(new Persona[3]);
    }

    public static List<Document> todosEmpleados() {
        List<Document> resultado = new ArrayList<>();
        ConexionMongo cm;

        cm = new ConexionMongo();
        cm.collection = cm.database.getCollection("Empleado");
        cm.collection.find().into(resultado);
        return resultado;
    }

    public static Document buscarEmpleadoNombre(String Nombre) {
        ConexionMongo cm = new ConexionMongo();
        cm.collection = cm.database.getCollection("Empleado");
        Document resultado = (Document) cm.collection.find(Filters.or(eq("pnombre", Nombre),
                eq("snombre", Nombre))).first();
        return resultado;
    }
    
    public static Document buscarEmpleadoApellido(String Apellido) {
        ConexionMongo cm = new ConexionMongo();
        cm.collection = cm.database.getCollection("Empleado");
        Document resultado = (Document) cm.collection.find(Filters.or(eq("papellido", Apellido), 
                eq("sapellido", Apellido))).first();
        return resultado;
    }

    //Busca un empleado dado el parametro y su valor
    public static Document buscarEmpleadoFiltro(String Parametro, String Valor) {
        ConexionMongo cm = new ConexionMongo();
        cm.collection = cm.database.getCollection("Empleado");
        Document resultado = (Document) cm.collection.find(eq(Parametro, Valor)).first();
        return resultado;
    }
    
    public void agreagarFamiliar(Persona familiar){
        if (this.dfamiliares.size()<3){
            dfamiliares.add(familiar);
        }
    }
    
}
