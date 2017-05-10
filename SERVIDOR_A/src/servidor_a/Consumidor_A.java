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

    public class Consumidor_A implements Runnable
{
    private final Contenedor_A contenedor;
    private final int idconsumidor;
    private String mensaje;

  

    /**
     * Constructor de la clase
     * @param contenedor Contenedor común a los consumidores y el productor
     * @param idconsumidor Identificador del consumidor
     */
    public Consumidor_A(Contenedor_A contenedor, int idconsumidor) 
    {
        this.contenedor = contenedor;
        this.idconsumidor = idconsumidor;
        this.mensaje="";
        
    }

    @Override
    /**
     * Implementación de la hebra
     */
    public void run() 
    {
        while(Boolean.TRUE)
        {
            System.out.println("El consumidor " + idconsumidor + " consume: " + contenedor.get());
            mensaje="El consumidor " + idconsumidor + " consume: " + contenedor.get();
        }
    }

//Metodos para obtener y enviar mensajes del run
  public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    }