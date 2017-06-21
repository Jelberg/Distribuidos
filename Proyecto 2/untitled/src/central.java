/**
 * Created by root on 14/05/17.
 */

import Model.Colores;
import Model.controllerColors;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/* Se define la raiz de todo el proyecto*/
@ApplicationPath("/")

/* Clase que contiene todos los ws del proyecto */
public class central extends Application {


    public central() throws IOException {
    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h= new HashSet<Class<?>>();
        h.add(colors.class);
        return h;
    }
}
