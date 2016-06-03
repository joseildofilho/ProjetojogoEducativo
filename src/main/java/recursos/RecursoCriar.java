package recursos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by root on 02/06/16.
 */
public class RecursoCriar implements Recurso {

    //todo refatorar interação dos recursos
    private String typedata;
    public RecursoCriar() {

    }

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) {
        request.getMethod();
        doGet();
        System.out.println("executando o run com a typedata de valor:"+typedata);
    }

    private void doGet() {
        StringBuilder sb = new StringBuilder();
        sb.append("inicio das palavras: \n");
        fachadaDao.getTodasPalavras().forEach(palavra -> sb.append(palavra.toString()+"\n"));
        System.out.println(sb.toString());
    }

    @Override
    public void setTypedata(String valor) {
        System.out.println("opa esta funcionando tudo ok");
        this.typedata = valor;
    }

}
