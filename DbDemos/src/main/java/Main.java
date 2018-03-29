import entities.Product;
import org.hibernate.SessionFactory;
import repositories.base.RepositoryBase;
import repositories.hibernate.GenericRepository;

public class Main {
    public static void main(String args[]) throws Exception {
        SessionFactory factory = HibernateUtils.getSessionFactory();
        RepositoryBase<Product> products =
            new GenericRepository<>(factory, Product.class);

        Product product = new Product("Sirene", 8, 1);
        products.create(product);

        products.getAll()
            .forEach(System.out::println);
    }
}