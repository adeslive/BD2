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
    private boolean estado;
    private String NombreOferta;
    private int cantidadVacantes;
    private final Map<String, String> puesto;
    private final Map<String, String> rsanitarios;
    private final Map<String, String> racademicos;
    private final Map<String, String> rlegales;
    private final Map<String, String> rprofesionales;
    
    public Oferta(String NombreOferta, int cantidadVacantes, String nombrePuesto,
            String salario, String Area ){
        puesto = new HashMap<>();
        rprofesionales = new HashMap<>();
        rsanitarios = new HashMap<>();
        racademicos = new HashMap<>();
        rlegales = new HashMap<>();
        
        this.NombreOferta = NombreOferta;
        this.cantidadVacantes = cantidadVacantes;
        this.definirOferta(nombrePuesto, salario, Area);
    }
    
    public static Document ofertaAdoc(Oferta of){
        Document nuevaOferta = new Document();
            
        nuevaOferta.put("estado", of.isEstado());
        nuevaOferta.put("nombreOferta", of.getNombreOferta());
        nuevaOferta.put("cantidadVacantes", of.getCantidadVacantes());
        nuevaOferta.put("puesto", of.getPuesto());
        nuevaOferta.put("rprofesionales", of.getRprofesionales());
        nuevaOferta.put("rsanitarios", of.getRsanitarios());
        nuevaOferta.put("racademicos", of.getRacademicos());
        nuevaOferta.put("rlegales", of.getRlegales());
        
        return nuevaOferta;
    }
    
    public static Oferta docAoferta(Document of){
        Oferta temp = new Gson().fromJson(of.toJson(), Oferta.class);
        return temp;
    }
    
    public void definirOferta(String nombre, String salario, String area){
        getPuesto().put("nombre", nombre );
        getPuesto().put("salario", salario );
        getPuesto().put("area", area);
    }
    
    public void agregarRsanitarios(String nombreParam , String valor){
        if (this.getRprofesionales().size()<3){
            getRprofesionales().put(nombreParam, valor);
        }
    }
    
    public void agregarRacademicos(String nombreParam , String valor){
        if (this.getRsanitarios().size()<3){
            getRsanitarios().put(nombreParam, valor);
        }
    }
    
    public void agregarRlegales(String nombreParam , String valor){
        if (this.getRprofesionales().size()<3){
            getRprofesionales().put(nombreParam, valor);
        }
    }
    
    public void agregarRprofesionales(String nombreParam , String valor){
        if (this.getRprofesionales().size()<3){
            getRprofesionales().put(nombreParam, valor);
        }
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNombreOferta() {
        return NombreOferta;
    }

    public void setNombreOferta(String NombreOferta) {
        this.NombreOferta = NombreOferta;
    }

    public int getCantidadVacantes() {
        return cantidadVacantes;
    }

    public void setCantidadVacantes(int cantidadVacantes) {
        this.cantidadVacantes = cantidadVacantes;
    }

    public Map<String, String> getPuesto() {
        return puesto;
    }

    public Map<String, String> getRsanitarios() {
        return rsanitarios;
    }

    public Map<String, String> getRacademicos() {
        return racademicos;
    }

    public Map<String, String> getRlegales() {
        return rlegales;
    }

    public Map<String, String> getRprofesionales() {
        return rprofesionales;
    }
}
