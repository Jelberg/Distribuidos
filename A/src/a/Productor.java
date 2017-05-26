/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import java.util.Random;

/**
 *
 * @author estefania
 */

    public class Productor implements Runnable
{
    private final int cantidad;
    private final Contenedor contenedor;
    private final int idproductor;
    private final int TIEMPOESPERA = 1500;

    /**
     * Constructor de la clase
     * @param contenedor Contenedor común a los consumidores y el productor
     * @param idproductor Identificador del productor
     */
    public Productor(Contenedor contenedor, int idproductor) 
    {
        this.contenedor = contenedor;
        this.idproductor = idproductor;
        cantidad =5;
    }

    @Override
    /**
     * Implementación de la hebra
     */
    public void run() 
    {
        while(Boolean.TRUE)
        {
ManejoArchivos man = new ManejoArchivos();
            int poner =  cantidad*Integer.parseInt(man.leeLinea(man.dirMultiplo,1));
            contenedor.put(poner);
            System.out.println("El productor " + idproductor + " pone: " + poner);
            contenedor.productosEntrantes(poner);
           
             
            try 
            {
                Thread.sleep(TIEMPOESPERA);
            } 
            catch (InterruptedException e) 
            {
                System.err.println("Productor " + idproductor + ": Error en run -> " + e.getMessage());
            }
        }
    }
}

