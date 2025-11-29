package Utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class XJPA {
    private static EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("PolyOE");
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}