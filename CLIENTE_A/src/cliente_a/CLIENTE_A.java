/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente_a;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import cliente_a.Mw;
/**
 *
 * @author estefania
 */
public class CLIENTE_A{

 public static void main(String argv[]) {
	 
while(true){	 
    
    
 System.out.println("--------------Bienvenido---------------");
 System.out.println();
 
 
 
 System.out.println("Ingrese A para conectarse al servidor A");
 System.out.println("Ingrese B para conectarse al servidor B");
 System.out.println("Ingrese C para conectarse al servidor C");
 //Tomando el dato
 String entradaTeclado = "";
 Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
 entradaTeclado = entradaEscaner.nextLine ();
 System.out.println(entradaTeclado);
    if(!"A".equals(entradaTeclado)){

                //Llamando a middleware
                Mw middle= new Mw();
                middle.ActivandoMw(entradaTeclado);

    }

        else{

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
}
 }
}



