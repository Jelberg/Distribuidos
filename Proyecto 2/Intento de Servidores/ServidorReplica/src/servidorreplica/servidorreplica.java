/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorreplica;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author estefania
 */
public class servidorreplica extends Thread {
      public void run(){
        try{
          ServerSocket server = new ServerSocket(20500);  
            System.out.println("Esperando al coordinador");
            int i =1;
            
            for(;;){
                Socket clientecoordinador = server.accept();
                new ManejoHilos(clientecoordinador,i).start();
                System.out.println("Cliente recibido");
                i++;
            }
        }catch(Exception e){
            
        }
    }
}
