/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_a;

import java.util.Random;

/**
 *
 * @author estefania
 */
public class Productor_A implements Runnable {
    private  int productos=5;
    private  Contenedor_A contenedor;
    private  int idproductor;
    private  int TIEMPOESPERA = 1500;

    /**
     * Constructor de la clase
     * @param contenedor Contenedor común a los consumidores y el productor
     * @param idproductor Identificador del productor
     */
    public Productor_A(Contenedor_A contenedor, int idproductor) 
    {
        this.contenedor = contenedor;
        this.idproductor = idproductor;
        this.productos =5;
    }

    @Override
    /**
     * Implementación del hilo de ejecucion
     */
    public void run() 
    {
        while(Boolean.TRUE)
        {
            int poner = 5;
            contenedor.put(poner);
            System.out.println("El productor A  pone: " + poner);
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
