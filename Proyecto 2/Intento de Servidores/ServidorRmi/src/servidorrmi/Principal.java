/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorrmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author estefania
 */
public class Principal {
    
    //para llevar la cuenta del coordinador
    public static int acumulador_votos = 0;
    
    public static void main(String args[]) throws RemoteException{
        System.out.println("Iniciando servidor....");
      
  
        
        try{
            
            //iniciando sockets e hilos
            new Comprobar().start();
            new Listener() .start();
            //Creando registro para almacenar la implementacion de la interfaz
            Registry registro = LocateRegistry.createRegistry(11560);
            //instanciando registros de la implemententation class
            registro.rebind("ReplicarObjetos",  new Implementation_class());
            registro.rebind("RestaurarObjetos", new Implementation_class());
            registro.rebind("HacerReplica",     new Implementation_class());
            registro.rebind("RecibirObjetos",   new Implementation_class());
            

        }catch(Exception e){
            
        }
    }
    
}
