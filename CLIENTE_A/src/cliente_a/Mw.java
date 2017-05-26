package cliente_a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author estefania
 */
public class Mw {
    /**
     * Metodo que verifica y realiza la traduccion de los mensajes para que sean compatibles en las 
     * diferentes arquitecturas
     * @param letra 
     */
    public void  ActivandoMw(String letra){
        
    if ("B".equals(letra)){
                
                 System.out.println("Ingrese cantidad de productos deseados: ");
                 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                //Definimos el sockets, número de bytes del buffer, y mensaje.
                 DatagramSocket socket;
                 InetAddress address;
                 byte[] mensaje_bytes = new byte[256];
                 String mensaje="";
                 mensaje_bytes=mensaje.getBytes();

                //Paquete
                 DatagramPacket paquete;

                 String cadenaMensaje="";

                DatagramPacket servPaquete;

                byte[] RecogerServidor_bytes = new byte[256];

                 try {
                 socket = new DatagramSocket();

                address=InetAddress.getByName("localhost");

                do {
                  if ("fin".equals(mensaje)){
                        break;
                  }
                 mensaje = in.readLine();
                 mensaje_bytes = mensaje.getBytes();
                 paquete = new DatagramPacket(mensaje_bytes,mensaje.length(),address,6000);
                 socket.send(paquete);  // Manda el paquete 

                RecogerServidor_bytes = new byte[256];

                //Esperamos a recibir un paquete
                 servPaquete = new DatagramPacket(RecogerServidor_bytes,256);
                 socket.receive(servPaquete);

                //Convertimos el mensaje recibido en un string
                 cadenaMensaje = new String(RecogerServidor_bytes).trim();

                //Imprimimos el paquete recibido
                 System.out.println(cadenaMensaje);
                 } while (!mensaje.startsWith("fin"));
                 }
                 catch (Exception e) {
                 System.err.println(e.getMessage());
                 System.exit(1);
                 }
        
    }
    
    if("C".equals(letra)){
                 System.out.println("Ingrese cantidad de productos deseados: ");
                 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                //Definimos el sockets, número de bytes del buffer, y mensaje.
                 DatagramSocket socket;
                 InetAddress address;
                 byte[] mensaje_bytes = new byte[256];
                 String mensaje="";
                 mensaje_bytes=mensaje.getBytes();

                //Paquete
                 DatagramPacket paquete;

                 String cadenaMensaje="";

                DatagramPacket servPaquete;

                byte[] RecogerServidor_bytes = new byte[256];

                 try {
                 socket = new DatagramSocket();

                address=InetAddress.getByName("localhost");

                do {
                 mensaje = in.readLine();
              
                 mensaje_bytes = mensaje.getBytes();
                 paquete = new DatagramPacket(mensaje_bytes,mensaje.length(),address,6000);
                 socket.send(paquete);  // Manda el paquete 

                RecogerServidor_bytes = new byte[256];

                //Esperamos a recibir un paquete
                 servPaquete = new DatagramPacket(RecogerServidor_bytes,256);
                 socket.receive(servPaquete);

                //Convertimos el mensaje recibido en un string
                 cadenaMensaje = new String(RecogerServidor_bytes).trim();

                //Imprimimos el paquete recibido
                 System.out.println(cadenaMensaje);
                 } while (!mensaje.startsWith("fin"));
                 }
                 catch (Exception e) {
                 System.err.println(e.getMessage());
                 System.exit(1);
                 }
    }
    };
}
