package Persistance;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Qiqi Wu
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    /**
     * creates a session factory based on hibernate.cfg.xml
     * @return the created session factory
     */
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Gets the session factory made with hibernate.cfg.xml
     * @return
     */
    public static SessionFactory getSessionFactory() {

        return sessionFactory;
    }

    /**
     * Closes caches and connection pools for the session factory
     */
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
