package bd2;

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

    public Document personaAdoc(){
        Document temp = new Document();
            
        temp.put("pnombre", this.getPnombre());
        temp.put("snombre", this.getSnombre());
        temp.put("papellido", this.getPapellido());
        temp.put("sapellido", this.getSapellido());
        temp.put("relacion", this.getRelacion());

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
