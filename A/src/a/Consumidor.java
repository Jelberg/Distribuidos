package a;

import static java.lang.Thread.sleep;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author estefania
 */
public class Consumidor implements Runnable
{
    private final Contenedor contenedor;
    private final int idconsumidor;
    

    /**
     * Constructor de la clase
     * @param contenedor común a los consumidores y el productor
     * @param idconsumidor Identificador del consumidor
     */
    public Consumidor(Contenedor contenedor, int idconsumidor) 
    {
        this.contenedor = contenedor;
        this.idconsumidor = idconsumidor;

    }

    @Override
    /**
     * Implementación de la hebra
     */
    
    public void run() 
    {
        while(Boolean.TRUE)
        {   
            System.out.println("El estado actual del inventario es: "+contenedor.estadoInventario());
            System.out.println("Ingrese cantidad de productos a consumir");
            
            String entradaTeclado;
            Scanner entradaEscaner = new Scanner (System.in);
            entradaTeclado = entradaEscaner.nextLine (); 
            int salida=Integer.valueOf(entradaTeclado);
            //int salida=contenedor.get();
            
            
            int actualizar=contenedor.productosSalientes(salida);
            
            if (contenedor.estadoInventario()==0){
                contenedor.get();
                
            }
        
        }
        
    }
}
