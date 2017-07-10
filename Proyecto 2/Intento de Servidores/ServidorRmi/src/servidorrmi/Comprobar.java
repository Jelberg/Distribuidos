/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorrmi;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estefania
 */
public class Comprobar extends Thread {
    public static int seguir ;
    int o = 0;
    
    
    public void run(){
        seguir =0;
     while(true){  
        try {
            
            if(seguir==0){
                System.out.println("Es cero");
            }
            if(seguir==1){
                System.out.println("llego una resp");
            }
            if(seguir==2){
                while(o<2){
                     String parametros[] = Listener.ipReplicas[o].split(":");
                    Socket socket = new Socket(parametros[0],Integer.parseInt(parametros[1]));
                    ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
                    salida.writeUTF("GLOBAL_COMMIT");
                    salida.close();
                    socket.close();
                    o++;
                }
                o=0;
                seguir=0;
            }
            if(seguir==-2){
                 while(o<2){
                    String parametros[] = Listener.ipReplicas[o].split(":");
                    Socket socket = new Socket(parametros[0],Integer.parseInt(parametros[1]));
                    ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
                    salida.writeUTF("GLOBAL_ABORT");
                    salida.close();
                    socket.close();
                    o++;
                }
                o=0;
                seguir=0;
            }
            Thread.sleep(500);
            
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Comprobar.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (IOException ex) {
                Logger.getLogger(Comprobar.class.getName()).log(Level.SEVERE, null, ex);
            }
     }
    }
    
}
