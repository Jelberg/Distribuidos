/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorrmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author estefania
 */

public class Implementation_class extends UnicastRemoteObject implements Remote_Interface {
  
   public Implementation_class() throws RemoteException{
       super(); 
    }
   
 /* public static void main(String[] args)
  {
    try
    {
      MiInterfazRemoto mir = new Implementation_class();
      java.rmi.Naming.rebind(“//” + java.net.InetAddress.getLocalHost().getHostAddress() +
                              ”:” + args[0] + “/PruebaRMI”, mir);
    }
    catch (Exception e)
    {
    }
  }*/

    @Override
    public String ReplicarObjetos(String accion) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String RestaurarObjetos(String accion) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object HacerReplica() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String RecibirObjetos() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
