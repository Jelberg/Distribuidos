/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;


import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
/**
 *
 * @author elberg
 */
public class controllerColors {
     private Element root;

    private String fileLocation = util.fileLocation;

    public controllerColors() throws IOException {
        
        try {
            SAXBuilder builder = new SAXBuilder(false);
            Document doc = null;
            doc = builder.build(fileLocation);
            root = doc.getRootElement();
            
        } catch (JDOMException ex) {
            System.out.println("No se pudo iniciar la operacion por: " + ex.getMessage());
        }
    }

    private Element ColortoXmlElement(Colores c) {
        Element produc = new Element("Colores");
        Element color = new Element("color");
        color.setText(c.getColor());
        Element fecha = new Element("fecha");
        fecha.setText(c.getFecha().toString());
        produc.addContent(color);
        produc.addContent(fecha);
        return produc;
    }

    private Colores ColorToObject(Element element) throws ParseException {
        Colores c = new Colores(element.getChildText("color"), element.getChildText("fecha"));
        return c;
    }

    /**
     * Metodo que hace actualiza las modificaciones del XML
     * @return 
     */
    
    private boolean updateDocument() {
        try {
            XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat());
            FileOutputStream file = new FileOutputStream(fileLocation);
            out.output(root, file);
            file.flush();
            file.close();
            return true;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Metodo Para buscar el color
     * @param raiz
     * @param dato
     * @return 
     */
    public static Element buscar(List raiz, String dato) {
        Iterator i = raiz.iterator();
        while (i.hasNext()) {
            Element e = (Element) i.next();
            if (dato.equals(e.getChild("color").getText())) {
                return e;
            }
        }
        return null;
    }
    
    /**
     * Metodo para borrar color  del XML
     * @param p
     * @return 
     */
      public boolean borrarColor(Colores p) {
        boolean resultado = false;
        Element aux = new Element("Colores");
        List Colors = this.root.getChildren("Colores");
        while (aux != null) {
            aux = controllerColors.buscar(Colors, p.getColor());
            if (aux != null) {
                Colors.remove(aux);
                resultado = updateDocument();
            }
        }
        return resultado;
    }
      /**
       * Metodo para agregar color al xml
       * @param nE
       * @return 
       */

    public boolean agregarColor(Colores nE) {
        boolean resultado = false;
        root.addContent(ColortoXmlElement((Colores) nE));
        resultado = updateDocument();
        return resultado;
    }
    
    
    /**
    * Metodo para devolver un arrayList de todos los registros
    * @return 
    */
    public ArrayList<Colores> todaslosColores() throws ParseException {
        ArrayList<Colores> resultado = new ArrayList<Colores>();
        for (Object it : root.getChildren()) {
            Element xmlElem = (Element) it;
            resultado.add(ColorToObject(xmlElem));
        }
        return resultado;
    }



    public ArrayList<Colores> todaslosColoresXFecha(Date date) throws ParseException {
        ArrayList<Colores> resultado = new ArrayList<Colores>();
        for (Object it : root.getChildren()) {
            Element xmlElem = (Element) it;
            Colores col = ColorToObject(xmlElem);
            if (col.getFecha() == date.toString()) {
                System.out.println(date.toString());
                resultado.add(col);
            }
        }
        return resultado;
    }
    
}
