package servlet;

import fachadas.FachadaDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by root on 02/06/16.
 */
@WebServlet(name = "JogoServlet",value = "/jogo/*")
public class JogoServlet extends HttpServlet {

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException,IOException {
        response.getWriter().print("Imprimendo algum valor PUT");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.getWriter().print("Imprimendo algum valor POST");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        new RESTAnalyzer(request,response).analyzeGET();

    }
}
