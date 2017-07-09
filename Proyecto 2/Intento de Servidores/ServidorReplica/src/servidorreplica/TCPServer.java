/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorreplica;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author estefania
 */
public class TCPServer{

    public static void main(String args[]){

        // Establecemos el número de puerto a utilizar.
        int serverPort = 7896;
        
        // Creamos una instancia para esperar las peticiones de los clientes.
        ServerSocket listenSocket;
        
        // Socket para conexión.
        Socket clientSocket;

        // Usamos la clase conection.
        Connection c;

        try{
            
            // Creamos el objeto para esperar peticiones de los clientes.
            listenSocket = new ServerSocket(serverPort);

            while (true){
                
                // Dejamos invocado el servidor esperando haste que un cliente
                //se conecte. clientSocket = Socket nuevo para comunicaciones.
                clientSocket = listenSocket.accept();
                
                // Se establece la conexión, y se vuelve a esperar un nuevo cliente.
                c = new Connection(clientSocket);
            }
            
        // Control de excepciones.
        }catch(IOException e){
            System.out.println("Error en socket: " + e.getMessage());
        }
    }
}
