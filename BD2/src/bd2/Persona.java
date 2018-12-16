/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd2;

import org.bson.Document;

/**
 *
 * @author adesl
 */
public class Persona {

    protected String pnombre;
    protected String snombre;
    protected String papellido;
    protected String sapellido;
    protected int edad;
    protected String sexo;
    protected String estadoCivil;
    protected String identificacion;
    protected String direccion;

    public Persona agregarPersona(String pnombre, String snombre, String papellido, String sapellido,
            int edad, String sexo, String estadoCivil, String identificacion,
            String direccion) {
        this.setPnombre(pnombre);
        this.setSnombre(snombre);
        this.setPapellido(papellido);
        this.setSapellido(papellido);
        this.setEdad(edad);
        this.setSexo(sexo);
        this.setEstadoCivil(estadoCivil);
        this.setIdentificacion(identificacion);
        this.setDireccion(direccion);
        return this;
    }

    public Document personaAdoc(){
        Document temp = new Document();
            
        temp.put("pnombre", this.pnombre);
        temp.put("snombre", this.snombre);
        temp.put("papellido", this.papellido);
        temp.put("sapellido", this.sapellido);
        temp.put("edad", this.edad);
        temp.put("sexo", this.sexo);
        temp.put("estadoCivil", this.estadoCivil);
        temp.put("identificacion", this.identificacion);
        temp.put("direccion", this.direccion);

        return temp;
    }
    
    protected String getPnombre() {
        return pnombre;
    }

    protected void setPnombre(String pnombre) {
        this.pnombre = pnombre;
    }

    protected String getSnombre() {
        return snombre;
    }

    protected void setSnombre(String snombre) {
        this.snombre = snombre;
    }

    protected String getPapellido() {
        return papellido;
    }

    protected void setPapellido(String papellido) {
        this.papellido = papellido;
    }

    protected String getSapellido() {
        return sapellido;
    }

    protected void setSapellido(String sapellido) {
        this.sapellido = sapellido;
    }

    protected int getEdad() {
        return edad;
    }

    protected void setEdad(int edad) {
        this.edad = edad;
    }

    protected String getSexo() {
        return sexo;
    }

    protected void setSexo(String sexo) {
        this.sexo = sexo;
    }

    protected String getEstadoCivil() {
        return estadoCivil;
    }

    protected void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    protected String getIdentificacion() {
        return identificacion;
    }

    protected void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    protected String getDireccion() {
        return direccion;
    }

    protected void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
