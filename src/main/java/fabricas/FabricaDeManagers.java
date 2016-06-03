package fabricas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Joseildo on 18/08/2015.
 */
public class FabricaDeManagers {
    private static final String PERSISTENCE_UNIT = "JPA";
    private static ThreadLocal<EntityManager> threadEntityManager =
            new ThreadLocal<EntityManager>();
    private static EntityManagerFactory entityManagerFactory;
    private FabricaDeManagers() {
    }
    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory =
                    Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        EntityManager entityManager = threadEntityManager.get();
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = entityManagerFactory.createEntityManager();
            FabricaDeManagers.threadEntityManager.set(entityManager);
        }
        return entityManager;
    }
    public static void closeEntityManager() {
        EntityManager em = threadEntityManager.get();
        if (em != null) {
            EntityTransaction transaction = em.getTransaction();
            if (transaction.isActive()) {
                transaction.commit();
            }
            em.close();
            threadEntityManager.set(null);
        }
    }
    public static void closeEntityManagerFactory() {
        closeEntityManager();
        entityManagerFactory.close();
    }

}