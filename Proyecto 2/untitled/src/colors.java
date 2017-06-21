import Model.Colores;
import Model.controllerColors;
import Model.util;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import static javax.print.attribute.standard.MediaPrintableArea.MM;
import static javax.swing.text.html.HTML.Tag.DD;


/**
 * Created by elberg on 20/06/17.
 */

@Path("/colors")
public class colors {
    Gson gson = new Gson();
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("DD-MM-YY");
    Date fecha = null;

    /**
     * Bienvenida al Web service
     * @return
     */

    @GET
    @Produces("text/plain")
    public String bienvenida(){
        return ("WELCOME TO THE JUNGLE");
    }

    /**
     * Servicio Agrega objeto a XML
     * @param nombre
     * @param fecha
     * @return
     * @throws IOException
     */

    @GET
    @Path("/addColor")
    @Produces("text/plain")
    public String insertColor(@QueryParam("color") String nombre,
                              @QueryParam("fecha") String fecha) throws IOException {
        controllerColors cc = new controllerColors();
        Colores color = new Colores();
        color.setColor(nombre);
        color.setFecha(fecha);
        cc.agregarColor(color);
        return ("AGREGADO");
    }

    /**
     * Servicio Elimina objeto por nombre
     * @param nombre
     * @return
     * @throws IOException
     */

    @GET
    @Path("/deleteColor")
    @Produces("text/plain")
    public String deleteColor(@QueryParam("color") String nombre) throws IOException {
        controllerColors cc = new controllerColors();
        Colores c =new Colores();
        c.setColor(nombre);
        cc.borrarColor(c);
        return ("ELIMINADO");
    }

    /**
     * Servicio devuelve todos los registros
     * @return
     * @throws IOException
     * @throws ParseException
     */
    @GET
    @Path("/getAllColors")
    @Produces("application/json")
    public String getAllColor() throws IOException, ParseException {
        controllerColors cc = new controllerColors();
        Colores c =new Colores();
        ArrayList<Colores> allColors = cc.todaslosColores();
        return gson.toJson(allColors);
    }

    /**
     * Devuelve colores segun la fecha
     * @param date
     * @return
     * @throws IOException
     * @throws ParseException
     */
    //**********REVISAAAAAARRRRRR***************
    @GET
    @Path("/getColorDate")
    @Produces("application/json")
    public String getColorDate(@QueryParam("fecha") Date date) throws IOException, ParseException {
        controllerColors cc = new controllerColors();
        Colores c =new Colores();
        ArrayList<Colores> allColors = cc.todaslosColoresXFecha(date);
        return gson.toJson(allColors);
    }


    /**
     * Servicio para descargar Archivo (Solamente XML)
     * @return
     * @throws FileNotFoundException
     */

    @GET
    @Path("/getFile")
    @Produces("application/xml")
    public Response GetDocument() throws FileNotFoundException {
    File file =new File(util.fileLocation);
        Response.ResponseBuilder response = Response.ok(file);
        response.header("Content-Disposition","attachment; filename=copy_colors.xml");
        return response.build();
    }



}
