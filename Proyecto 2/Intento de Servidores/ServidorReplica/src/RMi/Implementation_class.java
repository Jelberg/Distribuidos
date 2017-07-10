/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMi;

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
        //estos metodos se comunican con lso servidore de replicas y restaurarcion

        Socket replicar = new Socket("192.168.0.103",40000);
        ObjectOutputStream global = new ObjectOutputStream(replicar.getOutputStream());
        global.writeUTF("VOTE_REQUEST:"+accion);
        global.close();
        replicar.close();
        
        } catch (IOException ex) {
            Logger.getLogger(Implementation_class.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "la accion querecibe el metedo replicar objectos "+accion;
    }

    @Override
    public String RestaurarObjetos(String accion) throws RemoteException {//estos metodos se comunican con lso servidore de replicas y restaurarcion
                
        
         return "la accion que recibo es: "+accion;
    }

    @Override
    public Object HacerReplica() throws RemoteException {
      
      Object a = null;
        
        return a;
    }

    @Override
    public String RecibirObjetos() throws RemoteException {
        return "objetos recibidos";
    }
}
