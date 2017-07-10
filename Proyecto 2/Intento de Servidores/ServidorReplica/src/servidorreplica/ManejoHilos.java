/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorreplica;

import RMi.Color;
import RMi.Remote_Interface;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estefania
 */
public class ManejoHilos extends Thread {
        private Socket cliente;
    private int numero;

    public ManejoHilos(Socket cliente, int numero) {
        this.cliente = cliente;
        this.numero = numero;
    }
    
    public void run(){
        
        ObjectInputStream recibo = null;
        try {
            recibo = new ObjectInputStream(cliente.getInputStream());
            String llegada = (String) recibo.readUTF();
            System.out.println("loque llega "+llegada);
            recibo.close();
            String ipdestino = cliente.getInetAddress().getHostAddress();
            cliente.close();
            
            if(llegada.contains(":")){
            
                String[] accion = llegada.split(":");

             if(accion[1].equals("COMMIT")){
                
                 Socket socketAserver = new Socket(ipdestino,32500);
                ObjectOutputStream salida = new ObjectOutputStream(socketAserver.getOutputStream());
                salida.writeUTF("VOTE_COMMIT");
                salida.close();
                socketAserver.close();
                System.out.println("Enra commit");
              
             }
             if(accion[1].equals("ABORT")){
             
                 Socket socketAserver = new Socket(ipdestino,32500);
                ObjectOutputStream salida = new ObjectOutputStream(socketAserver.getOutputStream());
                salida.writeUTF("VOTE_ABORT");
                System.out.println("Entra abor");
                salida.close();
                socketAserver.close();
             
             }
            }else{
             
                if(llegada.equals("GLOBAL_COMMIT")){
                    System.out.println("recibo un global comit y hago rmi");
                       Registry miregistro = LocateRegistry.getRegistry("192.168.0.100",16500);
                    try {
                        Remote_Interface s = (Remote_Interface) miregistro.lookup("HacerReplica");
                        System.out.println("llamando al metodo hacerreplica ...");
                        Color pru = (Color) s.HacerReplica();
                        System.out.println(pru.getNombre());
                        Functions.guardar(pru);
                    } catch (RemoteException ex) {
                        Logger.getLogger(ManejoHilos.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NotBoundException ex) {
                        Logger.getLogger(ManejoHilos.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(ManejoHilos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(llegada.equals("GLOBAL_ABORT")){
                    System.out.println("recibo un global abort del coordina y no hago nada");
                }
            }
            
            
           
                
            
            
        } catch (IOException ex) {
            Logger.getLogger(ManejoHilos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                recibo.close();
            } catch (IOException ex) {
                Logger.getLogger(ManejoHilos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
