/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import servidorreplica.servidorreplica;


/**
 *
 * @author estefania
 */
public class Principal {
    public static int votos = 0;
    public static void main(String args[]) throws RemoteException{
       
        new servidorreplica().start();
        try{
            Registry miRegistry = LocateRegistry.createRegistry(20600);
            miRegistry.rebind("ReplicarObjetos",  new Implementation_class());
            miRegistry.rebind("RestaurarObjetos", new Implementation_class());
            miRegistry.rebind("HacerReplica",     new Implementation_class());
            miRegistry.rebind("RecibirObjetos",   new Implementation_class());
            
            System.out.println("Servidor On");
        }catch(Exception e){
            
        }
    }
}
