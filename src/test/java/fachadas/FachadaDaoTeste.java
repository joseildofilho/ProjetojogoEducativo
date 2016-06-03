package fachadas;

import dao.exception.JaExisteIDCadastrado;
import dao.exception.NaoExistePalavraException;
import entidades.Imagem;
import entidades.Palavra;
import entidades.Serializador;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by joseildo on 19/05/16.
 */

public class FachadaDaoTeste {
    private FachadaDao fachada = new FachadaDao();
    private Palavra palavra1, palavra2, palavra3;
    private Imagem imagem1, imagem2, imagem3;
    private Serializador serializador = new Serializador();
    @Before
    public void setUp() {
        palavra1 = new Palavra();
        palavra1.setPalavra("Caderno");
        palavra2 = new Palavra();
        palavra2.setPalavra("Mouse");
        palavra3 = new Palavra();
        palavra3.setPalavra("Computador");

        imagem1 = new Imagem();
        imagem2 = new Imagem();
        imagem3 = new Imagem();

        imagem1.setImagem("/imagem.jpg");
        imagem2.setImagem("Mouse da Dell");
        imagem3.setImagem("Computador mac");

        palavra1.relacionarImagem(imagem1);
        palavra2.relacionarImagem(imagem2);
        palavra3.relacionarImagem(imagem3);

    }

    @Test(expected = JaExisteIDCadastrado.class)
    public void createPalavraTest() throws JaExisteIDCadastrado {

        fachada.createPalavra(palavra1.getPalavra(), palavra1);
        fachada.createPalavra(palavra2.getPalavra(), palavra2);
        fachada.createPalavra(palavra3.getPalavra(), palavra3);
        fachada.createPalavra(palavra1.getPalavra(), palavra1);
    }

    @Test
    public void updatePalavraTest() throws NaoExistePalavraException {
        try {
            createPalavraTest();
        } catch (JaExisteIDCadastrado jaExisteIDCadastrado) {
        }
        Imagem imagem4 = new Imagem();
        imagem4.setImagem("Computador Windows");
        assertEquals(fachada.readPalavra(palavra1.getPalavra()), palavra1);
        palavra1.relacionarImagem(imagem4);
        fachada.updatePalavra(palavra1.getPalavra(), palavra1);
        assertEquals(fachada.readPalavra(palavra1.getPalavra()), palavra1);
        System.out.println(serializador.serizalizar(palavra1));
        System.out.println(serializador.Deserializa(serializador.serizalizar(palavra1)));
    }

    @Test(expected = NaoExistePalavraException.class)
    public void removePalavraTest() throws NaoExistePalavraException {
        try {
            createPalavraTest();
        } catch (JaExisteIDCadastrado jaExisteIDCadastrado) {
        }
        assertEquals(fachada.readPalavra(palavra1.getPalavra()), palavra1);
        fachada.deletePalavra(palavra1.getPalavra());
        fachada.readPalavra(palavra1.getPalavra());
    }

    @Test(expected = NaoExistePalavraException.class)
    public void readPalavraTest() throws NaoExistePalavraException {
        assertEquals(fachada.readPalavra(palavra1.getPalavra()), palavra1);
        assertEquals(fachada.readPalavra(palavra2.getPalavra()), palavra2);
        assertEquals(fachada.readPalavra(palavra3.getPalavra()), palavra3);
        fachada.deletePalavra(palavra1.getPalavra());
        assertEquals(fachada.readPalavra(palavra1.getPalavra()), palavra1);
    }

    @Test
    public void getTodasPalavrasTest() {
        try {
            createPalavraTest();
        } catch (JaExisteIDCadastrado jaExisteIDCadastrado) {
        }
        assertEquals(fachada.getTodasPalavras().size(),3);

    }
    @Test
    public void print() {
        List<Palavra> palavras = new ArrayList<>();
        palavras.add(palavra1);
        palavras.add(palavra2);
        palavras.add(palavra3);
        System.out.println(serializador.serizalizar(palavras));
    }

}
