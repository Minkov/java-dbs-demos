package repositories.hibernate;

import entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repositories.base.RepositoryBase;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ProductsRepository implements RepositoryBase<Product> {
    private SessionFactory session;
    private SessionFactory sessionFactory;

    public ProductsRepository(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public List<Product> getAll() throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        criteriaQuery.from(Product.class);

        List<Product> products = session.createQuery(criteriaQuery)
            .getResultList();

        System.out.println();
        System.out.println(products.get(0).getCategories());

        transaction.commit();
        session.close();

        return products;
    }

    @Override
    public Product create(Product entity) throws Exception {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session
            .save(entity);

        transaction.commit();
        session.close();
        return entity;
    }

    @Override
    public Product getById(int id) throws Exception {
        return null;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
