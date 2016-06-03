package dao.daoImplDB;

import fabricas.FabricaDeManagers;
import dao.exception.JaExisteIDCadastrado;
import dao.exception.NaoExistePalavraException;
import dao.interfaces.PalavrasDao;
import entidades.Palavra;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by joseildo on 19/05/16.
 */
//todo implementar esta classe
public class PalavrasDB implements PalavrasDao {

    private EntityManager fabricaDeManagers = FabricaDeManagers.getEntityManager();

    @Override
    public void updatePalavra(String id, Palavra palavra) {
        fabricaDeManagers.getTransaction().begin();
        fabricaDeManagers.merge(palavra);
        fabricaDeManagers.getTransaction().commit();
        fabricaDeManagers.close();
    }

    @Override
    public void deletePalavra(String id) {
        fabricaDeManagers.getTransaction().begin();
        //fabricaDeManagers.remove(i);
    }

    @Override
    public Palavra readPalavra(String id) throws NaoExistePalavraException {
        return null;
    }

    @Override
    public void createPalavra(String id, Palavra palavra) throws JaExisteIDCadastrado {

    }

    @Override
    public List<Palavra> getTodasPalavras() {
        return null;
    }
}

