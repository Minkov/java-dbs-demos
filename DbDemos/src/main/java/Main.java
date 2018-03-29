import entities.Category;
import entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class Main {

    public static void main(String args[]) throws Exception {

        SessionFactory factory = HibernateUtils.getSessionFactory();
        Session session = factory.getCurrentSession();

        session.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Product> criteria =
            criteriaBuilder.createQuery(Product.class);

        criteria.from(Product.class);

        session.createQuery(criteria)
            .getResultList()
            .forEach(System.out::println);


        criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Category> criteriaCategoties =
            criteriaBuilder.createQuery(Category.class);

        criteriaCategoties.from(Category.class);

        session.createQuery(criteriaCategoties)
            .getResultList()
            .forEach(System.out::println);

    }
//        Connection connection = getConnection();
//
//        RepositoryBase<Category> categories = new CategoriesRepository(connection);
//
//        System.out.println(categories.getById(1));
//
//        RepositoryBase<Product> products = new ProductsRepository(connection);
//
//        Product product = new Product("Luk", 0.5f, 1);
//
//        product = products.create(product);
//
//        System.out.println(product.toString());
//
//        products.getAll()
//            .forEach(System.out::println);
//
//        connection.close();
//    }
//
//    static Connection getConnection() throws Exception {
//        // test if jdbc driver is available
//        Class.forName("com.mysql.jdbc.Driver");
//
//        Connection con = DriverManager.getConnection(
//            "jdbc:mysql://localhost:3306/onlinemarketdb", "root", "");
//        return con;
//    }
}  