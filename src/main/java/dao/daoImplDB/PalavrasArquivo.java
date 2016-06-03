package dao.daoImplDB;

import dao.daoImplDB.FerramentaGravacao.FerramentaGravacao;
import dao.daoImplDB.FerramentaGravacao.IOTool;
import dao.exception.JaExisteIDCadastrado;
import dao.exception.NaoExistePalavraException;
import dao.interfaces.PalavrasDao;
import entidades.Palavra;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joseildo on 19/05/16.
 */
public class PalavrasArquivo implements PalavrasDao {

    private Map<String, Palavra> mapPalavras;
    private transient FerramentaGravacao<Map<String, Palavra>> io;
    private transient static final String NOME_ARQUIVO = "palavras.pj";

    public PalavrasArquivo() {
        this.mapPalavras = new HashMap<>();
        io = new IOTool<>();
    }

    @Override
    public void updatePalavra(String id, Palavra palavra) {
        if (mapPalavras.isEmpty()) throw new AssertionError();
        if (!mapPalavras.containsKey(id)) throw new AssertionError();
        mapPalavras.put(String.valueOf(id), palavra);
        gravarMapa();
    }

    @Override
    public void deletePalavra(String id) {
        if (mapPalavras.isEmpty()) throw new AssertionError();
        if (mapPalavras.containsKey(id)) mapPalavras.remove(id);
        gravarMapa();
    }

    @Override
    public Palavra readPalavra(String id) throws NaoExistePalavraException {
        if (mapPalavras.isEmpty()) carregarMapa();
        if (mapPalavras.containsKey(id)) return mapPalavras.get(id);
        throw new NaoExistePalavraException();
    }

    @Override
    public void createPalavra(String id, Palavra palavra) throws JaExisteIDCadastrado {
        if (mapPalavras.containsKey(id)) throw new JaExisteIDCadastrado();
        mapPalavras.put(id, palavra);
        gravarMapa();
    }

    @Override
    public List<Palavra> getTodasPalavras() {
        List<Palavra> temp = new ArrayList<>();
        for(Palavra palavra : mapPalavras.values()) temp.add(palavra);
        return temp;
    }


    private void gravarMapa() {
        io.gravarObjeto(mapPalavras, NOME_ARQUIVO);
    }

    private void carregarMapa() {
        try {
            mapPalavras = io.lerObjeto(NOME_ARQUIVO);
        } catch (FileNotFoundException e) {
            gravarMapa();
        }
    }
}
