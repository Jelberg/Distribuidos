/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 *
 * @author estefania
 */

public class ClienteTCP {

    // Función principal de la clase.
    public static void main(String args[]){
        
        // Creamos una instancia a null, de la clase socket.        
        Socket s = null;
        
        // Establecemos el puerto a utilizar en la variable serverPort.
        int serverPort =  7896;
        
        // Creamos una instancia para un flujo de entrada de datos.
        DataInputStream in;
        
        // Creamos una instancia para un fujo de salida de datos.
        DataOutputStream out;

        // data almacena los datos a enviar.
        String data;

        try {
            
            // Creamos un nuevo Socket, con el nombre de host pasado por argumento
            // y con el puerto establecido anteriormente.
           /* System.out.println("Introduzca el mensaje:");
            String mensaje="";
             //Creación de un objeto Scanner
            Scanner entradaEscaner = new Scanner (System.in); 
            //Invocamos un método sobre un objeto Scanner
            mensaje = entradaEscaner.nextLine (); 
            System.out.println ("Entrada recibida por teclado es: \"" + mensaje +"\"");
            
            //Pasando mensaje que sale
            mensaje=mensaje+", localhost";*/
           
            s = new Socket(args[1], serverPort);
            
            // Creamos un nuevo flujo de entrada de datos usando el socket creado.
            in = new DataInputStream(s.getInputStream());
            
            // Creamos un nuevo flujo de salida de datos usando el socket creado.
            out = new DataOutputStream(s.getOutputStream());
            
            // Escribimos en la tubería de datos el mensaje pasado como argumento.
            out.writeUTF(args[0]);
            
            // Obtiene la respuesta del servidor.
            data = in.readUTF();
            
            // Se muestra por pantalla.
            System.out.println("Datos recibidos: " +data+"\n");
        
        // Control de excepciones.
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\nHost no encontrado");
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

