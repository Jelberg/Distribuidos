/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMi;

/**
 *
 * @author estefania
 */
public class Color {
    private String nombre;
    private String atributo;

    public Color(String nombre, String atributo) {
        this.nombre = nombre;
        this.atributo = atributo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }
    
    
    
}
