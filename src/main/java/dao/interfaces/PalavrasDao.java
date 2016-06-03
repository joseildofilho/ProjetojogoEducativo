package dao.interfaces;

import dao.exception.JaExisteIDCadastrado;
import dao.exception.NaoExistePalavraException;
import entidades.Palavra;

import java.util.List;

/**
 * Created by joseildo on 19/05/16.
 */
public interface PalavrasDao {
    void updatePalavra(String id, Palavra palavra);
    void deletePalavra(String id);
    Palavra readPalavra(String id) throws NaoExistePalavraException;
    void createPalavra(String id, Palavra palavra) throws JaExisteIDCadastrado;
    List<Palavra> getTodasPalavras();

}
