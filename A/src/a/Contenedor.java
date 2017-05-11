/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import static java.lang.Boolean.FALSE;

/**
 *
 * @author estefania
 */
public class Contenedor {
    
    private int contenido=0;
    private int total;
    private boolean contenedorlleno = Boolean.FALSE;
    
    //Monitores
    
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
    return contenido;
    }
    
    public void prueba(){
    if (contenido==0){
    
    }
    }
    
}
