import Model.Colores;
import Model.controllerColors;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    @GET
    @Produces("application/json")
    public String bienvenida(){
        return ("WELCOME TO THE JUNGLE");
    }

    @GET
    @Path("/addColor")
    @Produces("application/json")
    public String insertColor(@QueryParam("color") String nombre,
                              @QueryParam("fecha") String fecha) throws IOException {
        controllerColors cc = new controllerColors();
        Colores color = new Colores();
        color.setColor(nombre);
        color.setFecha(fecha);
        cc.agregarColor(color);
        return ("AGREGADO");
    }

    @GET
    @Path("/deleteColor")
    @Produces("application/json")
    public String deleteColor(@QueryParam("color") String nombre) throws IOException {
        controllerColors cc = new controllerColors();
        Colores c =new Colores();
        c.setColor(nombre);
        cc.borrarColor(c);
        return ("ELIMINADO");
    }

    @GET
    @Path("/getAllColors")
    @Produces("application/json")
    public String getAllColor() throws IOException, ParseException {
        controllerColors cc = new controllerColors();
        Colores c =new Colores();
        ArrayList<Colores> allColors = cc.todaslosColores();
        return gson.toJson(allColors);
    }


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
}
