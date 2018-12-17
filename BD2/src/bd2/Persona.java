package bd2;

import com.google.gson.Gson;
import org.bson.Document;


public class Persona {
    
    protected String pnombre;
    protected String snombre;
    protected String papellido;
    protected String sapellido;
    protected String relacion;

    public Persona agregarPersona(String pnombre, String snombre, 
            String papellido, String sapellido, String relacion ) {
        this.setPnombre(pnombre);
        this.setSnombre(snombre);
        this.setPapellido(papellido);
        this.setSapellido(sapellido);
        this.setRelacion(relacion);
        return this;
    }

    public static Document personaAdoc(Persona p){
        Document temp = new Document();
            
        temp.put("pnombre", p.getPnombre());
        temp.put("snombre", p.getSnombre());
        temp.put("papellido", p.getPapellido());
        temp.put("sapellido", p.getSapellido());
        temp.put("relacion", p.getRelacion());

        return temp;
    }
    
    public static Persona docApersona(Document p){
        Persona temp = new Gson().fromJson(p.toJson(), Persona.class);
        return temp;
    }
      
    public String getPnombre() {
        return pnombre;
    }
    
    public void setPnombre(String pnombre) {
        this.pnombre = pnombre;
    }

    public String getSnombre() {
        return snombre;
    }

    public void setSnombre(String snombre) {
        this.snombre = snombre;
    }

    public String getPapellido() {
        return papellido;
    }

    public void setPapellido(String papellido) {
        this.papellido = papellido;
    }

    public String getSapellido() {
        return sapellido;
    }

    public void setSapellido(String sapellido) {
        this.sapellido = sapellido;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }
}
