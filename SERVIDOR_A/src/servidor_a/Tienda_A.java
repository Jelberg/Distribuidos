/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor_a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


/**
 *
 * @author estefania
 */
public class Tienda_A {
    private static Contenedor_A contenedor;
    private static Thread productor;
    private static Thread  consumidores;
    private static final int CANTIDADCONSUMIDORES = 5;
    private static int idconsum=0;
    private static int total=0;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        DatagramSocket socket;
        boolean fin = false;

        
        try {
            System.out.println("Iniciando Servidor...");
            
            System.err.println("El contenedor tiene : "+total +" productos");
           //Creamos el socket

            socket = new DatagramSocket(6000);
            do { 
            //Iniciando y Determinando estado del Contenedor
            contenedor = new Contenedor_A();
            


            //Creando hilo de ejecucion del productor
            productor = new Thread(new Productor_A (contenedor, 1));
          
  
           
            //Iniciamos el bucle
           


            
            byte[] mensaje_bytes = new byte[1024];
            String mensaje ="";
            mensaje = new String(mensaje_bytes);
            String mensajehilo ="";

            DatagramPacket paquete = new DatagramPacket(mensaje_bytes,1024);
            DatagramPacket envpaquete = new DatagramPacket(mensaje_bytes,1024);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

           int puerto;
            InetAddress address;
            byte[] mensaje2_bytes = new byte[1024];

          
            // Recibimos el paquete
            socket.receive(paquete);
            int bytesRec=paquete.getLength();

           //DATOS
            
           
            idconsum=1+idconsum;
            System.out.println();
            System.out.println("Total de consumidores: "+idconsum);
            System.out.println();
            System.out.println("Id del comsumidor: " + idconsum);
            System.out.println("Número de Bytes recibidos: " + bytesRec);
            //System.out.println("Contenido del Paquete    : " + paquete.getData().toString());
            System.out.println("Puerto origen del mensaje: " + paquete.getPort());
            System.out.println("IP de origen             : " + paquete.getAddress().getHostAddress());
            System.out.println("Puerto destino del mensaje:" + socket.getLocalPort());
            System.out.println();
            System.out.println("Contenido del Paquete    : " + mensaje);


            // Lo formateamos
            productor.start();
            mensaje= new String(paquete.getData());
            int long_men=mensaje.length();
            int lon_ult_men=long_men-bytesRec;
             // Lo mostramos por pantalla
             //Obtenemos IP Y PUERTO
             puerto = paquete.getPort();
             address = paquete.getAddress();
             
             for(int i = 0; i < idconsum; i++)
                    {
                        consumidores = new Thread(new Consumidor_A (contenedor, idconsum));
                        consumidores.start();

                    }
            
            
                    

             //formateamos el mensaje de salida
             mensaje2_bytes = mensajehilo.getBytes();

            //Preparamos el paquete que queremos enviar
             envpaquete = new DatagramPacket(mensaje2_bytes,mensajehilo.length(),address,puerto);

            // realizamos el envio
             socket.send(envpaquete);

            } 
           
           while (1>0);
             }
              catch (Exception e) {
             System.err.println(e.getMessage());
             System.exit(1);
             }

                }
    
}
