package recursos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static recursos.Recurso.Recursos;
import static recursos.Recurso.Parametros;
/**
 * @author Joseildo Filho
 * @version 1.0
 * @since 1.0
 * Gerencia a criação dos recursos para serem consumidos pela url ou no sistema
 */
public class GerenteRecursos {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public GerenteRecursos(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
    /**
     * verifica se a palavra é a chamada de um recurso
     *
     * @param pedaco string contendo o possivel recurso
     * @return true = recurso || false != recurso
     * */
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

    /**
     * executa uma verredura na URL em busca de recursos
     *
     * */
    public void executeRecurso(String[] pedacosDaUrl) {
        for(String pedaco : pedacosDaUrl) {
            if(isRecurso(pedaco)) return;
        }
        try {
            response.sendError(503);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Cria um recurso e o executa
     * */
    private void criarRecurso(Recursos nome) {
        Recurso recurso;
        switch (nome) {
            //todo inserir uma reflection para buscar os recursos e criar
            case CRIAR:
                recurso = new RecursoCriar();
                inserirParametros(recurso);
                recurso.run(request,response);
                break;
        }
    }

    /**
     * Popula o recurso com os parametros necessarios para a sua execução
     * */
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
