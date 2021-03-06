package servlet;

import fachadas.FachadaDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet que sera usada como interface de contato da aplicação externa
 */
@WebServlet(name = "JogoServlet",value = "/jogo/*")
public class JogoServlet extends HttpServlet {

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException,IOException {
        response.sendError(501);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.sendError(501);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        new RESTAnalyzer(request,response).analyzeGET();

    }
}
