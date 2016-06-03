package fachadas;

import dao.daoImpls.PalavrasArquivo;
import dao.exception.JaExisteIDCadastrado;
import dao.exception.NaoExistePalavraException;
import dao.interfaces.PalavrasDao;
import entidades.Palavra;

import java.util.List;

/**
 * Fachada que traz do baixo nivel as classes para a logica do negocio
 * Reunindo todos os metodos dos daos
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
