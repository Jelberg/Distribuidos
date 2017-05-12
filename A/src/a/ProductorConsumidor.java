/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a;

import java.io.BufferedReader;
import a.ManejoArchivos;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author estefania
 */
public class ProductorConsumidor {
    private static Contenedor contenedor;
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
        int id_productor = 1;
        
        
        try {
        System.out.println("Iniciando Servidor...");
        socket = new DatagramSocket(6000);
        contenedor = new Contenedor();
        
        //contenedor.cantidadproductos();
   
        System.out.println("El estado actual del inventario es: "+contenedor.estadoInventario());
                    do { 
                    
                    
                    	// se pasa la instancia del contenedor y la id del productor
                    productor = new Thread(new Productor(contenedor, id_productor)); 
                    
                    
                    byte[] mensaje_bytes = new byte[1024];
                    String mensaje ="";
                    mensaje = new String(mensaje_bytes);
                    String mensajehilo ="";
                    
                    /**
                     * Preparacion para recibir el paquete 
                     */
                    
                    DatagramPacket paquete = new DatagramPacket(mensaje_bytes,1024);
                    DatagramPacket envpaquete = new DatagramPacket(mensaje_bytes,1024);
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    int puerto;
                    InetAddress address;
                    byte[] mensaje2_bytes = new byte[1024];


                 
                    socket.receive(paquete);
                  

                    idconsum=1+idconsum;
                    int bytesRec=paquete.getLength();
                  /*  
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
                    
                    */
                    
                     mensaje= new String(paquete.getData());
                    int long_men=mensaje.length();
                    int lon_ult_men=long_men-bytesRec;
                    
                    // Aqui imprimira la cantidad de productos que quiere el usuario 
                    System.out.println("PEDIDO DEL CLIENTE    : " + mensaje);
               
                     //Obtenemos IP Y PUERTO
                    puerto = paquete.getPort();
                    address = paquete.getAddress();
                    
                   
                       // Leer el .txt que tenga la cantidad de productos actuales 
                    ManejoArchivos cantProductos = new ManejoArchivos();
               
                    String cant = cantProductos.leeLinea(cantProductos.dirProductos, 1);
                
                   int cantidadInventario= Integer.parseInt(cant);
                   // Se le hace un tratamiento a mensaje ya que viene con un  caracter especial
                   int cantidadSolicitada= Integer.parseInt(mensaje.replaceAll("\uFEFF", "").trim());
                   
                   //Comprueba inventario para ver si se puede satisfacer 
                   if (cantidadInventario>=cantidadSolicitada){
                	   System.out.println(">>Solicitud procesada<<");
                	   // Disminuye el inventario
                	   int actualizaInventario = cantidadInventario - cantidadSolicitada;
                	   cantProductos.sobreEscribeUnValor( String.valueOf(actualizaInventario) , cantProductos.dirProductos);
                       System.out.println("Inventario disminuye de: "+cant +" a "+String.valueOf(actualizaInventario));
                       
                       //Se responde al usuario
                       mensajehilo="Resivo: "+mensaje.replaceAll("\uFEFF", "").trim()+" productos y el inventario de la tienda queda en "
                       +String.valueOf(contenedor.estadoInventario());
                       
                       mensaje2_bytes = mensajehilo.getBytes();
                       envpaquete = new DatagramPacket(mensaje2_bytes,mensajehilo.length(),address,puerto);
                       // realizamos el envio
                       socket.send(envpaquete);
                   
                   }
                   else{
                    productor.start();
                   }
                    //Obtenemos IP Y PUERTO
                   
                   
                 /*   for(int i = 0; i < idconsum; i++)
                            {
                            
                            consumidores.sleep(2500);
                            
                            consumidores = new Thread(new Consumidor (contenedor, idconsum));
                            
                            consumidores.start();
                            

                                        }
                   
                    
                     mensajehilo="El estado actual del inventario es: "+contenedor.estadoInventario();
                     mensaje2_bytes = mensajehilo.getBytes();
                     envpaquete = new DatagramPacket(mensaje2_bytes,mensajehilo.length(),address,puerto);
                     // realizamos el envio
                     socket.send(envpaquete);
                   */
     
          } 
           
           while (1>0);
             }
              catch (Exception e) {
             System.err.println(e.getMessage());
             System.exit(1);
             }
        
    }

           
}
