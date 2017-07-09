/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorrmi;

/**
 *
 * @author estefania
 */
public interface Remote_Interface extends java.rmi.Remote {

   //Declarando metodos del interface
   public Object HacerReplica()   throws java.rmi.RemoteException;
   public String RecibirObjetos() throws java.rmi.RemoteException;
   public String ReplicarObjetos(String accion)  throws java.rmi.RemoteException;
   public String RestaurarObjetos(String accion) throws java.rmi.RemoteException;
  
    
}
