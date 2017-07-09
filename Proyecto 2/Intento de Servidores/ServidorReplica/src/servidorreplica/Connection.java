/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorreplica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estefania
 */
class Connection extends Thread{

    // Instanciamos los fuljos de datos de entrada y salida,y el socket para
    // conexión.
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    // Constructor.
    public Connection(Socket aSocket){
        
        // Asocia el Socket(this) con el del cliente que conecta.
        clientSocket = aSocket;
        
        try {
            
            // Creamos los flujos de entrada y salida de datos, y lo se los 
            // asociamos al socket cliente.
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en conexion: " + ex.getMessage());
        }
        
        // Lanzamos el método run.
        this.start();
    }

    public void run(){
        
        try {
                        
            String data;
            
            // Recibe los datos mandados por el cliente.
            data = in.readUTF();
            
            // Realiza la réplica de los datos + un ACK.
            out.writeUTF("Servidor TCP,OK! " + data);
            
        // Control de excepciones.
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}