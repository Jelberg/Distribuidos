/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorrmi;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estefania
 */

public class Implementation_class extends UnicastRemoteObject implements Remote_Interface {
  
   public Implementation_class() throws RemoteException{
       super(); 
    }

    @Override
    public String ReplicarObjetos(String accion) throws RemoteException {
            try {
            int contador_votos = 0;
            
        //PARA CONECTAR CON LOS SERVERS DE REPCLICA Y RESTAURACION
  
        while(contador_votos < 2){//enivio VOTE request a los dos servidores de réplicas
        String parametros[] = Listener.ipReplicas[contador_votos].split(":");
        
        Socket replicar = new Socket(parametros[0],Integer.parseInt(parametros[1]));
        
        ObjectOutputStream global = new ObjectOutputStream(replicar.getOutputStream());
        
        global.writeUTF("VOTE_REQUEST :"+accion);
        
        global.close();
        
        replicar.close();
        
        contador_votos++;
    
        }

        
        } catch (IOException ex) {
            Logger.getLogger(Implementation_class.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Accion: "+accion;
    }

    @Override
    public String RestaurarObjetos(String accion) throws RemoteException {
             return "Accion: "+accion;
    }

    @Override
    public Object HacerReplica() throws RemoteException {
       
      Object a = new Color("Un Objeto de la Clase","pablo");
      //aqui tengo que enviar el XML que está del lado del servidor de app
        
        return a;   
        
    }

    @Override
    public String RecibirObjetos() throws RemoteException {
    return "Objetos Recibidos";
    }
}
