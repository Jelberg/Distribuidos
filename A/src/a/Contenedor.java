/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import static java.lang.Boolean.FALSE;
import a.ManejoArchivos;
import a.ManejoArchivos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/*import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;*/

/**
 *
 * @author estefania
 */
public class Contenedor {
    ManejoArchivos inventario = new ManejoArchivos();
    private int contenido=0;
    private int total;
    private boolean contenedorlleno = Boolean.FALSE;
    private String dir ="src//a//Files//cantidadProductos.jason";
    
    /**
    *
    *      MONITORES
    */
    
    public synchronized int get(){
        while (contenedorlleno==FALSE){
            try{
                
            wait();
            
            }
            catch(InterruptedException e){
            }
        }
        contenedorlleno = Boolean.FALSE;
        notify();
        
        return contenido ;
    }
    
    public synchronized void put(int value) 
    {
        while (contenedorlleno) 
        {
            try 
            {
                // IMPLEMENTAR EL MANEJO DEL ARCHIVO 
                wait();
            } 
            catch (InterruptedException e) 
            {
                System.err.println("Contenedor: Error en put -> " + e.getMessage());
            }
        }
        
        contenedorlleno = Boolean.TRUE;
        notify();
    }
    
    
    //METODOS DE ESTADO DEL CONTENEDOR
    public int productosEntrantes(int productos_entrantes){
    contenido=contenido+productos_entrantes;
        return contenido;
    }
    
    public int productosSalientes(int productos_salientes){
    contenido=contenido-productos_salientes;
    return contenido;
    }
    
    public int estadoInventario(){
    String actual =	inventario.leeLinea(inventario.dirProductos, 1);
    return Integer.parseInt(actual);
    }
    
    public void prueba(){
    if (contenido==0){
    
    }
    }
    
    /**
     * Cantidad de productos disponibles en el contenedor 
     * @throws IOException 
     * @throws JSONException 
     * 
     */
    
    public void cantidadproductos() throws IOException  {
    	ManejoArchivos a = new ManejoArchivos();
    	String p = a.leeLinea( a.dirProductos,5);
    	System.out.println(p);
    };
    
}
