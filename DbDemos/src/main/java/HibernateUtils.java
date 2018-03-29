import entities.Category;
import entities.Product;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration()
            .configure();

        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(Category.class);

        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

        serviceRegistryBuilder.applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}