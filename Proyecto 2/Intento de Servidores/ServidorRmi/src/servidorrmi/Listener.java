/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorrmi;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author estefania
 */
public class Listener extends Thread {
    
    //Declarando ip y puertos de los servidores de replica
    
     public static String[] ipReplicas = {"192.168.0.100:35600","192.168.0.103:20500"};

     public void run(){
         
        System.out.println("Listener Online....");
      
        try{
            //Creando sockets 
            ServerSocket server = new ServerSocket(32500);
            int i = 1;
            
            for(;;){
                Socket peticion = server.accept();
                
                System.out.println("Cliente ok: "+i);
                
                new ManejoDeHilos(peticion,i).start(); 
                i++;
            }
            
        }
        catch(IOException e){
            
        }
    }
    
}
