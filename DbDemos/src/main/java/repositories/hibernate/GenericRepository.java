package repositories.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repositories.base.RepositoryBase;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class GenericRepository<T> implements RepositoryBase<T> {
    private SessionFactory session;
    private SessionFactory sessionFactory;
    private Class<T> entityClass;

    public GenericRepository(SessionFactory sessionFactory, Class<T> entityClass) {
        setSessionFactory(sessionFactory);
        setEntityClass(entityClass);
    }

    @Override
    public List<T> getAll() throws Exception {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<T> criteriaQuery = builder.createQuery(getEntityClass());
        criteriaQuery.from(getEntityClass());

        List<T> products = session.createQuery(criteriaQuery)
            .getResultList();

        transaction.commit();
        session.close();

        return products;
    }

    @Override
    public T create(T entity) throws Exception {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session
            .save(entity);

        transaction.commit();
        session.close();
        return entity;
    }

    @Override
    public T getById(int id) throws Exception {
        Session session = getSessionFactory().openSession();

        T entity = session.get(getEntityClass(), id);

        session.close();
        return entity;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
}
