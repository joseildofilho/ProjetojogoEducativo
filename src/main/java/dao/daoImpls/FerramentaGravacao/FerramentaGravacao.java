package dao.daoImpls.FerramentaGravacao;

import java.io.FileNotFoundException;

/**
 * Descreve uma meio do sistema gravar em disco algo
 * Originalmente foi penssada para arquivos, por isso possui throws FileNotFound
 * ira tornar-se deprecated
 */
public interface FerramentaGravacao<T> {

    void gravarObjeto(T o, String NOME);

    T lerObjeto(String NOME) throws FileNotFoundException;

    void removerObjeto(String NOME) throws FileNotFoundException;
}
