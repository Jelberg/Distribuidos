/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.Date;

/**
 *
 * @author elberg
 */
public class Colores {
    private String color;
    private String fecha;
    private String accion;

    public Colores() {
    }

    public Colores(String color, String fecha) {
        this.color = color;
        this.fecha = fecha;
    }
    
    

    public Colores(String color, String fecha, String accion) {
        this.color = color;
        this.fecha = fecha;
        this.accion = accion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
}
