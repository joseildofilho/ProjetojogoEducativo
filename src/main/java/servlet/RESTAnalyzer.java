package servlet;

import recursos.GerenteRecursos;
import recursos.Recurso;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Classe consumivel, onde tratarar as respostas, como um serviço OR (orientada recursos)
 * @author joseildo filho
 * @version 1.0
 * @since 1.0
 */
public class RESTAnalyzer {
    private GerenteRecursos gerenteRecursos;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final String[] pedacosURI;

    public RESTAnalyzer(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.gerenteRecursos = new GerenteRecursos(this.request,this.response);
        String url;
        try {
            url = java.net.URLDecoder.decode(this.request.getRequestURL().toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            url = java.net.URLDecoder.decode(this.request.getRequestURL().toString());
        }
        this.pedacosURI = url.split("/");
        System.out.println(this.request.getRequestURL().toString());
    }
    //todo encapsular este metodo para que o usuario do servlet precise apenas passar o Http
    public void analyzeGET() {
        gerenteRecursos.executeRecurso(pedacosURI);
    }
}
