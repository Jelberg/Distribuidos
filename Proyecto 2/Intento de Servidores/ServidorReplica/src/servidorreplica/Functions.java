/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorreplica;

import RMi.Color;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author estefania
 */
public class Functions {
    	public static void guardar(Color prueba) throws Exception{
         
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         DOMImplementation implementation = builder.getDOMImplementation();
         Document document = implementation.createDocument(null, "RESPALDO", null);
         document.setXmlVersion("1.0");
         
         Element raiz = document.getDocumentElement();
         
         Element dato = document.createElement("Color");
         
         Element nombre = document.createElement("nombre");
         Text nodoNombre = document.createTextNode(prueba.getNombre());
         nombre.appendChild(nodoNombre);
         
         Element pru = document.createElement("atributo");
         Text nodoPrueba = document.createTextNode(prueba.getAtributo());
         pru.appendChild(nodoPrueba);
         
         dato.appendChild(pru);
         dato.appendChild(nombre);
         raiz.appendChild(dato);
         Source source = new DOMSource(document);
         Result result = new StreamResult(new java.io.File("Archivos.xml")); //nombre del archivo
         Transformer transformer = TransformerFactory.newInstance().newTransformer();
         transformer.transform(source, result);
	}
}
