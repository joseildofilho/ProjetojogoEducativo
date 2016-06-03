package entidades;

import com.google.gson.Gson;
import fachadas.FachadaDao;

import java.util.List;

/**
 * Created by joseildo on 19/05/16.
 */
public class Serializador {

    Gson json = new Gson();

    public Serializador() {

    }

    public String serizalizar(Palavra palavra) {
        return json.toJson(palavra);
    }

    public String serizalizar(FachadaDao fachada) {
        return json.toJson(fachada);
    }

    public String serizalizar(List fachada) {
        return json.toJson(fachada);
    }

    public Palavra Deserializa(String palavra) {
        return json.fromJson(palavra,Palavra.class);
    }
}
