package a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class ManejoArchivos {
	public String dirProductos ="src//a//Files//cantidadProductos.txt";
	public String nombreArchivoProductos = "cantidadProductos.json"; 
	public String dirMultiplo ="src//a//Files//cantidadMultiplo.txt";
	
	/**
	 * Metodo Sobre escribe en un fichero el valor de la linea
	 * 
	 * @param valor
	 * @param direccion
	 * @throws IOException
	 */
	
	public void sobreEscribeUnValor(String valor,String direccion) throws IOException{
		File archivo = null;
	      FileReader fr = null;
	      BufferedReader br1 = null;
		File file = new File(direccion);
		if (!file.exists()){
			file.createNewFile();
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(valor);
            bw.close();
		}else {
			try {
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
	            BufferedWriter bw = new BufferedWriter(fw);
	            bw.write(valor);
	            bw.close();				
	      } catch(Exception e){
	          e.printStackTrace();
	      }
		}
		
	}
	
	/**
	 * 
	 * Lee la linea del achivo .txt que se le pasa
	 * 
	 * @param direccion
	 * @param Linea
	 * @return
	 */
	
	public String leeLinea(String direccion, int Linea){
		File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;
	      String texto=null;
	      try {
	          // Apertura del fichero y creacion de BufferedReader para poder
	          // hacer una lectura comoda (disponer del metodo readLine()).
	          archivo = new File (direccion);
	          fr = new FileReader (archivo);
	          br = new BufferedReader(fr);

	          // Lectura del fichero
	        for(int i=0; i < Linea; i++){
	          if ((texto=br.readLine())!=null && i==Linea)
	             System.out.println(texto);
	       }
	        return texto;
	        }
	       catch(Exception e){
	          e.printStackTrace();
	       }finally{
	          // En el finally cerramos el fichero, para asegurarnos
	          // que se cierra tanto si todo va bien como si salta 
	          // una excepcion.
	          try{                    
	             if( null != fr ){   
	                fr.close();     
	             }                  
	          }catch (Exception e2){ 
	             e2.printStackTrace();
	          }
	       }
		return texto;
	
	}
	
	

}
