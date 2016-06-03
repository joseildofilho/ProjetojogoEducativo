package recursos;

import fachadas.FachadaDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by root on 02/06/16.
 */
public interface Recurso {
    FachadaDao fachadaDao = new FachadaDao();

    enum Recursos {
        CRIAR
    }
    enum Parametros {
        TYPEDATA
    }

    default void setTypedata(String valor) {
        System.out.println("este parametro não existe");
    }

    void run(HttpServletRequest request, HttpServletResponse response);
}
