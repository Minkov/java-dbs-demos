import entities.Product;
import org.hibernate.SessionFactory;
import repositories.base.RepositoryBase;
import repositories.hibernate.ProductsRepository;

public class Main {

    public static void main(String args[]) throws Exception {
        SessionFactory factory = HibernateUtils.getSessionFactory();
//        Session session = factory.getCurrentSession();
        RepositoryBase<Product> products = new ProductsRepository(factory);

        Product product = new Product("Sirene", 8, 1);
        products.create(product);

        products.getAll()
            .forEach(System.out::println);
    }
}