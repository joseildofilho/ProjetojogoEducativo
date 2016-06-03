package dao.interfaces;

import dao.exception.JaExisteIDCadastrado;
import dao.exception.NaoExistePalavraException;
import entidades.Palavra;

import java.util.List;

/**
 * @author Joseildo Filho
 * @version 1.0
 * @since 1.0
 *
 *  Interface para implementação do dao que controlara as palavras
 */
public interface PalavrasDao {
    void updatePalavra(String id, Palavra palavra);
    void deletePalavra(String id);
    Palavra readPalavra(String id) throws NaoExistePalavraException;
    void createPalavra(String id, Palavra palavra) throws JaExisteIDCadastrado;
    List<Palavra> getTodasPalavras();

}
