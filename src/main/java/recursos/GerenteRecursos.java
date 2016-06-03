package recursos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static recursos.Recurso.Recursos;
import static recursos.Recurso.Parametros;
/**
 * Created by root on 02/06/16.
 */
public class GerenteRecursos {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public GerenteRecursos(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    private boolean isRecurso(String pedaco) {
        for(Recurso.Recursos r: Recurso.Recursos.values()){
            if(r.name().equalsIgnoreCase(pedaco)) {
                criarRecurso(r);
                try {
                    response.sendError(200);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    public void executeRecurso(String[] pedacos) {
        for(String pedaco : pedacos) {
            if(isRecurso(pedaco)) break;
        }
    }

    private void criarRecurso(Recursos nome) {
        Recurso recurso;
        switch (nome) {
            case CRIAR:
                recurso = new RecursoCriar();
                inserirParametros(recurso);
                recurso.run(request,response);
                break;
        }
    }

    private void inserirParametros(Recurso recurso) {
        for(Method m : recurso.getClass().getDeclaredMethods()) {
            System.out.println(m.getName());
            for(Parametros p:Parametros.values()) {
                //todo alterar esse padrão para da suporte a anotações e não a pradores de nome
                if(m.getName().equalsIgnoreCase("set"+p.name().toLowerCase())){
                    try {
                        m.invoke(recurso,request.getParameter(p.name().toLowerCase()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                };
            }
        }
    }
}
