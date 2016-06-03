package fachadas;

import dao.daoImplDB.PalavrasArquivo;
import dao.exception.JaExisteIDCadastrado;
import dao.exception.NaoExistePalavraException;
import dao.interfaces.PalavrasDao;
import entidades.Palavra;

import java.util.List;

/**
 * Created by joseildo on 19/05/16.
 */
public class FachadaDao {

    PalavrasDao palavrasDao = new PalavrasArquivo();

    public void updatePalavra(String id, Palavra palavra) {
        this.palavrasDao.updatePalavra(id, palavra);
    }

    public void deletePalavra(String id) {
        this.palavrasDao.deletePalavra(id);
    }

    public Palavra readPalavra(String id) throws NaoExistePalavraException {
        return this.palavrasDao.readPalavra(id);
    }

    public void createPalavra(String id, Palavra palavra) throws JaExisteIDCadastrado {
        this.palavrasDao.createPalavra(id, palavra);
    }

    public List<Palavra> getTodasPalavras() {
        return palavrasDao.getTodasPalavras();
    }
}
