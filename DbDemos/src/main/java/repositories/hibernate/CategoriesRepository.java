package repositories.hibernate;

import entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repositories.base.RepositoryBase;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CategoriesRepository implements RepositoryBase<Category> {
    private SessionFactory session;
    private SessionFactory sessionFactory;

    public CategoriesRepository(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public List<Category> getAll() throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<Category> criteriaQuery = builder.createQuery(Category.class);
        criteriaQuery.from(Category.class);

        List<Category> categories = session.createQuery(criteriaQuery)
            .getResultList();

        transaction.commit();
        session.close();

        return categories;
    }

    @Override
    public Category create(Category entity) throws Exception {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session
            .save(entity);

        transaction.commit();
        session.close();
        return entity;
    }

    @Override
    public Category getById(int id) throws Exception {
        Session session = getSessionFactory().openSession();

        Category Category = session.get(Category.class, id);

        session.close();
        return Category;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
