/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_a;

/**
 *
 * @author estefania
 */
public class Contenedor_A {
    private int contenido;
    private int total=0;
    private boolean contenedorlleno = Boolean.FALSE;

    /**
     * Obtiene de forma concurrente o síncrona el elemento que hay en el contenedor
     * @return Contenido el contenedor
     */
    public synchronized int get()
    {
        while (!contenedorlleno)
        {
            try 
            {
                
                wait();
            } 
            catch (InterruptedException e) 
                
            {
                System.err.println("Contenedor: Error en get -> " + e.getMessage());
            }
        }
        contenedorlleno = Boolean.FALSE;
        notify();
        return contenido;
    }

    /**
     * Introduce de forma concurrente o síncrona un elemento en el contenedor
     * @param value Elemento a introducir en el contenedor
     */
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
        contenido = value;
        contenedorlleno = Boolean.TRUE;
        notify();
    }
}
