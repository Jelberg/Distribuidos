/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorrmi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estefania
 */
public class ManejoDeHilos extends Thread {
     
    Socket peticion;
      int i;
    
    public ManejoDeHilos(Socket peticion, int i){
        
        this.peticion=peticion;
        this.i =i;
    
    }
    
    public void run(){
  
        try {
            
            ObjectInputStream respuestaServidorRepli = new ObjectInputStream(peticion.getInputStream());
            String respuesta = (String)respuestaServidorRepli.readUTF();
       
            if(respuesta.equals("VOTE_COMMIT")){
                
                //Envio del commit
                System.out.println(" RESULTADO :"+respuesta);
                Comprobar.seguir ++;

            }
            else
            {
            
                //Envio del commit  VOTE_ABORT
                System.out.println(" RESULTADO :"+respuesta);
                Comprobar.seguir --;

            
            }
        } catch (IOException ex) {
            
            Logger.getLogger(ManejoDeHilos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
